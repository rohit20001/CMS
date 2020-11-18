package com.kulvir72510.cms6;

public class ModelClass {
    String model_name,color,price,phone,other;

    public ModelClass(String model_name, String color, String price, String phone, String other) {
        this.model_name = model_name;
        this.color = color;
        this.price = price;
        this.phone = phone;
        this.other = other;
    }

    public ModelClass() {
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
