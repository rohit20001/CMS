package com.kulvir72510.cms6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Splash extends AppCompatActivity {

    public ImageView img_car_sales;
    public ImageView img_cms;
    public EditText et_username;
    public EditText et_password;
    public Button btn_login;
    public Button btn_sign_up;
    public Button btn_login_with_google;
    public Button btn_login_with_facebook;
    public TextView tv_forgot_password;
    public ProgressBar progressBar;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    String position="";
    FirebaseFirestore fStore=FirebaseFirestore.getInstance();
    ScrollView scroll;
    ScrollView G_scroll;

    public GoogleApiClient googleApiClient;
    public static final int SIGN_IN=1;
    public static  String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img_car_sales=(ImageView)findViewById(R.id.img_car_sales);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
                finish();


            }
        },4000);
    }
}