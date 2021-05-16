package com.gaurav.krushimitra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RequestQueue requestQueue;
    TextView temperature,humidity;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        //String eemail=firebaseUser.getEmail();
        temperature=findViewById(R.id.temperature);
        humidity=findViewById(R.id.humidity);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Submit(99234);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refresh) {
            finish();
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);
        }
        if (id == R.id.logout) {
            finish();
            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.cul_tips:
                startActivity(new Intent(this,CultipsActivity.class));
                break;
            case R.id.home:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.nav_diagnose:
                startActivity(new Intent(this,DiagnosisActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(this,ProfileActivity.class));
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void Submit(int data)
    {
//        final String saveData=data;
        try{
            String url="https://samp-model.herokuapp.com/api";
            try{
                requestQueue= Volley.newRequestQueue(getApplicationContext());
            }
            catch (Exception e){
                e.printStackTrace();
            }


            JSONObject postData=new JSONObject();
            try{
                postData.put("strt",data);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String temp = response.getString("result");
                        char c=0xB0;
                        String ss=" ";
                        ss+=c;
                        temperature.setText(temp+ss+"C");
                        humidity.setText(String.valueOf(Integer.parseInt(temp)+1));
//                    precipitation.setText(String.valueOf(Integer.parseInt(temp)-(2*2)));
                        Log.d("TEMPERATURE RESPONSE", temp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            requestQueue.add(jsonObjectRequest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Submit(99234);
    }
}