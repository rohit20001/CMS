package com.kulvir72510.cms6;

public class ModelClass {
    private  int imageIcon;
    String title,Body;

    public ModelClass(int imageIcon,String title,String Body){
        this.imageIcon=imageIcon;
        this.title=title;
        this.Body=Body;
    }
    public int getImageIcon(){
        return imageIcon;
    }
    public String getTitle(){
        return title;
    }
    public String getBody(){
        return Body;
    }
}
