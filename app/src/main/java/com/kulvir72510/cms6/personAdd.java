package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class personAdd extends AppCompatActivity {

    EditText tv_name,et_email_id,et_phone,et_address,et_city,et_password_family;
    RadioButton rbtn_male,rbtn_female,rbtn_monitor,rbtn_admin;
    Button btn_save;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_add);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_personadd);
        tv_name=findViewById(R.id.tv_email);
        et_email_id=findViewById(R.id.et_email_id);
        et_phone=findViewById(R.id.et_phone);
        et_address=findViewById(R.id.et_address);
        et_city=findViewById(R.id.et_city);
        et_password_family=findViewById(R.id.et_password_family);
        rbtn_male=findViewById(R.id.rbtn_male);
        rbtn_female=findViewById(R.id.rbtn_female);
        rbtn_admin=findViewById(R.id.rbtn_admin);
        rbtn_monitor=findViewById(R.id.rbtn_monitor);
        btn_save=findViewById(R.id.btn_save);
        fAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_personadd){
                    return false;
                }
                else if(id==R.id.ic_add){
                    startActivity(new Intent(personAdd.this,add.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_graph){
                    startActivity(new Intent(personAdd.this,graph.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_home){
                    startActivity(new Intent(personAdd.this,home.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(personAdd.this,profile.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });
    }
}