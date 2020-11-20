package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class graph2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph2);

        findViewById(R.id.btnBarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BarChartActivity.class));
            }
        });

        findViewById(R.id.btnPieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), lineChart.class));
            }
        });

        findViewById(R.id.btnRadarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RadarChartActivity.class));
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation2);
        bottomNavigationView.setSelectedItemId(R.id.ic_graph);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_graph){
                    return false;
                }
                else if(id==R.id.ic_add){
                    startActivity(new Intent(graph2.this,add2.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_home){
                    startActivity(new Intent(graph2.this,home2.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(graph2.this,profile2.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_add2){
                    startActivity(new Intent(graph2.this,Data.class));
                    overridePendingTransition(0, 0);
                }

                return true;
            }
        });
    }
}