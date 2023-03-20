package com.example.cycologist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cycologist-default-rtdb.firebaseio.com/");
    String gender = "male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText edtname = findViewById(R.id.edtname);
        final EditText edtfname = findViewById(R.id.edtfname);
        final EditText edtage = findViewById(R.id.edtage);
        final EditText edtemail = findViewById(R.id.edtemailS);
        final EditText edtphone = findViewById(R.id.edtphone);
        final EditText edtpswd = findViewById(R.id.edtpswdS);
        final EditText edtuser = findViewById(R.id.edtusernameS);
        RadioGroup rg = findViewById(R.id.genderRadioGroup);
        final Button signup = findViewById(R.id.btnsignup);
        final TextView logintxt = findViewById(R.id.logintxt);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbf = findViewById(R.id.female);
                if (rbf.isChecked()) {
                    gender = "female";
                    Toast.makeText(Signup.this, "female", Toast.LENGTH_SHORT).show();
                } else {
                    gender = "male";
                    Toast.makeText(Signup.this, "male", Toast.LENGTH_SHORT).show();

                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = edtname.getText().toString();
                final String fname = edtfname.getText().toString();
                final String uname = edtuser.getText().toString();
                final String age = edtage.getText().toString();
                final String phone = edtphone.getText().toString();
                final String email = edtemail.getText().toString();
                final String pswd = edtpswd.getText().toString();
                if (name.isEmpty() || fname.isEmpty() || uname.isEmpty() || age.isEmpty() || email.isEmpty() || pswd.isEmpty()) {
                    Toast.makeText(Signup.this, "Fill All Fields!", Toast.LENGTH_SHORT).show();
                } else if (!name.matches("[a-zA-Z]+")) {
                    edtname.requestFocus();
                    edtname.setError("please use alphabets only");
                } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+.+[a-z]+")) {
                    edtemail.requestFocus();
                    edtemail.setError("Invalid email address");
                } else if (!uname.matches("[a-zA-Z]+")) {
                    edtuser.requestFocus();
                    edtuser.setError("please use alphabets only");
                } else if (pswd.length() <= 5) {
                    edtpswd.requestFocus();
                    edtpswd.setError("password should be atleast 6 characters");
                } else {

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(uname)) {
                                Toast.makeText(Signup.this, "Username unavailable", Toast.LENGTH_SHORT).show();
                            } else {
                                //sending data to firebase
                                // using email as unique identifier
                                databaseReference.child("users").child(uname).child("Name").setValue(name);
                                databaseReference.child("users").child(uname).child("Father name").setValue(fname);
                                databaseReference.child("users").child(uname).child("Age").setValue(age);
                                databaseReference.child("users").child(uname).child("Phone").setValue(phone);
                                databaseReference.child("users").child(uname).child("Email Address").setValue(email);
                                databaseReference.child("users").child(uname).child("password").setValue(pswd);
                                databaseReference.child("users").child(uname).child("gender").setValue(gender);
                                Toast.makeText(Signup.this, "you have been registered ", Toast.LENGTH_SHORT).show();
                                Toast.makeText(Signup.this, "Login with your new credentials", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });
        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}