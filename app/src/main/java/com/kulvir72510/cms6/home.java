package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_add:
                        startActivity(new Intent(home.this, add.class));
                        overridePendingTransition(0, 0);
                    case R.id.ic_home:
                        return;
                    case R.id.ic_graph:
                        startActivity(new Intent(home.this, profile.class));
                        overridePendingTransition(0, 0);
                    case R.id.ic_personadd:
                        startActivity(new Intent(home.this, personAdd.class));
                        overridePendingTransition(0, 0);
                    case R.id.ic_profile:
                        startActivity(new Intent(home.this, personAdd.class));
                        overridePendingTransition(0, 0);
                }
            }
        });
    }

    public void logout(View view) {
        startActivity(new Intent(home.this,MainActivity.class))
    }
}
}