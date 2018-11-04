package com.example.pepi.busmanagmentsystem;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.pepi.busmanagmentsystem.Services.ApiHandler;
import com.example.pepi.busmanagmentsystem.Services.DataParser;
import com.example.pepi.busmanagmentsystem.Services.GetDirectionsData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Object dataTransfer[] = new Object[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String url = getDirectionsUrl(42.8632169,20.8389002,42.8887584,20.8630493);

        new GetDirectionsData().execute(url);

    }

    private String getDirectionsUrl(double start_latitude,double start_longitude,double end_latitude,double end_longitude)
    {
        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googleDirectionsUrl.append("origin="+start_latitude+","+start_longitude);
        googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitude);
        googleDirectionsUrl.append("&key="+"AIzaSyDddwvLAtWmS-91BQ-F2sRFIlCxZe7M-CI");

        return googleDirectionsUrl.toString();
    }
    private class GetDirectionsData extends AsyncTask<String,Void,String> {

        GoogleMap mMap;
        String url;
        String googleDirectionsData;


        @Override
        protected String doInBackground(String... strings) {

            String url=strings[0];

            ApiHandler downloadUrl = new ApiHandler();
            try {
                googleDirectionsData = downloadUrl.getJsonString(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return googleDirectionsData;
        }

        @Override
        protected void onPostExecute(String s) {

            String[] directionsList;
            DataParser parser = new DataParser();
            directionsList = parser.parseDirections(s);
            displayDirection(directionsList);

        }

        public void displayDirection(String[] directionsList)
        {

            int count = directionsList.length;
            for(int i = 0;i<count;i++)
            {
                PolylineOptions options = new PolylineOptions();
                options.color(Color.RED);
                options.width(10);
                options.addAll(PolyUtil.decode(directionsList[i]));

                mMap.addPolyline(options);
            }
        }


    }


}
