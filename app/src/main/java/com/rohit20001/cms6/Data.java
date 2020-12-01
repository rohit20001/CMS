package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data extends AppCompatActivity {
    public ImageView img_cms_family;
    public TextView tv_add_in_family_of;
    public TextView tv_cms;
    public ImageView img_back;
    public EditText et_full_name;
    public EditText et_address;
    public EditText et_city;
    public RadioButton rbtn_male;
    public RadioButton rbtn_female;
    public EditText et_phone;
    public EditText et_email_id;
    public EditText et_password_family;
    public Button btn_save;
    public ProgressBar progressBar2;
    FirebaseAuth fAuth;
    String userId;
    private static final String TAG ="TAG" ;
    public RadioGroup radioGroup1;
    private String position;
    FirebaseFirestore fStore=FirebaseFirestore.getInstance();
    private CollectionReference reff = fStore.collection("details");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        img_cms_family=(ImageView) findViewById(R.id.img_cms_family);
        tv_add_in_family_of=(TextView) findViewById(R.id.tv_add_in_family_of);
        tv_cms=(TextView) findViewById(R.id.tv_cms);
        img_back=(ImageView) findViewById(R.id.img_back);
        et_full_name=(EditText) findViewById(R.id.tv_email);
        et_address=(EditText) findViewById(R.id.et_address);
        et_city=(EditText) findViewById(R.id.et_city);
        rbtn_male=(RadioButton) findViewById(R.id.rbtn_male);
        rbtn_female=(RadioButton) findViewById(R.id.rbtn_female);
        et_phone=(EditText) findViewById(R.id.et_phone);
        et_password_family=(EditText) findViewById(R.id.et_password_family);
        btn_save=(Button) findViewById(R.id.btn_save);
        progressBar2 = findViewById(R.id.progressBar2);
        et_email_id= findViewById(R.id.et_email_id);
        radioGroup1=findViewById(R.id.radioGroup1);
        fAuth = FirebaseAuth.getInstance();
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation2);
        bottomNavigationView.setSelectedItemId(R.id.ic_add2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id == R.id.ic_add2){
                    return false;
                }
                else if(id==R.id.ic_add){
                    startActivity(new Intent(Data.this,add2.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_graph){
                    startActivity(new Intent(Data.this,graph2.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_home){
                    startActivity(new Intent(Data.this,home2.class));
                    overridePendingTransition(0, 0);
                }
                else if(id==R.id.ic_profile){
                    startActivity(new Intent(Data.this,profile2.class));
                    overridePendingTransition(0, 0);
                }
                return true;
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = et_email_id.getText().toString().trim();
                final String password = et_password_family.getText().toString().trim();
                final String fullname= et_full_name.getText().toString().trim();
                final String address = et_address.getText().toString().trim();
                final String city = et_city.getText().toString();
                final long phoneno = Long.parseLong(et_phone.getText().toString().trim());
                final String m = rbtn_male.getText().toString();
                final String f = rbtn_female.getText().toString();
                final String position = "user";


                if (TextUtils.isEmpty(email)){
                    et_email_id.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    et_password_family.setError("password is required");
                    return;
                }
                if (TextUtils.isEmpty(fullname)){
                    et_full_name.setError("password is required");
                    return;
                }
                if (TextUtils.isEmpty(address)){
                    et_address.setError("password is required");
                    return;
                }
                if (TextUtils.isEmpty(city)){
                    et_city.setError("password is required");
                    return;
                }

                if(password.length()<6){
                    et_password_family.setError("enter password more than 6");
                    return;
                }



                progressBar2.setVisibility(View.VISIBLE);

                //register the user in firebase
                if (rbtn_male.isChecked() || rbtn_female.isChecked()){


                                DocumentReference documentReference= fStore.collection("Details").document();
                                Map<String,Object> user= new HashMap<>();
                                user.put("Full_Name",fullname);
                                user.put("Phone",phoneno);
                                user.put("Email",email);
                                user.put("Model",password);
                                user.put("Address",address);
                                user.put("Year",city);
                                user.put("position",position);
                                if (rbtn_male.isChecked()){
                                    user.put("Gender",m);
                                }
                                if (rbtn_female.isChecked()){
                                    user.put("Gender",f);
                                }
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "user profile created successfully" + userId);
                                        Toast.makeText(Data.this,"details added successfully",Toast.LENGTH_LONG).show();
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(),home2.class));
                }else {
                    Toast.makeText(Data.this,"Please check the gender",Toast.LENGTH_LONG).show();
                    progressBar2.setVisibility(View.GONE);
                }
            }
        });

    }
}