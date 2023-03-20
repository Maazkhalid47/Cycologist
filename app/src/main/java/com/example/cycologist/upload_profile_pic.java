package com.example.cycologist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class upload_profile_pic extends AppCompatActivity {
private Button btnchoosepic, btnuploadpic;
private static final int PICK_IMAGE_REQUEST=1;
private ImageView img;
private ProgressBar loading;
private Uri imguri;
    String username;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_pic);
        btnchoosepic=findViewById(R.id.btnchoosepic);
        btnuploadpic=findViewById(R.id.btnuploadpic);
        img=findViewById(R.id.imageView_profile_dp);
        loading=findViewById(R.id.loading);
        Intent it=getIntent();
         username = it.getStringExtra("uname");
        storageReference= FirebaseStorage.getInstance().getReference();
        StorageReference profileref=storageReference.child("users/"+username+"profile.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img);
            }
        });
        btnchoosepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent opengalleryintent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(opengalleryintent,1000);
//openfilechoose();
            }
        });
        btnuploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadpic(imguri);

            }
        });

    }

    private void uploadpic(Uri imguri) {
        StorageReference fileref=storageReference.child("users/"+username+"profile.jpg");
        fileref.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
    @Override
    public void onSuccess(Uri uri) {

        Picasso.get().load(uri).into(img);
        Toast.makeText(upload_profile_pic.this, "picture updated", Toast.LENGTH_SHORT).show();
    }
});            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(upload_profile_pic.this, "An Error Ocurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 1000){
        if(resultCode == Activity.RESULT_OK){
            imguri=data.getData();
            img.setImageURI(imguri);
        }
    }

    }

    //    private void openfilechoose() {
//        Intent pickpic=new Intent();
//        pickpic.setType("image/*");
//        pickpic.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(pickpic,PICK_IMAGE_REQUEST);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
//imguri=data.getData();
//        Picasso.with(this).load(imguri).into(img);
//        }
//    }
}