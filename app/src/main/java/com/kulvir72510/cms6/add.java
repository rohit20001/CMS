package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class add extends AppCompatActivity {

    EditText model_name,gst,road_price,color,price;
    Button btn_save;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        price = findViewById(R.id.et_phone);
        color = findViewById(R.id.et_email_id);
        road_price = findViewById(R.id.et_city);
        gst = findViewById(R.id.et_address);
        model_name = findViewById(R.id.tv_email);
        btn_save = findViewById(R.id.btn_save);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_add);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_add){
                    return false;
                }
                else if(id==R.id.ic_home){
                    startActivity(new Intent(add.this,home.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_graph){
                    startActivity(new Intent(add.this,graph.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_personadd){
                    startActivity(new Intent(add.this,personAdd.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(add.this,profile.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String modelName = model_name.getText().toString().trim();
                final String col = color.getText().toString().trim();
                final long pri = Long.parseLong(price.getText().toString().trim());
                final int g = Integer.parseInt(gst.getText().toString().trim());
                final String o = road_price.getText().toString();

                if (TextUtils.isEmpty(modelName)){
                    model_name.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(col)){
                    color.setError("password is required");
                    return;
                }
                if (TextUtils.isEmpty(o)){
                    road_price.setError("password is required");
                    return;
                }



                Map<String, Object> docData = new HashMap<>();
                docData.put("model_name", modelName);
                docData.put("color", col);
                docData.put("price", pri);
                docData.put("gst",g);
                docData.put("other", o);
                docData.put("imageUri","");

// Add a new document (asynchronously) in collection "cities" with id "LA"
                fStore.collection("Models").add(docData);
                Toast.makeText(add.this,"Car added successfully",Toast.LENGTH_LONG).show();

// ...
// future.get() blocks on response

            }
        });
    }
}