package com.example.andrew.sha_road_tour.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andrew.sha_road_tour.R;
import com.example.andrew.sha_road_tour.adapter.Item;
import com.example.andrew.sha_road_tour.service.GpsInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by andrew on 2016. 10. 18..
 */
public class page_map extends android.support.v4.app.Fragment implements OnMapReadyCallback {

    static final LatLng SEOUL = new LatLng(37.478583, 126.955566);
    private static final String KEY_MAP_SAVED_STATE = "mapState";
    private GoogleMap map;
    private View view;
    private GpsInfo gps;
    private LatLng latLng;
    MapView mapView;
    double lat;
    double lon;
    final int ITEM_SIZE = 6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contanier, Bundle saveInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, contanier, false);

        MapSetting(saveInstanceState);
        return view;
    }

    private void MapSetting(Bundle saveInstanceState) {
        mapView = (MapView) view.findViewById(R.id.map);

        Bundle mapState = (saveInstanceState != null)
                ? saveInstanceState.getBundle(KEY_MAP_SAVED_STATE) : null;
        mapView.onCreate(mapState);
        mapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.map.setMyLocationEnabled(true);

        MarkerOptions opFirts = new MarkerOptions();

        Item[] item = new Item[ITEM_SIZE];

        item[0] = new Item(R.drawable.journey_marker, "저니", 37.479099, 126.953787);
        item[1] = new Item(R.drawable.moz_marker, "모즈", 37.479231, 126.953418);
        item[2] = new Item(R.drawable.jeju_marker, "제주상회", 37.477463, 126.958380);
        item[3] = new Item(R.drawable.kiyoi_marker, "키요이", 37.478847, 126.956183);
        item[4] = new Item(R.drawable.amelie_marker, "아멜리에", 37.478658, 126.955979);
        item[5] = new Item(R.drawable.sharo_marker, "샤로스톤", 37.478830, 126.955979);

        for (int i = 0; i < ITEM_SIZE; i++) {
            latLng = new LatLng(item[i].getX(), item[i].getY());
            opFirts.position(latLng);
            opFirts.title(item[i].getTitle());
            opFirts.icon(BitmapDescriptorFactory.fromResource(item[i].getImage()));
            map.addMarker(opFirts).showInfoWindow();

            Log.e("Tag", String.valueOf(i));

        }

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 16));


    }
}
