package com.gaurav.krushimitra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.Calendar;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    String city="",name="";
    TextView t1,t2,t3;
    Button btn;
    Toolbar toolbar=null;
    EditText editText;
    DatePickerDialog datepicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        t1=findViewById(R.id.name);
        t2=findViewById(R.id.email);
        t3=findViewById(R.id.city);
        editText=findViewById(R.id.date);
//        btn=findViewById(R.id.btn);

        final String targetEmail=firebaseAuth.getCurrentUser().getEmail();

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(ProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                                editText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);



                                db.collection("UserData").get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                                                if (!queryDocumentSnapshots.isEmpty()) {
                                                    List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                                                    for (DocumentSnapshot d : lst) {

                                                        if (d.getString("email").equals(targetEmail)) {
                                                            Log.e("CHECK",d.getString("email")+" "+targetEmail);
//                                                            final Calendar calendar = Calendar.getInstance();
//                                                            int day = calendar.get(Calendar.DAY_OF_MONTH);
//                                                            int month = calendar.get(Calendar.MONTH);
//                                                            int year = calendar.get(Calendar.YEAR);
                                                            String finalDate="";
                                                            finalDate+=(String.valueOf(dayOfMonth).length()==1 ? "0"+ String.valueOf(dayOfMonth):String.valueOf(dayOfMonth))+(String.valueOf(monthOfYear+1).length()==1 ? "0" + String.valueOf(monthOfYear+1):String.valueOf(monthOfYear+1))+String.valueOf(year);
                                                            Log.e("FINALDATE",finalDate);
                                                            final String finalDate1 = finalDate;
                                                            db.collection("UserData").document(d.getId()).update("date", finalDate)
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            try {
                                                                                SingletonClass.class.getDeclaredConstructor();
                                                                            } catch (NoSuchMethodException e) {
                                                                                e.printStackTrace();
                                                                            }
                                                                            SingletonClass.setDate(finalDate1);
                                                                            Toast.makeText(ProfileActivity.this, "Date updated Successfully !!", Toast.LENGTH_SHORT).show();
                                                                            editText.clearFocus();
                                                                        }
                                                                    }
                                                                    ).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(ProfileActivity.this, "Some Error Occured ! Try Again !!", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                            break;
                                                        }
                                                    }
                                                }


                                            }
                                        });
                            }
                        }, year, month, day);
                datepicker.show();



            }
        });

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
                                    String date=d.getString("date");
                                    Log.e("DATTA",city+name);
                                    t1.setText(name);
                                    editText.setText(date);
                                    t3.setText(city);
                                    break;
                                }
                            }
                        }
                    }
                });



//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
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
            case R.id.nav_expenditure:
                startActivity(new Intent(this,ExpenditureActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}