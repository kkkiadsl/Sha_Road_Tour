package com.example.andrew.sha_road_tour.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.andrew.sha_road_tour.R;
import com.example.andrew.sha_road_tour.service.AlarmReceive;
import com.example.andrew.sha_road_tour.service.GpsInfo;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

/**
 * Created by andrew on 2016. 10. 18..
 */
public class page_event extends android.support.v4.app.Fragment {

    ImageView imageView;
    private GpsInfo gps;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contanier, Bundle saveInstanceState){
        final LinearLayout linearLayout =
                (LinearLayout)inflater.inflate(R.layout.fragment_event, contanier, false);


       imageView = (ImageView)linearLayout.findViewById(R.id.event);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              setAlarm(getContext(), 1);
                mylocation();
            }
        });

        Picasso.with(getActivity())
                .load(R.drawable.event)
                .fit()
                .into(imageView);

            return linearLayout;
    }

    // 알람 등록
    private void setAlarm(Context context, long second) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        Intent intent = new Intent(context, AlarmReceive.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
/*

        37.478583, 126.955566 // 서울
        37.479359, 126.952262 // 서울대입구 투썸플레이스
        34.816898, 126.459826 // 무안 사무실
        37.487684, 126.956408 // 집
*/
        locationManager.addProximityAlert(34.816898, 126.459826, 500, -1, pIntent);

        //second​ 초후에 알람 울리게 설정(1000*실제 초 ex>5초 = 5000)
        //alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + second, pIntent);
    }

    public void mylocation(){

        gps = new GpsInfo(getContext());
        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            LatLng latLng = new LatLng(latitude, longitude);
/*

            37.478583, 126.955566 // 서울
            37.479359, 126.952262 // 서울대입구 투썸플레이스
            34.816898, 126.459826 // 무안 사무실
            37.487684, 126.956408 // 집

*/

            Toast.makeText(
                    getContext(),
                    "당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude,
                    Toast.LENGTH_LONG).show();
        } else {
            // GPS 를 사용할수 없으므로
            gps.showSettingsAlert();
        }

    }

}
