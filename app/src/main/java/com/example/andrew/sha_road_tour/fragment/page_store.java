package com.example.andrew.sha_road_tour.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.andrew.sha_road_tour.R;
import com.example.andrew.sha_road_tour.adapter.Item;
import com.example.andrew.sha_road_tour.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016. 10. 18..
 */
public class page_store extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private View view;
    final int ITEM_SIZE = 6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contaniner, Bundle saveInstanceState) {
        view = inflater.inflate(R.layout.fragment_store, contaniner, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        ImageView img = null;


        List<Item> items = new ArrayList<>();
        Item[] item = new Item[ITEM_SIZE];
        item[0] = new Item(R.drawable.journey, "저니");
        item[1] = new Item(R.drawable.moz, "모즈");
        item[2] = new Item(R.drawable.jeju, "제주상회");
        item[3] = new Item(R.drawable.kiyoi, "키요이");
        item[4] = new Item(R.drawable.amelie, "아멜리에");
        item[5] = new Item(R.drawable.sharo, "샤로스톤");

        for (int i = 0; i < ITEM_SIZE; i++) {
            items.add(item[i]);
            Log.e("Tag", String.valueOf(i));
        }


        recyclerView.setAdapter(new RecyclerAdapter(view.getContext(), items, R.layout.fragment_store));

        return view;
    }
}
