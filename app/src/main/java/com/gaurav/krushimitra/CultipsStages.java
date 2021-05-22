package com.gaurav.krushimitra;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.io.Serializable;
import java.util.List;

public class CultipsStages extends Fragment implements Serializable {
    LinearLayout l1,l2,l3,l4,l5,l6;
    TextView t1,t2,t3,t4,t5,t6;
    FirebaseFirestore firebaseFirestore;
    String str1,str2;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cultips_stages,container,false);
    }

    public void initialize()
    {
        t1=getActivity().findViewById(R.id.t1);
        t2=getActivity().findViewById(R.id.t2);
        t3=getActivity().findViewById(R.id.t3);
        t4=getActivity().findViewById(R.id.t4);
        t5=getActivity().findViewById(R.id.t5);
        t6=getActivity().findViewById(R.id.t6);

        l1=getActivity().findViewById(R.id.ll1);
        l2=getActivity().findViewById(R.id.ll2);
        l3=getActivity().findViewById(R.id.ll3);
        l4=getActivity().findViewById(R.id.ll4);
        l5=getActivity().findViewById(R.id.ll5);
        l6=getActivity().findViewById(R.id.ll6);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseFirestore=FirebaseFirestore.getInstance();

        initialize();

        firebaseFirestore.collection("CultivationStages")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : lst)
                            {
                                if(d.getId().equals("Stage-1"))
                                {
                                    t1.setText(d.getString("Name"));
                                }
                                if(d.getId().equals("Stage-2"))
                                {
                                    t2.setText(d.getString("Name"));
                                }
                                if(d.getId().equals("Stage-3"))
                                {
                                    t3.setText(d.getString("Name"));
                                }
                                if(d.getId().equals("Stage-4"))
                                {
                                    t4.setText(d.getString("Name"));
                                }
                                if(d.getId().equals("Stage-5"))
                                {
                                    t5.setText(d.getString("Name"));
                                }
                                if(d.getId().equals("Stage-6"))
                                {
                                    t6.setText(d.getString("Name"));
                                }
                            }
                        }
                    }
                });
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("CultivationStages").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d: lst)
                                {
                                    if(d.getId().equals("Stage-1"))
                                    {

                                        str1=d.getString("Name");
                                        str2 =d.getString("Description");
                                        WeekUser weekUser=new WeekUser(str1,str2);
                                        Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                        intent.putExtra("Value", (Serializable) weekUser);
                                        startActivity(intent);
                                    }
                                }
                            }
                        });
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("CultivationStages").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d: lst)
                                {
                                    if(d.getId().equals("Stage-2"))
                                    {

                                        str1=d.getString("Name");
                                        str2 =d.getString("Description");
                                        WeekUser weekUser=new WeekUser(str1,str2);
                                        Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                        intent.putExtra("Value", (Serializable) weekUser);
                                        startActivity(intent);
                                    }
                                }
                            }
                        });
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("CultivationStages").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d: lst)
                                {
                                    if(d.getId().equals("Stage-3"))
                                    {

                                        str1=d.getString("Name");
                                        str2 =d.getString("Description");
                                        WeekUser weekUser=new WeekUser(str1,str2);
                                        Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                        intent.putExtra("Value", (Serializable) weekUser);
                                        startActivity(intent);
                                    }
                                }
                            }
                        });
            }
        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("CultivationStages").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d: lst)
                                {
                                    if(d.getId().equals("Stage-4"))
                                    {

                                        str1=d.getString("Name");
                                        str2 =d.getString("Description");
                                        WeekUser weekUser=new WeekUser(str1,str2);
                                        Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                        intent.putExtra("Value", (Serializable) weekUser);
                                        startActivity(intent);
                                    }
                                }
                            }
                        });
            }
        });

        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("CultivationStages").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d: lst)
                                {
                                    if(d.getId().equals("Stage-5"))
                                    {

                                        str1=d.getString("Name");
                                        str2 =d.getString("Description");
                                        WeekUser weekUser=new WeekUser(str1,str2);
                                        Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                        intent.putExtra("Value", (Serializable) weekUser);
                                        startActivity(intent);
                                    }
                                }
                            }
                        });
            }
        });

        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("CultivationStages").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                for(DocumentSnapshot d: lst)
                                {
                                    if(d.getId().equals("Stage-6"))
                                    {

                                        str1=d.getString("Name");
                                        str2 =d.getString("Description");
                                        WeekUser weekUser=new WeekUser(str1,str2);
                                        Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                        intent.putExtra("Value", (Serializable) weekUser);
                                        startActivity(intent);
                                    }
                                }
                            }
                        });
            }
        });
    }
}
