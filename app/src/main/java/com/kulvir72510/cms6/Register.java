package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

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
    FirebaseFirestore fStore;
    private static final String TAG ="TAG" ;
    public RadioGroup radioGroup1;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        fStore=FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser()!=null){
            userId = fAuth.getCurrentUser().getUid();
            DocumentReference documentReference=fStore.collection("users").document(userId);
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()){
                        position = documentSnapshot.getString("Full_Name");
                        System.out.println(position);
                        Toast.makeText(Register.this,"User Logged in successfully",Toast.LENGTH_LONG).show();


                        if (position.equals("Admin")){

                            startActivity(new Intent(getApplicationContext(),home.class));}
                        else if (position.equals("Monitor")){

                            startActivity(new Intent(getApplicationContext(),home2.class));}
                        else{

                            startActivity(new Intent(getApplicationContext(),home3.class));}
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"sorry error !!",Toast.LENGTH_LONG).show();
                }
            });

        }

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
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created",Toast.LENGTH_LONG).show();
                            userId = fAuth.getCurrentUser().getUid();

                            DocumentReference documentReference= fStore.collection("users").document(userId);
                            Map<String,Object> user= new HashMap<>();
                            user.put("Full_Name",fullname);
                            user.put("Phone",phoneno);
                            user.put("Email",email);
                            user.put("Password",password);
                            user.put("Address",address);
                            user.put("city",city);
                            user.put("position",position);
                            user.put("imageUri","");
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
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),home3.class));
                        }else {
                            Toast.makeText(Register.this,"Error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            progressBar2.setVisibility(View.GONE);
                        }
                    }
                });


            }else {
                    Toast.makeText(Register.this,"Please check the gender",Toast.LENGTH_LONG).show();
                    progressBar2.setVisibility(View.GONE);
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

            }
}
