package com.gaurav.krushimitra;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.tensorflow.lite.Interpreter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RequestQueue requestQueue;
    TextView temperature, humidity, task1, task2, task3, task4, definition, stageName, weekNumber;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    String enteredDate = "";
    LinearLayout l1, l2, l3, l4;
    ImageView i1, i2, i3, i4;
    double currentTemp;
    double currentHumid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        //String eemail=firebaseUser.getEmail();
        temperature = findViewById(R.id.temperature);
        humidity = findViewById(R.id.humidity);
        task1 = findViewById(R.id.task1);
        task2 = findViewById(R.id.task2);
        task3 = findViewById(R.id.task3);
        task4 = findViewById(R.id.task4);
        definition = findViewById(R.id.def_tv);
        stageName = findViewById(R.id.stage_tv);
        weekNumber = findViewById(R.id.week_number);

        l1 = findViewById(R.id.lay1);
        l2 = findViewById(R.id.lay2);
        l3 = findViewById(R.id.lay3);
        l4 = findViewById(R.id.lay4);

        i1 = findViewById(R.id.i1);
        i2 = findViewById(R.id.i2);
        i3 = findViewById(R.id.i3);
        i4 = findViewById(R.id.i4);


        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((i1.getDrawable()).getConstantState() == (ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_circle)).getConstantState())
                    i1.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_line_circle));
                else if ((i1.getDrawable()).getConstantState() == (ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_line_circle)).getConstantState())
                    i1.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_circle));


            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((i2.getDrawable()).getConstantState() == (ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_circle)).getConstantState())
                    i2.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_line_circle));
                else if ((i2.getDrawable()).getConstantState() == (ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_line_circle)).getConstantState())
                    i2.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_circle));


            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((i3.getDrawable()).getConstantState() == (ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_circle)).getConstantState())
                    i3.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_line_circle));
                else if ((i3.getDrawable()).getConstantState() == (ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_line_circle)).getConstantState())
                    i3.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_circle));

            }
        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if((i4.getTag())== R.drawable.ic_circle)
//                    i4.setTag(R.drawable.ic_line_circle);
//
//                if(((int)i4.getTag() ) == R.drawable.ic_line_circle)
//                    i4.setTag(R.drawable.ic_circle);

                if ((i4.getDrawable()).getConstantState() == (ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_circle)).getConstantState())
                    i4.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_line_circle));
                else if ((i4.getDrawable()).getConstantState() == (ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_line_circle)).getConstantState())
                    i4.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_circle));

            }
        });

        SingletonClass singletonClass = new SingletonClass();


        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String finalDate = "";
        finalDate += (String.valueOf(day).length() == 1 ? "0" + String.valueOf(day) : String.valueOf(day)) + (String.valueOf(month + 1).length() == 1 ? "0" + String.valueOf(month + 1) : String.valueOf(month + 1)) + String.valueOf(year);

        //CASE 1

        Log.e("FIREBASEUSER MAIL", firebaseUser.getEmail());
        firebaseFirestore = FirebaseFirestore.getInstance();

//
//        firebaseFirestore.collection("UserData")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful())
//                        {
//                            QuerySnapshot qsn=task.getResult();
//                            if (qsn != null) {
//                                List<DocumentSnapshot> lst=qsn.getDocuments();
//                                for (DocumentSnapshot d : lst)
//                                {
//                                    if(d.getString("email").equals(firebaseUser.getEmail()))
//                                    {
//                                        Log.e("CHECK", d.getString("email") + " " + firebaseAuth.getCurrentUser().getEmail());
//                                        enteredDate =d.getString("city");
//                                        Log.e("ENTERED DATE", enteredDate);
//                                        break;
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                });


        enteredDate = SingletonClass.getDate();
        String submitDate = "";
        submitDate += finalDate.charAt(0);
        submitDate += finalDate.charAt(1);
        submitDate += '-';
        submitDate += finalDate.charAt(2);
        submitDate += finalDate.charAt(3);
        submitDate += '-';
        submitDate += finalDate.charAt(4);
        submitDate += finalDate.charAt(5);
        submitDate += finalDate.charAt(6);
        submitDate += finalDate.charAt(7);

//        Query documentSnapshot=firebaseFirestore.collection("UserData").whereEqualTo("email",firebaseUser.getEmail());
        Log.e("ENETERD DATE SIZE", String.valueOf(enteredDate.length()));
        String d1 = String.valueOf(finalDate.charAt(0)) + String.valueOf(finalDate.charAt(1));
        String d2 = String.valueOf(enteredDate.charAt(0)) + String.valueOf(enteredDate.charAt(1));

        Log.e("SINGLETON CLASS DATE", enteredDate);
        Log.e("CURRENT DATE", finalDate);

        String m2 = String.valueOf(enteredDate.charAt(2)) + String.valueOf(enteredDate.charAt(3));
        String m1 = String.valueOf(finalDate.charAt(2)) + String.valueOf(finalDate.charAt(3));

        String y2 = String.valueOf(enteredDate.charAt(4)) + String.valueOf(enteredDate.charAt(5)) + String.valueOf(enteredDate.charAt(6)) + String.valueOf(enteredDate.charAt(7));
        String y1 = String.valueOf(finalDate.charAt(4)) + String.valueOf(finalDate.charAt(5)) + String.valueOf(finalDate.charAt(6)) + String.valueOf(finalDate.charAt(7));

        int week = 0;
        Date date = new Date(Integer.parseInt(y1), Integer.parseInt(m1) - 1, Integer.parseInt(d1));

        Calendar c1 = Calendar.getInstance();
//        c1.set(Integer.parseInt(y1),Integer.parseInt(m1)-1,Integer.parseInt(d1),0,0);
        c1.setTime(date);
        Log.e("YEAR 1", y1);
        Log.e("MONTH 1", m1);
        Log.e("DAY 1", d1);

        Calendar c2 = Calendar.getInstance();
//        c2.set(Integer.parseInt(y2),Integer.parseInt(m2)-1,Integer.parseInt(d2),0,0);
        Date date1 = new Date(Integer.parseInt(y2), Integer.parseInt(m2) - 1, Integer.parseInt(d2));
        c2.setTime(date1);

        long remainingDays = Math.round((float) (c1.getTimeInMillis() - c2.getTimeInMillis()) / (24 * 60 * 60 * 1000));

        Log.e("CAlendar 1", String.valueOf(c1.getTime()));
        Log.e("CAlendar 2", String.valueOf(c2.getTime()));
        int daysBetween = (int) remainingDays;
        Log.e("DAYS BETWEEN ", String.valueOf(daysBetween));
        week = 1 + (daysBetween / 7);
        Log.e("WEEK BECOMES", String.valueOf(week));

        String currentWeek = "Week ";
        currentWeek += (String.valueOf(week));

        weekNumber.setText(currentWeek);
        final String finalCurrentWeek = currentWeek;
        Submit(submitDate,finalCurrentWeek);
        String temp = "";
        if (week >= 1 && week <= 3) {
            stageName.setText("Pre-Seeding");
            temp = "Pre-seeding stage";
        } else if (week > 3 && week <= 6) {
            stageName.setText("Sowing");
            temp = "Sowing stage";
        } else if (week > 6 && week <= 11) {
            stageName.setText("Vegetative");
            temp = "Vegetative Stage";
        } else if (week > 11 && week <= 13) {
            stageName.setText("Pollination/Flowering");
            temp = "Pollination Stage";
        } else if (week > 13 && week <= 21) {
            stageName.setText("Fruiting");
            temp = "Fruiting Stage";
        } else if (week > 21 && week <= 24) {
            stageName.setText("Harvesting");
            temp = "Harvesting Stage";
        }

        String currentTask="Task ";

        Log.e("CURRENT TEMP VAL IS  ", String.valueOf(currentTemp));
        if(currentTemp>21 && currentTemp<=27)
        currentTask+="C";
        else if(currentTemp<=21)
            currentTask+="B";
        else if(currentTemp>27)
            currentTask+="A";

        firebaseFirestore.collection(currentTask)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : lst) {
                                if (d.getId().equals(finalCurrentWeek)) {
                                    task1.setText(d.getString("Task 1"));
                                    task2.setText(d.getString("Task 2"));
                                    task3.setText(d.getString("Task 3"));
                                    task4.setText(d.getString("Task 4"));
                                }
                            }

                        }
                    }
                });

        final String currentStage = "Sowing stage";

        final String finalTemp = temp;
        firebaseFirestore.collection("Definition")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : lst) {
                                Log.e("Enterd inside", "Inside DOcumetn");
                                if (d.getId().equals(finalTemp)) {
                                    definition.setText(d.getString("Definition"));
                                    Log.e("DATA IS:---   ", d.getString("Definition"));
                                }

                            }
                        }
                    }
                });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public static long daysBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
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

    private void Submit(String data, final String finalCurrentWeek) {
//        final String saveData=data;
        try {
//            String url="https://samp-model.herokuapp.com/api";
            String url = "https://weather-lstm-app.herokuapp.com/apikey";
            try {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }


            JSONObject postData = new JSONObject();
            try {
                postData.put("time", data);
                postData.put("region", "Pune");
            } catch (JSONException e) {
                e.printStackTrace();
            }




//            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, postData, new Response.Listener<JSONArray>() {
//                @Override
//                public void onResponse(JSONArray response) {
//                    Log.e("RETURNED MODEL VAL  ", response.toString());
////                        String temp = response.getString("result");
//                    char c = 0xB0;
//                    String ss = " ";
//                    ss += c;
//                    temperature.setText(ss + "C");
////                        humidity.setText(String.valueOf(Integer.parseInt(temp) + 1));
////                    precipitation.setText(String.valueOf(Integer.parseInt(temp)-(2*2)));
////                        Log.d("TEMPERATURE RESPONSE", temp);
//                }
//            }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                }
//            });
//            requestQueue.add(jsonArrayRequest);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.e("RETURNED MODEL VAL  ", response.toString());
                        JSONArray jsonElements=response.getJSONArray("ans");
                        double temp=jsonElements.getJSONArray(0).getDouble(1);
                        String tempString=(String) String.format("%.2f",temp);
                        temp=Double.parseDouble(tempString);
                        currentTemp=temp;

                        double temp1=jsonElements.getJSONArray(0).getDouble(2);
                        String tempString1=(String) String.format("%.2f",temp1);
                        temp1=Double.parseDouble(tempString1);
                        currentHumid=temp1;

                        Log.e("TEMPERTURE", String.valueOf(temp));
                        Log.e("HUMIDITYYY", String.valueOf(temp1));
                        char c = 0xB0;
                        String ss = " ";
                        ss += c;
                        temperature.setText( temp + ss + "C");
                        humidity.setText(String.valueOf(temp1 + "%"));
//                    precipitation.setText(String.valueOf(Integer.parseInt(temp)-(2*2)));
//                        Log.d("TEMPERATURE RESPONSE", temp);


                        String currentTask="Task ";

                        Log.e("CURRENT TEMP VAL IS  ", String.valueOf(temp));
                        if(temp>21 && temp<=27)
                            currentTask+="C";
                        else if(temp<=21)
                            currentTask+="B";
                        else if(temp>27)
                            currentTask+="A";

                        firebaseFirestore.collection(currentTask)
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        if (!queryDocumentSnapshots.isEmpty()) {
                                            List<DocumentSnapshot> lst = queryDocumentSnapshots.getDocuments();
                                            for (DocumentSnapshot d : lst) {
                                                if (d.getId().equals(finalCurrentWeek)) {
                                                    task1.setText(d.getString("Task 1"));
                                                    task2.setText(d.getString("Task 2"));
                                                    task3.setText(d.getString("Task 3"));
                                                    task4.setText(d.getString("Task 4"));
                                                }
                                            }

                                        }
                                    }
                                });



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
        } catch (Exception e) {
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

    }
}