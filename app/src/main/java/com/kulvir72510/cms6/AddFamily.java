package com.kulvir72510.cms6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class AddFamily extends AppCompatActivity {

    public ImageView img_cms_family;
    public TextView tv_add_in_family_of;
    public TextView tv_cms;
    public ImageView img_back;
    public EditText et_full_name;
    public EditText et_address;
    public EditText et_city;
    public EditText et_email_id;
    public RadioButton rbtn_male;
    public RadioButton rbtn_female;
    public EditText et_phone;
    public RadioButton rbtn_monitor;
    public RadioButton rbtn_admin;
    public EditText et_password_family;
    public Button btn_save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family);

        img_cms_family=(ImageView) findViewById(R.id.img_cms_family);
        tv_add_in_family_of=(TextView) findViewById(R.id.tv_add_in_family_of);
        tv_cms=(TextView) findViewById(R.id.tv_cms);
        img_back=(ImageView) findViewById(R.id.img_back);
        et_full_name=(EditText) findViewById(R.id.et_full_name);
        et_address=(EditText) findViewById(R.id.et_address);
        et_city=(EditText) findViewById(R.id.et_city);
        et_email_id=(EditText) findViewById(R.id.et_email_id);
        rbtn_male=(RadioButton) findViewById(R.id.rbtn_male);
        rbtn_female=(RadioButton) findViewById(R.id.rbtn_female);
        et_phone=(EditText) findViewById(R.id.et_phone);
        rbtn_monitor=(RadioButton) findViewById(R.id.rbtn_monitor);
        rbtn_admin=(RadioButton) findViewById(R.id.rbtn_admin);
        et_password_family=(EditText) findViewById(R.id.et_password_family);
        btn_save=(Button) findViewById(R.id.btn_save);
    }
}