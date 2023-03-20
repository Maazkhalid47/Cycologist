package com.example.cycologist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cycologist-default-rtdb.firebaseio.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText useredt = findViewById(R.id.edtuser);
        EditText pswdedt = findViewById(R.id.edtpswdL);
        Button loginbutton = findViewById(R.id.btnlogin);
        TextView frgtpswd = findViewById(R.id.frgtpswd);
        TextView signuptxt = findViewById(R.id.signuptxt);

        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, Signup.class);
                startActivity(it);
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = useredt.getText().toString();
                String pswd = pswdedt.getText().toString();
                if (username.isEmpty() || pswd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Complete details", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(username)) {
                                final String password = snapshot.child(username).child("password").getValue(String.class);
                                if (password.equals(pswd)) {
                                    Toast.makeText(MainActivity.this, "Logging in", Toast.LENGTH_SHORT).show();
                                    Intent it = new Intent(MainActivity.this, home.class);
                                    //first get values from database according to the logged in user into variables
                                    final String name = snapshot.child(username).child("Name").getValue(String.class);
                                    final String fname = snapshot.child(username).child("Father name").getValue(String.class);
                                    final String email = snapshot.child(username).child("Email Address").getValue(String.class);
                                    final String phone = snapshot.child(username).child("Phone").getValue(String.class);
                                    //now sending the acquired data to the activity where intent is taking
                                    it.putExtra("name", name);
                                    it.putExtra("fathername", fname);
                                    it.putExtra("uname", username);
                                    it.putExtra("email", email);
                                    it.putExtra("phone", phone);
                                    startActivity(it);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "invalid password ", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "User not registered ", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        frgtpswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}