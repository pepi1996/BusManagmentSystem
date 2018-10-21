package com.example.pepi.busmanagmentsystem;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pepi.busmanagmentsystem.Fragments.ListRouteFragment;
import com.example.pepi.busmanagmentsystem.Fragments.SearchFragment;
import com.example.pepi.busmanagmentsystem.Models.CityItem;
import com.example.pepi.busmanagmentsystem.Models.RouteItem;
import com.example.pepi.busmanagmentsystem.Services.ApiHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchFragment.onSearchFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


    }

    @Override
    public void onSpinnerToClick(int fromId, int toId) {

        ListRouteFragment listFragment=(ListRouteFragment) getFragmentManager().findFragmentById(R.id.list_route_fragment);

        listFragment.fillListWithItems(fromId,toId);

    }
}
