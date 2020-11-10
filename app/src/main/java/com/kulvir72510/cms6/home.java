package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_home){
                    return false;
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
                return true;
            }
        });
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        List<ModelClass> modelClassList=new ArrayList<>();
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"this is title 1","this is title 1 user"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"this is title 2","this is title 2 user"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"this is title 3","this is title 3 user"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"this is title 4","this is title 4 user"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"this is title 5","this is title 5 user"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"this is title 6","this is title 6 user"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"this is title 7","this is title 7 user"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"this is title 8","this is title 8 user"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"this is title 9","this is title  9 user"));
        modelClassList.add(new ModelClass(R.drawable.ic_launcher_background,"this is title 10","this is title 10 user"));

        Adapter adapter=new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}