package model;

import android.media.Image;

import androidx.annotation.NonNull;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String rId;
    private String name;
    private String email;
    private String phone;
    private String password;
    private List<Item> listOfItems;
    private String  photo;
    private String address;

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Item> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(ArrayList<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public Restaurant(String rId, String name, String email, String phone, String password, ArrayList<Item> listOfItems, String  photo, String address) {
        this.rId = rId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.listOfItems = listOfItems;
        this.photo = photo;
        this.address = address;
    }
    public Restaurant()
    {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String  photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
