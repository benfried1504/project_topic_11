package com.example.project_topic11;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;


public class Product implements Serializable {
    String name;
    byte[] photo; // saved as bytes[] instead of Bitmap so that it will be Serializable
    double price;
    int amount;

    public Product(String name, Bitmap photo, double price, int amount) {
        this.name = name;
        setPhoto(photo);
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPhoto() {
        // Change into Bitmap from bytes[]
        if(photo!=null) {
            return BitmapFactory.decodeByteArray(photo, 0, photo.length);
        }
        return null;
    }

    public void setPhoto(Bitmap photo) {
        // save Bitmap photo as bytes[]
        if (photo != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            this.photo = stream.toByteArray();
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @NonNull
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public double getTotalPrice() {
        return price * amount;
    }
}
