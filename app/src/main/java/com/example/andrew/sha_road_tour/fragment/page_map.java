package com.example.andrew.sha_road_tour.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

    static final LatLng SEOUL = new LatLng(37.481345, 126.952655);
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
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
                lat = location.getLatitude();
                lon = location.getLongitude();

                latLng = new LatLng(lat, lon);
            }

            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // TODO Auto-generated method stub

            }

            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub

            }

            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub

            }
        };

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return contanier;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        String locationProvider = LocationManager.GPS_PROVIDER;
        Location lastKnowLocation = locationManager.getLastKnownLocation(locationProvider);


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

        item[0] = new Item(37.479099, 126.953787,"저니");
        item[1] = new Item(37.479231, 126.953418, "모즈");
        item[2] = new Item(37.477463, 126.958380, "제주상회");
        item[3] = new Item(37.478847, 126.956183, "키요이");
        item[4] = new Item(37.478658, 126.955979, "아멜리에");
        item[5] = new Item(37.478830, 126.955979, "샤로스톤");

        for (int i = 0; i < ITEM_SIZE; i++) {
            latLng = new LatLng(item[i].getX(), item[i].getY());
            opFirts.position(latLng);
            opFirts.title(item[i].getTitle());
            opFirts.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
            map.addMarker(opFirts).showInfoWindow();

            Log.e("Tag", String.valueOf(i));

        }

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 16));


    }
}
