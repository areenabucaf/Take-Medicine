package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;
import java.util.UUID;
public class Uploading extends AppCompatActivity {
    private Button s;
    private Button l;
    private ImageView photo;
    private String name;
    private String num;
    private String communicate;
    private String location;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    String userName = "";
    String link = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //<< this
        setContentView(R.layout.activity_loadphoto);
        Intent z = getIntent();
        userName=z.getStringExtra("username");
        name=z.getStringExtra("name");
        num=z.getStringExtra("num");
        communicate=z.getStringExtra("communicate");
        location=z.getStringExtra("location");


        s = (Button) findViewById(R.id.next);
        s.setOnClickListener(new View.OnClickListener() {//زر المشاركة يعود لصفحتي الشخصية مع اضافة كتابي الجديد في صفحتي الشخصية وفي معرض الكتب//
            @Override
            public void onClick(View view) {


                try {
                    uploadImage();
                }catch (Exception t) {}
                Medicine med = new Medicine(link, num, communicate, location, name);

                addToCollections(userName, med);

            }
        });
        photo = (ImageView) findViewById(R.id.imageload);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseImage();
            }
        });


        l = (Button) findViewById(R.id.cancel);//زر الالغاء يعود لصفحتي الشخصية بدون اي تغيير//
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Uploading.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void uploadImage() {
        //Firebase
        FirebaseStorage storage;
        StorageReference storageReference;


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            link = "images/"+ UUID.randomUUID().toString() ;
            StorageReference ref = storageReference.child(link);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Uploading.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Uploading.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    public void addToCollections(final String userName, Medicine b) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.w("tag" , "userName " + userName  ) ;
        db.collection(userName)
                .add(b).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Uploading.this, " donee", Toast.LENGTH_LONG).show();

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Uploading.this, " الرجاء المحاوله مره اخرى ", Toast.LENGTH_LONG).show();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                photo.setImageBitmap(bitmap);


            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


    }
}

