package com.kulvir72510.cms6;

import android.text.Editable;

import com.google.firebase.database.ServerValue;

public class Post {
    private String postKey;
    private String tv_email;
    private String et_email_id;
    private String et_phone;
    private String imageView6;
    private String et_address;
    private String et_city;
    private Object timestamp;

    public Post(String tv_email,String et_email_id,String et_phone,String imageView6,String et_address,String et_city){

        this.tv_email=tv_email;
        this.et_email_id=et_email_id;
        this.et_phone=et_phone;
        this.imageView6=imageView6;
        this.et_address=et_address;
        this.et_city=et_city;
        this.timestamp= ServerValue.TIMESTAMP;


    }


    public Post() {
    }

    public Post(Editable text, Editable text1, Editable text2, Editable text3, Editable text4, String imageDownloadLink) {
    }


    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getTv_email() {
            return tv_email;
        }

        public String getEt_email_id() {
            return et_email_id;
        }

        public String getEt_phone() {
            return et_phone;
        }

        public String getImageView6() {
            return imageView6;
        }

        public String getEt_address() {
            return et_address;
        }

        public String getEt_city() {
            return et_city;
        }

        public Object getTimestamp() {
            return timestamp;
        }

    public void setTv_email(String tv_email) {
        this.tv_email = tv_email;
    }

    public void setEt_email_id(String et_email_id) {
        this.et_email_id = et_email_id;
    }

    public void setEt_phone(String et_phone) {
        this.et_phone = et_phone;
    }

    public void setImageView6(String imageView6) {
        this.imageView6 = imageView6;
    }

    public void setEt_address(String et_address) {
        this.et_address = et_address;
    }

    public void setEt_city(String et_city) {
        this.et_city = et_city;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}

