package com.purvik.purpledocsdemo;

/**
 * Created by Purvik Rana on 27-07-2016.
 */
public class ContactPerson {

    public int imgView;
    public String name;
    public String phone_no;

    public ContactPerson() {
        super();
    }

    public ContactPerson(int imgView, String phone_no, String name) {
        this.imgView = imgView;
        this.phone_no = phone_no;
        this.name = name;
    }

    public int getImg_icon() {
        return imgView;
    }

    public void setImg_icon(int imgView) {
        this.imgView = imgView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    @Override
    public String toString() {
        return name +" "+ phone_no;
    }
}
