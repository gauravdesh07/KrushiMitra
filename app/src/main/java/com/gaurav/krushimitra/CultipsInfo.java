package com.gaurav.krushimitra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

public class CultipsInfo extends AppCompatActivity implements Serializable {

    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultips_info);
        Intent i=this.getIntent();
        title=this.findViewById(R.id.titleText);
        description=this.findViewById(R.id.descriptionText);

        WeekUser temp=(WeekUser) getIntent().getSerializableExtra("Value");

            String s1=temp.getTitle();
            title.setText(s1);
            String s2=temp.getDescription();
            s2=s2.replace("\\\\n","\n");
            description.setText("\n\n"+ s2 + "\n");


    }
}