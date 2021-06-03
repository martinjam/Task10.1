package com.example.task61.model;

import android.graphics.Bitmap;

import java.util.Date;

public class Food {
    private int id, listed;
    private String title, description, date, pick_up_time, quantity, location, price;
    private byte[] image;


    public Food(String date, String title, String description, String pick_up_time, String quantity, String location, byte[] image, String price) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.pick_up_time = pick_up_time;
        this.quantity = quantity;
        this.location = location;
        this.image = image;
        this.price = price;
    }

    public Food() {
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPick_up_time() {
        return pick_up_time;
    }

    public void setPick_up_time(String pick_up_time) {
        this.pick_up_time = pick_up_time;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getListed() {
        return listed;
    }

    public void setListed(int listed) {
        this.listed = listed;
    }
}