package com.example.andrew.sha_road_tour;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.andrew.sha_road_tour.adapter.MainpagerAdapter;
import com.example.andrew.sha_road_tour.service.GpsInfo;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by andrew on 2016. 10. 18..
 */

public class MainActivity extends AppCompatActivity {


    private TabLayout tablayout;
    private ViewPager viewPager;
    private MainpagerAdapter adpter;
    private GpsInfo gps;
    private static final int MY_RMISSION_REQUEST_WRITE = 33;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        mylocation();

        tablayout = (TabLayout) findViewById(R.id.tab_layout);
        setTabIcon(tablayout);
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tablayout.setSelectedTabIndicatorHeight(0);
        tablayout.setLayoutDirection(TabLayout.LAYOUT_DIRECTION_INHERIT);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adpter = new MainpagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(adpter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });

        checkPermission();


    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(android.Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to write the permission.
                    Toast.makeText(this, "앱 내의 컨텐츠 저장용으로 사용됩니다.", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.READ_CONTACTS,
                        android.Manifest.permission.READ_PHONE_STATE
                }, MY_RMISSION_REQUEST_WRITE);


                // MY_PERMISSION_REQUEST_STORAGE is an
                // app-defined int constant

            } else {
                // 다음 부분은 항상 허용일 경우에 해당이 됩니다.

                return true;
            }
        }
        return false;
    }

    private void setTabIcon(TabLayout tabLayout) {
        View view1 = getLayoutInflater().inflate(R.layout.tab_icon_view, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_action_event);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));

        View view2 = getLayoutInflater().inflate(R.layout.tab_icon_view, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_action_store);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));

        View view3 = getLayoutInflater().inflate(R.layout.tab_icon_view, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.ic_action_map);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }


    public void mylocation(){

        gps = new GpsInfo(MainActivity.this);
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
                    getApplicationContext(),
                    "당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude,
                    Toast.LENGTH_LONG).show();
        } else {
            // GPS 를 사용할수 없으므로
            gps.showSettingsAlert();
        }

    }
}



