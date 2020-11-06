package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class add3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add3);
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