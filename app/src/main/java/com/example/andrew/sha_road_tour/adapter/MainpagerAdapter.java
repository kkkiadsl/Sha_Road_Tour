package com.example.andrew.sha_road_tour.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.andrew.sha_road_tour.fragment.page_event;
import com.example.andrew.sha_road_tour.fragment.page_map;
import com.example.andrew.sha_road_tour.fragment.page_store;

/**
 * Created by andrew on 2016. 10. 18..
 */

public class MainpagerAdapter extends FragmentStatePagerAdapter{

    int MAX_PAGE = 3;
    Fragment main_fragment = new Fragment();


        public MainpagerAdapter(FragmentManager fm, int max_page) {
            super(fm);
            this.MAX_PAGE = max_page;

        }

        @Override
        public Fragment getItem(int position) {

            if(position<0 || MAX_PAGE<=position)
                return null;

            switch (position){
                case 0 :
                    main_fragment = new page_store();
                    break;
                case 1 :
                    main_fragment = new page_map();
                    break;
                case 2 :
                    main_fragment = new page_event();
                    break;
            }
            return main_fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
}

