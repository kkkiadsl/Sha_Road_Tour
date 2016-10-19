package com.example.andrew.sha_road_tour.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.andrew.sha_road_tour.R;
import com.squareup.picasso.Picasso;

/**
 * Created by andrew on 2016. 10. 18..
 */
public class page_event extends android.support.v4.app.Fragment {

    ImageView imageView;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contanier, Bundle saveInstanceState){
        LinearLayout linearLayout =
                (LinearLayout)inflater.inflate(R.layout.fragment_event, contanier, false);


       imageView = (ImageView)linearLayout.findViewById(R.id.event);

        Picasso.with(getActivity())
                .load(R.drawable.event)
                .fit()
                .into(imageView);

            return linearLayout;
    }
}

