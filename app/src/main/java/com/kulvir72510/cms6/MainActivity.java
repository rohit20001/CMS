package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.rpc.context.AttributeContext;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    public ImageView img_cms;
    public EditText et_username;
    public EditText et_password;
    public Button btn_login;
    public Button btn_sign_up;
    public Button btn_login_with_google;
    public Button btn_login_with_facebook;
    public TextView tv_forgot_password;
    public ProgressBar progressBar;
    FirebaseAuth fAuth;
    String position="";
    FirebaseFirestore fStore;
    ScrollView scroll;
    ScrollView G_scroll;

    public GoogleApiClient googleApiClient;
    public static final int SIGN_IN=1;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_cms= (ImageView) findViewById(R.id.img_cms);
        et_username=(EditText) findViewById(R.id.et_username);
        et_password= (EditText) findViewById(R.id.et_password);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_sign_up= (Button) findViewById(R.id.btn_sign_up);
        btn_login_with_google= (Button) findViewById(R.id.btn_login_with_google);
        btn_login_with_facebook= (Button) findViewById(R.id.btn_login_with_facebook);
        tv_forgot_password= (TextView) findViewById(R.id.tv_forgot_password);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        //scroll = findViewById(R.layout.);
        //G_scroll = findViewById(R.id.G_scroll);
        //loginManager = LoginManager.getInstance();
        if (fAuth.getCurrentUser()!=null){
            userId = fAuth.getCurrentUser().getUid();
            DocumentReference documentReference=fStore.collection("users").document(userId);
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()){
                        position = documentSnapshot.getString("Full_Name");
                        System.out.println(position);
                        Toast.makeText(MainActivity.this,"User Logged in successfully",Toast.LENGTH_LONG).show();


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



        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //scroll.setVisibility(View.VISIBLE);
                String email = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                //String fullname= et_full_name.getText().toString().trim();
                //long phoneno = Long.parseLong(et_phone.getText().toString().trim());

                if (TextUtils.isEmpty(email)){
                    et_username.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    et_password.setError("password is required");
                    return;
                }
                if(password.length()<6){
                    et_password.setError("enter password more than 6");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //Authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            userId = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fStore.collection("users").document(userId);
                            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()){
                                        position = documentSnapshot.getString("position");
                                        Toast.makeText(MainActivity.this,"User Logged in successfully",Toast.LENGTH_LONG).show();


                                        if (position.equals("Admin")){

                                            startActivity(new Intent(getApplicationContext(),home.class));
                                            finish();}
                                        else if (position.equals("Monitor")){

                                            startActivity(new Intent(getApplicationContext(),home2.class));
                                            finish();}
                                        else{

                                            startActivity(new Intent(getApplicationContext(),home3.class));
                                            finish();}

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(),"sorry error !!",Toast.LENGTH_LONG).show();
                                }
                            });



                        }else {
                            Toast.makeText(MainActivity.this,"Error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        btn_login_with_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN);
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                startActivity(new Intent(MainActivity.this, home3.class));
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}