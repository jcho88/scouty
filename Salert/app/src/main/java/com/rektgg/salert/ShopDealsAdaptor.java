package com.rektgg.salert;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by steve_000 on 5/19/2016.
 */
public class ShopDealsAdaptor extends ArrayAdapter {
    Context context;
    int resource;
    ShopDeals data[] = null;

    public ShopDealsAdaptor(Context context, int resource, ShopDeals[] data) {
        super(context, resource, data);
        this.resource = resource;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        dealsDetailHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new dealsDetailHolder();
            holder.username = (TextView)row.findViewById(R.id.tv_shopDeals_username);
            holder.dealDetails = (TextView)row.findViewById(R.id.tv_shopDeals_deal);

            row.setTag(holder);
        }
        else
        {
            holder = (dealsDetailHolder)row.getTag();
        }

        ShopDeals shopdeals = data[position];
        holder.username.setText(shopdeals.username);
        holder.dealDetails.setText(shopdeals.dealDetails);

        return row;
    }

    static class dealsDetailHolder
    {
        TextView username;
        TextView dealDetails;
    }
}
