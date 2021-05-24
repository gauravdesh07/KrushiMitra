package com.gaurav.krushimitra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ExpenditureActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView t1,t2,t3;
    EditText e1,e2;
    Button button;
    ImageView i1,i2;
    int val=-1;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        e1=findViewById(R.id.enter_amount);

        button=findViewById(R.id.okayButton);

        t1=findViewById(R.id.tv_amount_spent);
        t2=findViewById(R.id.tv_amount_gained);
        t3=findViewById(R.id.tv_profit);

        i1=findViewById(R.id.iv1);
        i2=findViewById(R.id.iv2);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();




        firebaseFirestore.collection("UserData")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                          @Override
                                          public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                              List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                                              for (DocumentSnapshot d : lst) {

                                                  if (d.getString("email").equals(firebaseAuth.getCurrentUser().getEmail())) {

                                                      long amount_spent = (long) d.get("amount_spent");
                                                      long amount_gained = (long) d.get("amount_gained");
                                                      t1.setText(String.valueOf(amount_spent));
                                                      t2.setText(String.valueOf(amount_gained));
                                                  }
                                              }
                                          }
                                      });


        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val=1;
                Toast.makeText(ExpenditureActivity.this, "Add Selected", Toast.LENGTH_SHORT).show();
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val=2;
                Toast.makeText(ExpenditureActivity.this, "Deduct Selected", Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!e1.getText().toString().isEmpty()))
                {
                    final String s1=e1.getText().toString();

                    firebaseFirestore.collection("UserData")
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                    List<DocumentSnapshot> lst =queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        Log.e("MISSION OUTSODE  ", "onSuccess: ");
                                        Log.e("DB EMAIL  ", d.getString("email"));
                                        Log.e("FIREBASE EMAIL", String.valueOf(firebaseAuth.getCurrentUser().getEmail()));
                                        if(d.getString("email").equals(firebaseAuth.getCurrentUser().getEmail()))
                                        {
                                            Log.e("MISSION INSIDE ", "Success" );
                                            long amount_spent= (long) d.get("amount_spent");
                                            long amount_gained= (long) d.get("amount_gained");
                                            Log.e("AMOUTN GAINDE", String.valueOf(amount_gained));
                                            Log.e("AMOUTN SPENT", String.valueOf(amount_spent));
                                            if(val==2) {
                                                amount_spent += Integer.parseInt(s1);
                                                firebaseFirestore.collection("UserData").document(d.getId()).update("amount_spent", amount_spent)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(ExpenditureActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }
                                            else if(val==1) {
                                                amount_gained += Integer.parseInt(s1);
                                                firebaseFirestore.collection("UserData").document(d.getId()).update("amount_gained", amount_gained)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(ExpenditureActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                            }
                                            if(amount_gained>amount_spent)
                                                t3.setText("Profit");
                                            else if(amount_gained==amount_spent)
                                                t3.setText("No Profit No Loss");
                                            else
                                                t3.setText("Loss");
                                            t1.setText(String.valueOf(amount_spent));
                                            t2.setText(String.valueOf(amount_gained));
                                            break;
                                        }
                                    }
                                }
                            });


                }
                else
                    Toast.makeText(ExpenditureActivity.this, "Enter Details Correctly", Toast.LENGTH_SHORT).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.cul_tips:
                startActivity(new Intent(this, CultipsActivity.class));
                break;
            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.nav_diagnose:
                startActivity(new Intent(this, DiagnosisActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}