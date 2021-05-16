package com.gaurav.krushimitra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    String city="",name="";
    TextView t1,t2,t3;
    Button btn;
    Toolbar toolbar=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        t1=findViewById(R.id.name);
        t2=findViewById(R.id.email);
        t3=findViewById(R.id.city);
        btn=findViewById(R.id.btn);
        final String targetEmail=firebaseAuth.getCurrentUser().getEmail();


        t2.setText(targetEmail);
        db.collection("UserData").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : lst) {

                                if (d.getString("email").equals(targetEmail)) {
                                    Log.e("CHECK",d.getString("email")+" "+targetEmail);
                                    city=d.getString("city");
                                    name=d.getString("name");
                                    Log.e("DATTA",city+name);
                                    t1.setText(name);

                                    t3.setText(city);
                                    break;
                                }
                            }
                        }
                    }
                });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeTextViewEdit(t1);
                makeTextViewEdit(t2);
                makeTextViewEdit(t3);
            }
        });

//        Log.e("DATTA",city+name);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);



    }
    void makeTextViewEdit(TextView tv)
    {
        tv.setFocusable(true);
        tv.setEnabled(true);
        tv.setClickable(true);
        tv.setFocusableInTouchMode(true);
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
}