package com.example.andrew.sha_road_tour.adapter;

/**
 * Created by andrew on 2016. 10. 18..
 */

public class Item {
    int image;
    String title;

    int getImage() {
        return this.image;
    }
    String getTitle() {
        return this.title;
    }

    public Item(int image, String title) {
        this.image = image;
        this.title = title;
    }
}

