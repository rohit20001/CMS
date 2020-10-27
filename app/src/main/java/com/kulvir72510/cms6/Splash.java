package com.kulvir72510.cms6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    public ImageView img_car_sales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img_car_sales=(ImageView)findViewById(R.id.img_car_sales);
    }
}