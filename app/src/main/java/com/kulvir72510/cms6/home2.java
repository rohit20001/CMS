package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation2);
        bottomNavigationView.setSelectedItemId(R.id.ic_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_home){
                    return false;
                }
                else if(id==R.id.ic_graph){
                    startActivity(new Intent(home2.this,graph2.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_add){
                    startActivity(new Intent(home2.this,add2.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(home2.this,profile2.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });
    }
}