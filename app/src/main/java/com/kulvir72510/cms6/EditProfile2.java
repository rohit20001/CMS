package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.kulvir72510.cms6.MainActivity.userId;

public class EditProfile2 extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText etName,etPhone,etAddress,etCity,etEmail;
    Button btnSave,btnBack;


    FirebaseAuth FAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile2);

        Intent data = getIntent();
        String Name = data.getStringExtra("name");
        final String Email = data.getStringExtra("email");
        final String Phone = data.getStringExtra("phone");
        String Address = data.getStringExtra("address");
        String City = data.getStringExtra("city");
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress= findViewById(R.id.etAddress);
        etEmail = findViewById(R.id.etEmail);
        etCity = findViewById(R.id.etCity);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        FAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = FAuth.getCurrentUser();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),profile2.class));

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty()||etPhone.getText().toString().isEmpty()||
                        etEmail.getText().toString().isEmpty()|| etCity.getText().toString().isEmpty()
                        ||etAddress.getText().toString().isEmpty()){
                    Toast.makeText(EditProfile2.this,"One or more fields are Empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                final long Phone = Long.parseLong(etPhone.getText().toString().trim());
                final String Email = etEmail.getText().toString().trim();
                final String Name = etName.getText().toString().trim();
                final String Address = etAddress.getText().toString().trim();
                final String City = etCity.getText().toString().trim();



                user.updateEmail(Email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditProfile2.this, "Email is changed", Toast.LENGTH_SHORT).show();
                        DocumentReference documentReference=fStore.collection("users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("Full_Name",Name);
                        edited.put("Address",Address);
                        edited.put("Phone",Phone);
                        edited.put("city",City);
                        documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditProfile2.this, "Changes Successfully updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),profile2.class));
                                finish();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile2.this, "Error in updating changes", Toast.LENGTH_SHORT).show();
                    }
                });


            }

        });

        etName.setText(Name);
        etPhone.setText(Phone);
        etAddress.setText(Address);
        etCity.setText(City);
        etEmail.setText(Email);

        Log.d(TAG,"onCreate" +  Name +" "+ Phone +" "+ Address +" "+City );



    }
}