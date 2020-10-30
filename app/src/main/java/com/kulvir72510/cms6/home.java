package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_home);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_home){
                    return;
                }
                else if(id==R.id.ic_add){
                    startActivity(new Intent(home.this,add.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_graph){
                    startActivity(new Intent(home.this,graph.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_personadd){
                    startActivity(new Intent(home.this,personAdd.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_profile){
                   startActivity(new Intent(home.this,profile.class));
                    overridePendingTransition(0, 0);
                }
            }
        });
    }
}