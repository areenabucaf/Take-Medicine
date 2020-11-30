package com.example.addaneworder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;


public class MainActivity extends AppCompatActivity implements LocationListener {

    Button button_location;
    TextView textView_location;
    LocationManager locationManager;
    EditText EnterText;
    EditText EnterNum;
    Button buttonNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterText=findViewById(R.id.EnterText);
        EnterNum=findViewById(R.id.EnterNum);
        buttonNext=findViewById(R.id.buttonNext);
        textView_location=findViewById(R.id.text_location);
        button_location=findViewById(R.id.button_location);
        EnterText.addTextChangedListener(loginTextWatcher);
        EnterNum.addTextChangedListener(loginTextWatcher);





        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });


    }
    @SuppressLint("MissingPermission")
    private void getLocation(){
        try{
            locationManager=(LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,MainActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();

        try{
            Geocoder geocoder= new Geocoder(MainActivity.this, Locale.getDefault());
            List<Address> addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address=addresses.get(0).getAddressLine(0);
            textView_location.setText(address);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private TextWatcher loginTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String userMedInput =EnterText.getText().toString().trim();
            String userNumInput=EnterNum.getText().toString().trim();

            buttonNext.setEnabled(!userMedInput.isEmpty() && !userNumInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}