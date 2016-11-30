package com.example.andrew.sha_road_tour.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrew.sha_road_tour.R;
import com.example.andrew.sha_road_tour.service.GpsInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Item> items;
    int item_layout;
    private GpsInfo gps;

    public RecyclerAdapter(Context context, List<Item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Item item = items.get(position);
        Picasso.with(context)
                .load(item.getImage())
                .fit()
                .into(holder.image);
        holder.title.setText(item.getTitle());


        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double lat = item.getX();
                double lon = item.getY();
                double dist = calDistance(lat, lon);

                String num = String.format("%.1f" , dist);

                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                holder.cardview.startAnimation(anim);


                if(dist >= 50) {
                    Toast.makeText(context, item.getTitle() + "\n남은거리 : " + num + "m", Toast.LENGTH_SHORT).show();
                }else if(dist < 50){
                    LinearLayout linearLayout = (LinearLayout)View.inflate(context, R.layout.toast, null);
                    Toast toast = new Toast(context);
                    toast.setView(linearLayout);
                    toast.show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.store_image);
            title = (TextView) itemView.findViewById(R.id.store_title);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
        }
    }

    public double calDistance(double lat1, double lon1){

        gps = new GpsInfo(context);
        // GPS 사용유무 가져오기

        double theta, dist = 0;

        if (gps.isGetLocation()) {

            double lat2 = gps.getLatitude();
            double lon2 = gps.getLongitude();



            theta = lon1 - lon2;
            dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
                    * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
            dist = Math.acos(dist);
            dist = rad2deg(dist);

            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;    // 단위 mile 에서 km 변환.
            dist = dist * 1000.0;      // 단위  km 에서 m 로 변환

        } else {
            // GPS 를 사용할수 없으므로
            gps.showSettingsAlert();
        }

        return dist;
    }

    // 주어진 도(degree) 값을 라디언으로 변환
    private double deg2rad(double deg){
        return (double)(deg * Math.PI / (double)180d);
    }

    // 주어진 라디언(radian) 값을 도(degree) 값으로 변환
    private double rad2deg(double rad){
        return (double)(rad * (double)180d / Math.PI);
    }

}

