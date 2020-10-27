package com.kulvir72510.cms6;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public ImageView img_cms;
    public EditText et_username;
    public EditText et_password;
    public Button btn_login;
    public Button btn_sign_up;
    public Button btn_login_with_google;
    public Button btn_login_with_facebook;
    public TextView tv_forgot_password;

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

    }
}