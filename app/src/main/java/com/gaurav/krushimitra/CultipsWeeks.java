package com.gaurav.krushimitra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22;
    LinearLayout l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22;
    static String str1,str2;
    @Override
    public void onStart() {
        super.onStart();
        firebaseFirestore=FirebaseFirestore.getInstance();
        t1=getActivity().findViewById(R.id.week1);
        t2=getActivity().findViewById(R.id.week2);
        t3=getActivity().findViewById(R.id.week3);
        t4=getActivity().findViewById(R.id.week4);
        t5=getActivity().findViewById(R.id.week5);
        t6=getActivity().findViewById(R.id.week6);
        t7=getActivity().findViewById(R.id.week7);
        t8=getActivity().findViewById(R.id.week8);
        t9=getActivity().findViewById(R.id.week9);
        t10=getActivity().findViewById(R.id.week10);
        t11=getActivity().findViewById(R.id.week11);
        t12=getActivity().findViewById(R.id.week12);
        t13=getActivity().findViewById(R.id.week13);
        t14=getActivity().findViewById(R.id.week14);
        t15=getActivity().findViewById(R.id.week15);
        t16=getActivity().findViewById(R.id.week16);
        t17=getActivity().findViewById(R.id.week17);
        t18=getActivity().findViewById(R.id.week18);
        t19=getActivity().findViewById(R.id.week19);
        t20=getActivity().findViewById(R.id.week20);
        t21=getActivity().findViewById(R.id.week21);
        t22=getActivity().findViewById(R.id.week22);

        l1=getActivity().findViewById(R.id.l1);
        l2=getActivity().findViewById(R.id.l2);
        l3=getActivity().findViewById(R.id.l3);
        l4=getActivity().findViewById(R.id.l4);
        l5=getActivity().findViewById(R.id.l5);
        l6=getActivity().findViewById(R.id.l6);
        l7=getActivity().findViewById(R.id.l7);
        l8=getActivity().findViewById(R.id.l8);
        l9=getActivity().findViewById(R.id.l9);
        l10=getActivity().findViewById(R.id.l10);
        l11=getActivity().findViewById(R.id.l11);
        l12=getActivity().findViewById(R.id.l12);
        l13=getActivity().findViewById(R.id.l13);
        l14=getActivity().findViewById(R.id.l14);
        l15=getActivity().findViewById(R.id.l15);
        l16=getActivity().findViewById(R.id.l16);
        l17=getActivity().findViewById(R.id.l17);
        l18=getActivity().findViewById(R.id.l18);
        l19=getActivity().findViewById(R.id.l19);
        l20=getActivity().findViewById(R.id.l20);
        l21=getActivity().findViewById(R.id.l21);
        l22=getActivity().findViewById(R.id.l22);

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
                                if(d.getId().equals("Week 6"))
                                {
                                    String s1=d.getString("Title");
                                    t6.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 7"))
                                {
                                    String s1=d.getString("Title");
                                    t7.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 8"))
                                {
                                    String s1=d.getString("Title");
                                    t8.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 9"))
                                {
                                    String s1=d.getString("Title");
                                    t9.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 10"))
                                {
                                    String s1=d.getString("Title");
                                    t10.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 11"))
                                {
                                    String s1=d.getString("Title");
                                    t11.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 12"))
                                {
                                    String s1=d.getString("Title");
                                    t12.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 13"))
                                {
                                    String s1=d.getString("Title");
                                    t13.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 14"))
                                {
                                    String s1=d.getString("Title");
                                    t14.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 15"))
                                {
                                    String s1=d.getString("Title");
                                    t15.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 16"))
                                {
                                    String s1=d.getString("Title");
                                    t16.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 17"))
                                {
                                    String s1=d.getString("Title");
                                    t17.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 18"))
                                {
                                    String s1=d.getString("Title");
                                    t18.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 19"))
                                {
                                    String s1=d.getString("Title");
                                    t19.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 20"))
                                {
                                    String s1=d.getString("Title");
                                    t20.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 21"))
                                {
                                    String s1=d.getString("Title");
                                    t21.setText(s1);
                                    String s2=d.getString("Description");
                                }
                                if(d.getId().equals("Week 22"))
                                {
                                    String s1=d.getString("Title");
                                    t22.setText(s1);
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


        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });


        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 2")) {
                                            str1 = d.getString("Title");
                                            str2 = d.getString("Description");
                                            WeekUser weekUser = new WeekUser(str1, str2);
                                            Intent intent = new Intent(getActivity(), CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 3")) {
                                            str1 = d.getString("Title");
                                            str2 = d.getString("Description");
                                            WeekUser weekUser = new WeekUser(str1, str2);
                                            Intent intent = new Intent(getActivity(), CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
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
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
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
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 6"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 7"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 8"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 9"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 10"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 11"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 12"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 13"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 14"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 15"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 16"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 17"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 18"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 19"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });


        l20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 20"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 21"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });

        l22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] s1 = new String[1];
                final String[] s2 = new String[1];
                Intent intent=new Intent(getActivity(),CultipsInfo.class);
                firebaseFirestore.collection("CultivationWeeks").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    List<DocumentSnapshot> lst=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d : lst)
                                    {
                                        if(d.getId().equals("Week 22"))
                                        {
                                            str1=d.getString("Title");
                                            str2 =d.getString("Description");
                                            WeekUser weekUser=new WeekUser(str1,str2);
                                            Intent intent=new Intent(getActivity(),CultipsInfo.class);
                                            intent.putExtra("Value", (Serializable) weekUser);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
