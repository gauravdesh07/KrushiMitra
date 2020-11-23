package com.gaurav.krushimitra;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.List;

public class CultipsWeeks extends Fragment implements Serializable {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cultips_weeks,container,false);
    }
    FirebaseFirestore firebaseFirestore;
    TextView t1,t2,t3,t4,t5;
    @Override
    public void onStart() {
        super.onStart();
        firebaseFirestore=FirebaseFirestore.getInstance();
        t1=getActivity().findViewById(R.id.week1);
        t2=getActivity().findViewById(R.id.week2);
        t3=getActivity().findViewById(R.id.week3);
        t4=getActivity().findViewById(R.id.week4);
        t5=getActivity().findViewById(R.id.week5);

        firebaseFirestore.collection("CultivationWeeks").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : lst)
                            {
                                if(d.getId().equals("Week 1"))
                                {
                                    String s1=d.getString("Title");
                                    t1.setText(s1);
                                    String s2=d.getString("Description");
                                }
                            }
                        }
                    }
                });

        firebaseFirestore.collection("CultivationWeeks").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : lst)
                            {
                                if(d.getId().equals("Week 2"))
                                {
                                    String s1=d.getString("Title");
                                    t2.setText(s1);
                                    String s2=d.getString("Description");
                                }
                            }
                        }
                    }
                });

        firebaseFirestore.collection("CultivationWeeks").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : lst)
                            {
                                if(d.getId().equals("Week 3"))
                                {
                                    String s1=d.getString("Title");
                                    t3.setText(s1);
                                    String s2=d.getString("Description");
                                }
                            }
                        }
                    }
                });

        firebaseFirestore.collection("CultivationWeeks").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : lst)
                            {
                                if(d.getId().equals("Week 4"))
                                {
                                    String s1=d.getString("Title");
                                    t4.setText(s1);
                                    String s2=d.getString("Description");
                                }
                            }
                        }
                    }
                });

        firebaseFirestore.collection("CultivationWeeks").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : lst)
                            {
                                if(d.getId().equals("Week 5"))
                                {
                                    String s1=d.getString("Title");
                                    t5.setText(s1);
                                    String s2=d.getString("Description");
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
