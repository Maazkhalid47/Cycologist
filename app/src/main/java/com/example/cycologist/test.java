package com.example.cycologist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class test extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cycologist-default-rtdb.firebaseio.com/");
    String[] questions = {"0", "Are you consuming any substance/medications?", "do you find it difficult to stay aware or attentive?",
            "Do you have any medical conditions?", "have you been experiencing any anxiety symptoms severe enough to warrant clinical attention?"
            , "do you find it difficult to stay aware or attentive?", "Have you recently experienced a decline in any of the following cognitive domains?:complex attention,executive function,learning and memory,language,motor skills?",
            "Do you experience intense fear or discomfort that reaches peak within minutes?", "have you ever had repeated significant panic attacks?", "Do you feel irritated by and avoid any two of the following?:public transportations, enclosed spaces,being in a crowd, standing in a line or being outside home alone"
            , "Do you get too anxious while being away from a person", "Do you avoid people because you are worried that they might judge you?",
            "Are you afraid of exposure to any object? e.g. spiders, injections, road accidents",
            "Are you worried about catching a virus or falling sick more than usual?", "Do you have  has a significant focus on physical symptoms such as pain, weakness or shortness of breath all the time?"
            , "Do you have a persistent fear that you have a serious or life-threatening illness despite few or no symptoms?", "Do you experience hallucinations?"
            , "Do you find it difficult to get rid of personal items even when they are useless and keep them stacked up to the point that they even disturb your living?"
            , "Do you spend a lot of time worrying about flaws in your appearance?", "Do you experience repeated, persistent and unwanted thoughts, urges or images that are intrusive and cause distress or anxiety?"
            , "Do you feel any of the following?:Fear of dirt or germs, Fear of contamination, A need for symmetry, order and precision",
            "Do you experience excessive worry about several events or situations"};


    String[] disorders = {"", "substance intoxication Delirium/substance withdrawal delirium/Medication induced delirium", "Medication/substance induced anxiety disorder"
            , "Substance intoxication", "Delirium due to another medical condition", "Major/mild neurocognitive disorder", "Anxiety disorder due to medical condition"
            , "Panic attack or limited symptom attack", "Panic disorder", "Agoraphobia", "Separation Anxiety Disorder", "Social Anxiety Disorder",
            "Object Specific Phobia", "Somatic Symptom Disorder", "Psychotic Disorder", "Illness Anxiety Disorder", "Hoarding Disorder", "Body dysmorphic Disorder"
            , "OCD", "Generalized Anxiety Disorder"};
    TextView question, disordertxt;
    Button btnyes, btnno, quit;
    int yes = 0, no = 0, anchor = 0, yesvalue, novalue;
    Dialog dialog, dialog2;
    String disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        question = findViewById(R.id.question);
        btnyes = findViewById(R.id.btnyes);
        btnno = findViewById(R.id.btnno);
        dialog = new Dialog(test.this);
        dialog2 = new Dialog(test.this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        dialog2.setContentView(R.layout.custom_dialog_layout_passed);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog2.setCancelable(false);
        dialog2.setCanceledOnTouchOutside(false);
        disordertxt = dialog.findViewById(R.id.txtdisorder);
        Button endtest = dialog.findViewById(R.id.btnendtest);
        Button endtestpassed = dialog2.findViewById(R.id.btnendtestpassed);
        Intent it = getIntent();
        String username = it.getStringExtra("uname");
        quit = findViewById(R.id.quitbtn);
        endtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesvalue = yes;
                novalue = no;
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("users").child(username).child("yesvalue").setValue(yesvalue);
                        databaseReference.child("users").child(username).child("novalue").setValue(novalue);
                        databaseReference.child("users").child(username).child("disease").setValue(disease);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                onBackPressed();
                finish();
            }
        });
        endtestpassed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesvalue = yes;
                novalue = no;
                databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("users").child(username).child("yesvalue").setValue(yesvalue);
                        databaseReference.child("users").child(username).child("novalue").setValue(novalue);
                        databaseReference.child("users").child(username).child("disease").setValue(disease);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                onBackPressed();
                finish();
            }
        });


        question.setText(questions[1]);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yes++;
                checker();
            }
        });

        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no++;
                checker();
            }
        });
    }

    private void checker() {
        if (yes == 1 && no == 0) {
            question.setText(questions[2]);
        }
        if (yes == 2 && no == 0) {
            disordertxt.setText(disorders[1]);
            disease=disorders[1];
            dialog.show();
        }
        if (yes == 1 && no == 1 && anchor == 0) {
            question.setText(questions[4]);
        }
        if (yes == 2 && no == 1 && anchor == 0) {
            disordertxt.setText(disorders[2]);
            disease=disorders[2];
            dialog.show();
        }
        if (yes == 1 && no == 2 && anchor == 1) {
            question.setText(questions[6]);
        }
        if (yes == 1 && no == 2 && anchor == 0) {
            disordertxt.setText(disorders[3]);
            disease=disorders[3];
            dialog.show();
        }
        if (yes == 0 && no == 1) {
            anchor = 1;
            question.setText(questions[3]);
        }
        if (yes == 1 && no == 1 && anchor == 1) {
            question.setText(questions[5]);
        }
        if (yes == 2 && no == 1 && anchor == 1) {
            disordertxt.setText(disorders[4]);
            disease=disorders[4];
            dialog.show();
        }
        if (yes == 2 && no == 2 && anchor == 1) {
            disordertxt.setText(disorders[5]);
            disease=disorders[5];
            dialog.show();
        }
        if (yes == 1 && no == 3 && anchor == 1) {
            disordertxt.setText(disorders[6]);
            disease=disorders[6];
            dialog.show();
        }
        if (yes == 0 && no == 2 && anchor == 1) {
            anchor = 2;
            question.setText(questions[7]);
        }
        if (yes == 1 && no == 2 && anchor == 2) {
            disordertxt.setText(disorders[7]);
            disease=disorders[7];
            dialog.show();
        }
        if (yes == 0 && no == 3 && anchor == 2) {
            question.setText(questions[8]);
        }///////////////////////////////////////////////////////////////////////
        if (yes == 1 && no == 3 && anchor == 2) {
            disordertxt.setText(disorders[8]);
            disease=disorders[8];
            dialog.show();
        }
        if (yes == 0 && no == 4 && anchor == 2) {
            question.setText(questions[9]);
        }
        if (yes == 1 && no == 4 && anchor == 2) {
            disordertxt.setText(disorders[9]);
            disease=disorders[9];
            dialog.show();
        }
        if (yes == 0 && no == 5 && anchor == 2) {
            question.setText(questions[10]);
        }
        if (yes == 1 && no == 5 && anchor == 2) {
            disordertxt.setText(disorders[10]);
            disease=disorders[10];
            dialog.show();
        }
        if (yes == 0 && no == 6 && anchor == 2) {
            question.setText(questions[11]);
        }
        if (yes == 1 && no == 6 && anchor == 2) {
            disordertxt.setText(disorders[11]);
            disease=disorders[11];
            dialog.show();
        }
        if (yes == 0 && no == 7 && anchor == 2) {
            question.setText(questions[12]);
        }
        if (yes == 1 && no == 7 && anchor == 2) {
            disordertxt.setText(disorders[12]);
            disease=disorders[12];
            dialog.show();
        }
        if (yes == 0 && no == 8 && anchor == 2) {
            question.setText(questions[13]);
        }
        if (yes == 1 && no == 8 && anchor == 2) {
            question.setText(questions[14]);
        }
        if (yes == 2 && no == 8 && anchor == 2) {
            disordertxt.setText(disorders[13]);
            disease=disorders[13];
            dialog.show();
        }
        if (yes == 1 && no == 9 && anchor == 2) {
            question.setText(questions[15]);
        }
        if (yes == 2 && no == 9 && anchor == 2) {
            question.setText(questions[16]);
        }
        if (yes == 2 && no == 10 && anchor == 2) {
            disordertxt.setText(disorders[15]);
            disease=disorders[15];
            dialog.show();
        }
        if (yes == 3 && no == 9 && anchor == 2) {
            disordertxt.setText(disorders[14]);
            disease=disorders[14];
            dialog.show();
        }
        if (yes == 0 && no == 9 && anchor == 2) {
            anchor++;
            question.setText(questions[17]);
        }
        if (yes == 1 && no == 9 && anchor == 3) {
            disordertxt.setText(disorders[16]);
            disease=disorders[16];
            dialog.show();
        }
        if (yes == 0 && no == 10 && anchor == 3) {
            question.setText(questions[18]);
        }
        if (yes == 1 && no == 10 && anchor == 3) {
            disordertxt.setText(disorders[17]);
            disease=disorders[17];
            dialog.show();
        }
        if (yes == 0 && no == 11 && anchor == 3) {
            question.setText(questions[19]);
        }
        if (yes == 1 && no == 11 && anchor == 3) {
            question.setText(questions[20]);
        }
        if (yes == 2 && no == 11 && anchor == 3) {
            disordertxt.setText(disorders[18]);
            disease=disorders[18];
            dialog.show();
        }
        if (yes == 1 && no == 12 && anchor == 3) {
            question.setText(questions[21]);
        }
        if (yes == 2 && no == 12 && anchor == 3) {

            disordertxt.setText(disorders[19]);
            disease=disorders[19];
            dialog.show();
        }
        if (yes == 0 && no == 12 && anchor == 3) {
            anchor++;
            dialog2.show();

        }
        if (yes == 1 && no == 13 && anchor == 3) {
            anchor++;
            dialog2.show();

        }
    }
}