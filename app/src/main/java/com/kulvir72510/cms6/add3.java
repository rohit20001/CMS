package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class add3 extends AppCompatActivity {
    EditText model_name,gst,road_price,color,price;
    Button btn_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add3);
        price = findViewById(R.id.et_phone);
        color = findViewById(R.id.et_email_id);
        road_price = findViewById(R.id.et_city);
        gst = findViewById(R.id.et_address);
        model_name = findViewById(R.id.tv_email);
        btn_save = findViewById(R.id.btn_save);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation3);
        bottomNavigationView.setSelectedItemId(R.id.ic_graph);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_add){
                    return false;
                }
                else if(id==R.id.ic_home){
                    startActivity(new Intent(add3.this,home3.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(add3.this,profile3.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });
    }
}