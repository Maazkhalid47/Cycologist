package com.example.cycologist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {
    ImageView dp,dpedit;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbarprofile);
        setSupportActionBar(toolbar);
        dp = findViewById(R.id.imgvprofile);
        dpedit=findViewById(R.id.imgveditprofile);
        Intent it = getIntent();
        String username = it.getStringExtra("uname");
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileref = storageReference.child("users/" + username + "profile.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(dp);
            }
        });
        EditText edtname = findViewById(R.id.nameprof);
        EditText edtuname = findViewById(R.id.unameprof);
        EditText edtfname = findViewById(R.id.fathernameprof);
        EditText edtemail = findViewById(R.id.emailprof);
        EditText edtphone = findViewById(R.id.phoneprof);
        String name = it.getStringExtra("name");
        String fathername = it.getStringExtra("fathername");
        String email = it.getStringExtra("email");
        String phone = it.getStringExtra("phone");
        edtname.setText(name);
        edtname.setFocusableInTouchMode(false);
        edtuname.setText(username);
        edtfname.setText(fathername);
        edtemail.setText(email);
        edtphone.setText(phone);

        dpedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dpedit = new Intent(profile.this, upload_profile_pic.class);
                dpedit.putExtra("uname", username);
                startActivity(dpedit);

            }
        });

    }


}
