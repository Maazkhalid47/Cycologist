package com.example.cycologist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class doctorrecommendation extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cycologist-default-rtdb.firebaseio.com");
    TextView name1, name2, name3, name4, name5, name6, name7, name8, name9, name10, name11, name12, name13, name14, name15, name16, name17, name18, name19, name20,
            name21, name22, name23, name24, name25, name26, name27, name28, name29, name30,
            spec1, spec2, spec3, spec4, spec5, spec6, spec7, spec8, spec9, spec10,
            spec11, spec12, spec13, spec14, spec15, spec16, spec17, spec18, spec19, spec20, spec21, spec22, spec23, spec24, spec25, spec26, spec27, spec28, spec29, spec30;
    String[] naamarray = new String[31];
    String[] specarray = new String[31];
    int doctor;
    Dialog dialog;
    TextView docname, docspec, docexp, docadd, docsat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorrecommendation);
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        name4 = findViewById(R.id.name4);
        name5 = findViewById(R.id.name5);
        name6 = findViewById(R.id.name6);
        name7 = findViewById(R.id.name7);
        name8 = findViewById(R.id.name8);
        name9 = findViewById(R.id.name9);
        name10 = findViewById(R.id.name10);
        name11 = findViewById(R.id.name11);
        name12 = findViewById(R.id.name12);
        name13 = findViewById(R.id.name13);
        name14 = findViewById(R.id.name14);
        name15 = findViewById(R.id.name15);
        name16 = findViewById(R.id.name16);
        name17 = findViewById(R.id.name17);
        name18 = findViewById(R.id.name18);
        name19 = findViewById(R.id.name19);
        name20 = findViewById(R.id.name20);
        name21 = findViewById(R.id.name21);
        name22 = findViewById(R.id.name22);
        name23 = findViewById(R.id.name23);
        name24 = findViewById(R.id.name24);
        name25 = findViewById(R.id.name25);
        name26 = findViewById(R.id.name26);
        name27 = findViewById(R.id.name27);
        name28 = findViewById(R.id.name28);
        name29 = findViewById(R.id.name29);
        name30 = findViewById(R.id.name30);
        spec1 = findViewById(R.id.spec1);
        spec2 = findViewById(R.id.spec2);
        spec3 = findViewById(R.id.spec3);
        spec4 = findViewById(R.id.spec4);
        spec5 = findViewById(R.id.spec5);
        spec6 = findViewById(R.id.spec6);
        spec7 = findViewById(R.id.spec7);
        spec8 = findViewById(R.id.spec8);
        spec9 = findViewById(R.id.spec9);
        spec10 = findViewById(R.id.spec10);
        spec11 = findViewById(R.id.spec11);
        spec12 = findViewById(R.id.spec12);
        spec13 = findViewById(R.id.spec13);
        spec14 = findViewById(R.id.spec14);
        spec15 = findViewById(R.id.spec15);
        spec16 = findViewById(R.id.spec16);
        spec17 = findViewById(R.id.spec17);
        spec18 = findViewById(R.id.spec18);
        spec19 = findViewById(R.id.spec19);
        spec20 = findViewById(R.id.spec20);
        spec21 = findViewById(R.id.spec21);
        spec22 = findViewById(R.id.spec22);
        spec23 = findViewById(R.id.spec23);
        spec24 = findViewById(R.id.spec24);
        spec25 = findViewById(R.id.spec25);
        spec26 = findViewById(R.id.spec26);
        spec27 = findViewById(R.id.spec27);
        spec28 = findViewById(R.id.spec28);
        spec29 = findViewById(R.id.spec29);
        spec30 = findViewById(R.id.spec30);
        dialog = new Dialog(doctorrecommendation.this);
        dialog.setContentView(R.layout.custom_dialog_doctor);
        docname = dialog.findViewById(R.id.docname);
        docspec = dialog.findViewById(R.id.docspec);
        docexp = dialog.findViewById(R.id.docexp);
        docadd = dialog.findViewById(R.id.docadd);
        docsat = dialog.findViewById(R.id.docsat);
        databaseReference.child("doctors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (int count = 0; count <= 30; count++) {
                    int sno = count;
                    sno++;
                    String name = snapshot.child(String.valueOf(sno)).child("name").getValue(String.class);
                    String spec = snapshot.child(String.valueOf(sno)).child("qualification").getValue(String.class);
                    naamarray[count] = name;
                    specarray[count] = spec;
                }
                name1.setText(naamarray[0]);
                spec1.setText(specarray[0]);
                name2.setText(naamarray[1]);
                spec2.setText(specarray[1]);
                name3.setText(naamarray[2]);
                spec3.setText(specarray[2]);
                name4.setText(naamarray[3]);
                spec4.setText(specarray[3]);
                name5.setText(naamarray[4]);
                spec5.setText(specarray[4]);
                name6.setText(naamarray[5]);
                spec6.setText(specarray[5]);
                name7.setText(naamarray[6]);
                spec7.setText(specarray[6]);
                name8.setText(naamarray[7]);
                spec8.setText(specarray[7]);
                name9.setText(naamarray[8]);
                spec9.setText(specarray[8]);
                name10.setText(naamarray[9]);
                spec10.setText(specarray[9]);
                name11.setText(naamarray[10]);
                spec11.setText(specarray[10]);
                name12.setText(naamarray[11]);
                spec12.setText(specarray[11]);
                name13.setText(naamarray[12]);
                spec13.setText(specarray[12]);
                name14.setText(naamarray[13]);
                spec14.setText(specarray[13]);
                name15.setText(naamarray[14]);
                spec15.setText(specarray[14]);
                name16.setText(naamarray[15]);
                spec16.setText(specarray[15]);
                name17.setText(naamarray[16]);
                spec17.setText(specarray[16]);
                name18.setText(naamarray[17]);
                spec18.setText(specarray[17]);
                name19.setText(naamarray[18]);
                spec19.setText(specarray[18]);
                name20.setText(naamarray[19]);
                spec20.setText(specarray[19]);
                name21.setText(naamarray[20]);
                spec21.setText(specarray[20]);
                name22.setText(naamarray[21]);
                spec22.setText(specarray[21]);
                name23.setText(naamarray[22]);
                spec23.setText(specarray[22]);
                name24.setText(naamarray[23]);
                spec24.setText(specarray[23]);
                name25.setText(naamarray[24]);
                spec25.setText(specarray[24]);
                name26.setText(naamarray[25]);
                spec26.setText(specarray[25]);
                name27.setText(naamarray[26]);
                spec27.setText(specarray[26]);
                name28.setText(naamarray[27]);
                spec28.setText(specarray[27]);
                name29.setText(naamarray[28]);
                spec29.setText(specarray[28]);
                name30.setText(naamarray[29]);
                spec30.setText(specarray[29]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void cardclicked(View view) {
        if (view.getId() == R.id.card1) {
            doctor = 1;
        } else if (view.getId() == R.id.card2) {
            doctor=2;
        } else if (view.getId() == R.id.card3) {
            doctor=3;
        } else if (view.getId() == R.id.card4) {
            doctor=4;//button3 action
        } else if (view.getId() == R.id.card5) {
            doctor=5;//button3 action
        } else if (view.getId() == R.id.card6) {
            doctor=6;//button3 action
        } else if (view.getId() == R.id.card7) {
            doctor=7;//button3 action
        } else if (view.getId() == R.id.card8) {
            doctor=8;//button3 action
        } else if (view.getId() == R.id.card9) {
            doctor=9;//button3 action
        } else if (view.getId() == R.id.card10) {
            doctor=10;//button3 action
        } else if (view.getId() == R.id.card11) {
            doctor=11;//button3 action
        } else if (view.getId() == R.id.card12) {
            doctor=12;//button3 action
        } else if (view.getId() == R.id.card13) {
            doctor=13;//button3 action
        } else if (view.getId() == R.id.card14) {
            doctor=14; //button3 action
        } else if (view.getId() == R.id.card15) {
            doctor=15;//button3 action
        } else if (view.getId() == R.id.card16) {
            doctor=16;//button3 action
        } else if (view.getId() == R.id.card17) {
            doctor=17;  //button3 action
        } else if (view.getId() == R.id.card18) {
            doctor=18;//button3 action
        } else if (view.getId() == R.id.card19) {
            doctor=19;//button3 action
        } else if (view.getId() == R.id.card20) {
            doctor=20;//button3 action
        } else if (view.getId() == R.id.card21) {
            doctor=21;//button3 action
        } else if (view.getId() == R.id.card22) {
            doctor=22;//button3 action
        } else if (view.getId() == R.id.card23) {
            doctor=23;  //button3 action
        } else if (view.getId() == R.id.card24) {
            doctor=24;//button3 action
        } else if (view.getId() == R.id.card25) {
            doctor=25;//button3 action
        } else if (view.getId() == R.id.card26) {
            doctor=26;//button3 action
        } else if (view.getId() == R.id.card27) {
            doctor=27;//button3 action
        } else if (view.getId() == R.id.card28) {
            doctor=28;//button3 action
        } else if (view.getId() == R.id.card29) {
            doctor=29; //button3 action
        } else if (view.getId() == R.id.card30) {
            doctor=30;//button3 action
        }
        databaseReference.child("doctors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child(String.valueOf(doctor)).child("name").getValue(String.class);
                String spec = snapshot.child(String.valueOf(doctor)).child("qualification").getValue(String.class);
                String exp = String.valueOf(snapshot.child(String.valueOf(doctor)).child("experience").getValue(int.class));
                String add = snapshot.child(String.valueOf(doctor)).child("address").getValue(String.class);
                String sat = snapshot.child(String.valueOf(doctor)).child("satisfaction").getValue(String.class);
                docname.setText(name);
                docadd.setText("Address: " + add);
                docspec.setText(spec);
                docsat.setText("Patient Satisfaction:" + sat);
                docexp.setText("Experience: " + exp+" years");
                dialog.show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}



