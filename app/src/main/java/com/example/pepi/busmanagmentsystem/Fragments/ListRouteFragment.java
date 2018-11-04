package com.example.pepi.busmanagmentsystem.Fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pepi.busmanagmentsystem.Adapters.RouteAdapter;
import com.example.pepi.busmanagmentsystem.MapsActivity;
import com.example.pepi.busmanagmentsystem.Models.RouteItem;
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

public class ListRouteFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.list_view_fragment,container,false);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent intent=new Intent(getActivity(), MapsActivity.class);

        startActivity(intent);
    }

    public void fillListWithItems(int fromId, int toId)
    {
        new RouteAPI().execute("http://192.168.0.111:8000/getRoutes/"+fromId+"/"+toId);
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

            List<RouteItem> routes=new ArrayList<>();

            try {
                JSONArray routeItems=new JSONArray(s);

                for(int i=0;i<routeItems.length();i++)
                {
                    JSONObject item=routeItems.getJSONObject(i);

                    int id=item.getInt("id");
                    String from=item.getString("from");
                    String to=item.getString("to");
                    String time=item.getString("time");
                    String company=item.getString("company");

                    routes.add(new RouteItem(id,from,to,time,company));
                }

                RouteAdapter adapter=new RouteAdapter(getActivity(),routes);

                setListAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
