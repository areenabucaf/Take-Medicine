package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button log;
    private Button sign;
    private EditText email ;
    private EditText pass ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //<< this
        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);

        sign = (Button) findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Uploading.class);
                startActivity(i);
            }
        });

        log = (Button) findViewById(R.id.log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Intent l = new Intent(MainActivity.this, Uploading.class);

                if (ValidDetails(email.getText().length(),pass.getText().length())==true ) {
                    logInFunction(email.getText().toString() , pass.getText().toString());
                }
                else
                    Toast.makeText(MainActivity.this , "תכניס הפרטיים", Toast.LENGTH_LONG).show();
            }
        });
    }
    public boolean ValidDetails(int email, int pass){
        if (email!= 0 && pass!= 0 )
            return true;
        else
            return false;
    }
    public void logInFunction(final String userName , String password ) {
        mAuth.signInWithEmailAndPassword(userName , password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           Intent i = new Intent(MainActivity.this, AddOrder.class);
                           i.putExtra("userName", userName );
                            startActivity(i);
                            finish();
                        } else
                        {
                            Toast.makeText(MainActivity.this, "not existing ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}