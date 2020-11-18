package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class home3 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter PostAdaptar;
    private DatabaseReference mDatabaseRef;
    private List<ModelClass> mUploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation3);
        bottomNavigationView.setSelectedItemId(R.id.ic_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.ic_home) {
                    return false;
                } else if (id == R.id.ic_add) {
                    startActivity(new Intent(home3.this, add3.class));
                    overridePendingTransition(0, 0);
                } else if (id == R.id.ic_profile) {
                    startActivity(new Intent(home3.this, profile3.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });
        recyclerView=findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads=new ArrayList<>();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Models");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshhot : dataSnapshot.getChildren()){
                    ModelClass modelClass=postSnapshhot.getValue(ModelClass.class);
                    mUploads.add(modelClass);
                }
                PostAdaptar=new Adapter(home3.this,mUploads);
                recyclerView.setAdapter(PostAdaptar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
             Toast.makeText(home3.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}

