package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class personAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_add);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_personadd);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_personadd){
                    return;
                }
                else if(id==R.id.ic_add){
                    startActivity(new Intent(personAdd.this,add.class));
                }
                else if(id==R.id.ic_graph){
                    startActivity(new Intent(personAdd.this,graph.class));
                }
                else if(id==R.id.ic_home){
                    startActivity(new Intent(personAdd.this,home.class));
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(personAdd.this,profile.class));
                }
            }
        });
    }
}