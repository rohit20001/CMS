package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.kulvir72510.cms6.MainActivity.userId;

public class personAdd extends AppCompatActivity {

    EditText et_name,et_email_id,et_phone,et_address,et_city,et_password_family;
    RadioButton rbtn_male,rbtn_female,rbtn_monitor,rbtn_admin;
    Button btn_save;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore=FirebaseFirestore.getInstance();
    String userId2;
    private static final String TAG ="TAG" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_add);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_personadd);
        et_name=findViewById(R.id.tv_email);
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

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = et_name.getText().toString().trim();
                final String email = et_email_id.getText().toString().trim();
                final String password = et_password_family.getText().toString().trim();
                final String address = et_address.getText().toString().trim();
                final String city = et_city.getText().toString();
                final long phoneno = Long.parseLong(et_phone.getText().toString().trim());
                final String m = rbtn_male.getText().toString();
                final String f = rbtn_female.getText().toString();
                final String admin = rbtn_admin.getText().toString();
                final String monitor = rbtn_monitor.getText().toString();


                if (TextUtils.isEmpty(email)){
                    et_email_id.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    et_password_family.setError("password is required");
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    et_name.setError("password is required");
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


                //register the user in firebase
                if (rbtn_male.isChecked() || rbtn_female.isChecked()){

                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(personAdd.this,"New User Created",Toast.LENGTH_LONG).show();
                                System.out.println(userId);
                                userId2 = fAuth.getUid();
                                System.out.println(userId2);
                                System.out.println(userId);



                                DocumentReference documentReference= fStore.collection("users").document(userId2);
                                Map<String,Object> user= new HashMap<>();
                                user.put("Full_Name",name);
                                user.put("Phone",phoneno);
                                user.put("Email",email);
                                user.put("Password",password);
                                user.put("Address",address);
                                user.put("city",city);
                                if (rbtn_male.isChecked()){
                                    user.put("Gender",m);
                                }
                                if (rbtn_female.isChecked()){
                                    user.put("Gender",f);
                                }
                                if (rbtn_admin.isChecked()){
                                    user.put("position",admin);
                                }
                                if (rbtn_monitor.isChecked()){
                                    user.put("position",monitor);
                                }
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG,"user profile created successfully"+userId2);
                                        fAuth.signOut();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        finish();

                                    }
                                });


                            }else {
                                Toast.makeText(personAdd.this,"Error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();

                            }
                        }
                    });


                }else {
                    Toast.makeText(personAdd.this,"Please check the gender",Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}