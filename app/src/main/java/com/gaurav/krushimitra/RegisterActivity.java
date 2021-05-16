package com.gaurav.krushimitra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

public class RegisterActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextInputEditText name,email,password,mobile;
    Button register;
    CollectionReference UserData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseApp.initializeApp(this);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        name=findViewById(R.id.input_name);
        email=findViewById(R.id.input_email);
        password=findViewById(R.id.input_password);
        mobile=findViewById(R.id.input_mobile);

        register=findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String names=name.getText().toString();
                final String emails=email.getText().toString();
                final String passwords=password.getText().toString();
                final String mobiles=mobile.getText().toString();
                if(!names.isEmpty() && !emails.isEmpty() && !passwords.isEmpty() && !mobiles.isEmpty())
                {
                    if(passwords.length()<6)
                        Toast.makeText(RegisterActivity.this, "Minimum length of password must be 6 characters", Toast.LENGTH_SHORT).show();
                    else{
                        firebaseAuth.createUserWithEmailAndPassword(emails,passwords)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        UserData=firebaseFirestore.collection("UserData");
                                        User user=new User(names,emails,passwords,mobiles);
                                        UserData.add(user)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {

                                                        Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(RegisterActivity.this, "Some Error Occured. Please Try Again.", Toast.LENGTH_SHORT).show();
                                                    }
                                                });;
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this, "Some Error Occured. Please Try Again.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this, "All the fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}