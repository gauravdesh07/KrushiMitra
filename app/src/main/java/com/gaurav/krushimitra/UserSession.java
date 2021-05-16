package com.gaurav.krushimitra;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserSession extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            // startActivity(new Intent(getBaseContext(),EventRegistrationActivity.class));


            Intent i = new Intent().setClass(getBaseContext(), MainActivity.class);
            try{
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            }
            catch (Exception e)
            {
                Log.e("ERROR", e.toString());
            }



// Launch the new activity and add the additional flags to the intent
            startActivity(i);
        }
    }
}
