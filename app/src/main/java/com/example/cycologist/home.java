package com.example.cycologist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class home extends AppCompatActivity  {
 String name, fathername, username, email, phone;
 StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar tb=findViewById(R.id.toolbarhome);
        setSupportActionBar(tb);
        ImageView dphome=findViewById(R.id.dphome);
        ImageButton test=findViewById(R.id.test);
        ImageButton infobtn=findViewById(R.id.info);
ImageButton scaling=findViewById(R.id.scaling);
        ImageButton recommendbtn=findViewById(R.id.recommend);
        TextView tvname = findViewById(R.id.namehome);
        Intent it = getIntent();
         name = it.getStringExtra("name");
         fathername = it.getStringExtra("fathername");
         username = it.getStringExtra("uname");
         email = it.getStringExtra("email");
         phone = it.getStringExtra("phone");
        tvname.setText(name);
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileref = storageReference.child("users/" + username + "profile.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(dphome);
            }
        });
        recommendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recommend=new Intent(home.this, doctorrecommendation.class);
                startActivity(recommend);
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testact=new Intent(home.this,test.class);
                testact.putExtra("uname", username);
                startActivity(testact);
            }
        });
        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoit=new Intent(home.this,info.class);
                startActivity(infoit);
            }
        });
        scaling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scalingintent=new Intent(home.this, scaling.class);
                scalingintent.putExtra("username",username);
                startActivity(scalingintent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menus,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                Intent intent = new Intent(home.this, MainActivity.class);
                Toast.makeText(home.this, "Logging Out", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
                return true;
            case R.id.profile:
                Intent intent2 = new Intent(home.this, profile.class);
                intent2.putExtra("name", name);
                intent2.putExtra("fathername", fathername);
                intent2.putExtra("uname", username);
                intent2.putExtra("email", email);
                intent2.putExtra("phone", phone);
                startActivity(intent2); return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}