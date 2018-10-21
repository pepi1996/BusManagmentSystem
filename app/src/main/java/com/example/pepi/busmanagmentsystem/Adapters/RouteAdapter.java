package com.example.pepi.busmanagmentsystem.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pepi.busmanagmentsystem.Models.RouteItem;
import com.example.pepi.busmanagmentsystem.R;

import java.util.List;

/**
 * Created by pepi_ on 10/21/2018.
 */

public class RouteAdapter extends BaseAdapter {

    private Activity context;
    private List<RouteItem> routes;

    public RouteAdapter(Activity context, List<RouteItem> routes) {
        this.context = context;
        this.routes = routes;
    }

    @Override
    public int getCount() {
        return routes.size();
    }

    @Override
    public Object getItem(int position) {
        return routes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            LayoutInflater inflater=context.getLayoutInflater();
            View mView=inflater.inflate(R.layout.route_item,parent,false);

            TextView from=mView.findViewById(R.id.txtFrom);
            TextView to=mView.findViewById(R.id.txtTo);
            TextView time=mView.findViewById(R.id.txtTime);
            TextView company=mView.findViewById(R.id.txtCompany);

            from.setText(routes.get(position).getFrom());
            to.setText(routes.get(position).getTo());
            time.setText(routes.get(position).getTime());
            company.setText(routes.get(position).getCompany());

            return mView;
        }

        return convertView;
    }
}
