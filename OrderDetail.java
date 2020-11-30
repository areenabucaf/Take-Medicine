package com.example.takemed;

import android.app.AppComponentFactory;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderDetail extends AppCompatActivity {
    private DatabaseReference oRefernceOrder;
    private Button del,red;
    Button btn;

    private String Number_of_order,Image,Telephon,Name;
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContenView(R.layout.orderdetail);
        btn=(Button) findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

            }


        });
    }

    private void setContenView(int orderdetail) {
    }

   /* private void setContenView(int orderdetail) {
        return;
    }*/

    public OrderDetail() {
    }



    public interface dataStatus{
        void DataIsDeleted();


    }

    public OrderDetail(String number_of_order, String image, String telephon, String name)
    {
        Number_of_order=number_of_order;
        Image=image;
        Telephon=telephon;

    }
    public  String getNumber_of_order()
    {
        return  Number_of_order;
    }
    public  String getImage()
    {
        return  Image;
    }
    public String getTelephon()
    {
        return  Telephon;
    }
    public String getName()
    {
        return Name;
    }
    public void setNumber_of_order(String numberOfOrder)

    {
        Number_of_order=numberOfOrder;
    }
    public void setImage(String image)

    {
        Image=image;
    }

    public void setTelephon(String telephon) {
        Telephon = telephon;
    }

    public void setName(String name) {
        Name = name;
    }
    public  void onClick(DialogInterface dialog,int which)
    {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("order").child(getNumber_of_order());
        reference.removeValue();
    }


}
