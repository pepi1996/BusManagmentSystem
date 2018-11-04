package com.example.pepi.busmanagmentsystem.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pepi.busmanagmentsystem.Models.CityItem;
import com.example.pepi.busmanagmentsystem.R;
import com.example.pepi.busmanagmentsystem.Services.ApiHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepi_ on 10/21/2018.
 */

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner_from,spinner_to;
    Boolean isFrom=true;

    onSearchFragmentListener searchFragmentListener;

    public interface onSearchFragmentListener
    {
        void onSpinnerToClick(int fromId,int toId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_view_fragment,container,false);

        spinner_from=view.findViewById(R.id.spinner_from);
        spinner_to=view.findViewById(R.id.spinner_to);

        spinner_from.setOnItemSelectedListener(this);
        spinner_to.setOnItemSelectedListener(this);

        new RouteAPI().execute("http://192.168.0.111:8000/getCities");

        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId())
        {
            case R.id.spinner_from:

                isFrom=false;

                CityItem item= (CityItem) spinner_from.getSelectedItem();

                new RouteAPI().execute("http://192.168.0.111:8000/getCitiesById/"+item.getId());
                break;

            case R.id.spinner_to:

                CityItem from=(CityItem) spinner_from.getSelectedItem();
                CityItem to=(CityItem) spinner_to.getSelectedItem();

                searchFragmentListener.onSpinnerToClick(from.getId(),to.getId());
                break;

            default:
                break;
        }

    }



    private class RouteAPI extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String url=strings[0];

            ApiHandler apiHandler=new ApiHandler();

            String result= null;
            try {
                result = apiHandler.getJsonString(url);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            List<CityItem> cityItems=new ArrayList<>();

            cityItems.add(new CityItem(0,"Select One"));

            try {
                JSONArray routeItems=new JSONArray(s);

                for(int i=0;i<routeItems.length();i++)
                {
                    JSONObject item=routeItems.getJSONObject(i);

                    int id=item.getInt("id");
                    String name=item.getString("name");

                    cityItems.add(new CityItem(id,name));
                }

                ArrayAdapter<CityItem> adapter=new ArrayAdapter<CityItem>(getContext(),
                        android.R.layout.simple_spinner_item,cityItems);

                adapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);

                if(isFrom)
                {
                    spinner_from.setAdapter(adapter);

                }
                else
                {
                    spinner_to.setAdapter(adapter);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try {
            searchFragmentListener = (onSearchFragmentListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()+" must implements searchFragmentListener methods");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        searchFragmentListener=null;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

