package com.gaurav.krushimitra;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class SingletonClass {
    private static String date="28052021";

    public SingletonClass() {
        final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("UserData")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : lst)
                            {
                                if(d.getString("email").equals(firebaseAuth))
                                    date=d.getString("date");
                            }
                        }
                    }
                });
    }

    public static String getDate() {
        return date;
    }

    static {
        final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("UserData")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : lst)
                            {
                                if(d.getString("email").equals(firebaseAuth))
                                    date=d.getString("date");
                            }
                        }
                    }
                });


    }

    public static void setDate(String date) {
        SingletonClass.date = date;
    }

}
