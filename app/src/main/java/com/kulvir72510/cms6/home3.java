package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseReference;
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
    private static final String TAG = "TAG";
    FirebaseFirestore fStore=FirebaseFirestore.getInstance();
    String userId;
    FirebaseAuth fAuth;
    private List mArrayList;
    private CollectionReference reff = fStore.collection("users");
    String[] array;
    int i=0;
    AutoCompleteTextView first;
    String HoldAutocompletetextview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation3);
        bottomNavigationView.setSelectedItemId(R.id.ic_home);
        first = findViewById(R.id.first);
        final ArrayList<String> mylist = new ArrayList<String>();

        fAuth=FirebaseAuth.getInstance();
        reff.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    String name=documentSnapshot.getString("Full_Name");
                    System.out.println(name);
                    mylist.add(name);


                }
                System.out.println("fetched all data success");
                System.out.println(mylist);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();

            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_home){
                    return false;
                }
                else if(id==R.id.ic_add){
                    startActivity(new Intent(home3.this,add3.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(home3.this,profile3.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_view,  mylist);
        first.setThreshold(1);
        first.setAdapter(adapter);
        HoldAutocompletetextview = first.getText().toString();




}
    }