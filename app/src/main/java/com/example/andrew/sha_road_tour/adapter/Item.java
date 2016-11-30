package com.example.andrew.sha_road_tour.adapter;

/**
 * Created by andrew on 2016. 10. 18..
 */

public class Item {
    int image;
    String title;
    double x;
    double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getImage() {
        return this.image;
    }
    public String getTitle()
    {
        return title;
    }

    public Item(int image, String title, double x, double y) {
        this.image = image;
        this.title = title;
        this.x = x;
        this.y = y;
    }
    public Item(double x, double y, String title) {
        this.x = x;
        this.y = y;
        this.title = title;
    }
}

