package com.example.pepi.busmanagmentsystem.Fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.pepi.busmanagmentsystem.Models.CityItem;
import com.example.pepi.busmanagmentsystem.Models.RouteItem;
import com.example.pepi.busmanagmentsystem.R;
import com.example.pepi.busmanagmentsystem.Services.ApiHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pepi_ on 10/21/2018.
 */

public class ListRouteFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.list_view_fragment,container,false);

        return view;
    }

    public void fillListWithItems(int fromId,int toId)
    {
        new RouteAPI().execute("http://192.168.0.111:8000/getRoutes/"+fromId+"/"+toId);
    }


    private class RouteAPI extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String url=strings[0];

            ApiHandler apiHandler=new ApiHandler();

            String result=apiHandler.getJsonString(url);

            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            List<RouteItem> routes=new ArrayList<>();

            try {
                JSONArray routeItems=new JSONArray(s);

                for(int i=0;i<routeItems.length();i++)
                {
                    JSONObject item=routeItems.getJSONObject(i);

                    int id=item.getInt("id");
                    String from=item.getString("from");
                    String to=item.getString("to");

                    routes.add(new RouteItem(id,from,to));
                }

                ArrayAdapter<RouteItem> adapter=new ArrayAdapter<RouteItem>(getContext(),
                        android.R.layout.simple_list_item_1,routes);

                setListAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
