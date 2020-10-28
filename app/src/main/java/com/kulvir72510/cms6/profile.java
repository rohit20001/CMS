package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_profile);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_add:
                        startActivity(new Intent(getApplicationContext(), add.class));
                        overridePendingTransition(0, 0);
                    case R.id.ic_profile:
                        return;
                    case R.id.ic_graph:
                        startActivity(new Intent(getApplicationContext(), graph.class));
                        overridePendingTransition(0, 0);
                    case R.id.ic_personadd:
                        startActivity(new Intent(getApplicationContext(), personAdd.class));
                        overridePendingTransition(0, 0);
                    case R.id.ic_home:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0, 0);
                }
            }
        });
    }
}