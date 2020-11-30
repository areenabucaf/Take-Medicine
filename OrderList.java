package com.example.takemed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManagerm;

    FirebaseDatabase database;
    DatabaseReference orderList;


    Button exitb;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list);


        database =FirebaseDatabase.getInstance();
        orderList=database.getReference("order");
        //recyclerView=(RecyclerView)findViewById(R.id.Re)
        recyclerView.setHasFixedSize(true);
        layoutManagerm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManagerm);

        exitb=(Button)findViewById(R.id.button2);
        exitb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        button=(Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                composeOrder();
            }
        });

    }
    public  void composeOrder()
    {
        Intent i =new Intent(this,OrderDetail.class);
        startActivity(i);

    }
}