package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_graph);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_graph){
                    return;
                }
                else if(id==R.id.ic_add){
                    startActivity(new Intent(graph.this,add.class));
                }
                else if(id==R.id.ic_home){
                    startActivity(new Intent(graph.this,home.class));
                }
                else if(id==R.id.ic_personadd){
                    startActivity(new Intent(graph.this,personAdd.class));
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(graph.this,profile.class));
                }
            }
        });
    }
}