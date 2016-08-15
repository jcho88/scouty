package com.rektgg.salert;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by steve_000 on 5/19/2016.
 */
public class ShopDealsAdaptor extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ShopDeals> data; //user "crowdsourced" deals (ShopDeals.java class)

    public ShopDealsAdaptor(Context context, int resource, ArrayList<ShopDeals> data) {
        super(context, resource, data);
        this.resource = resource;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        dealsDetailHolder holder = null; //this holder is for reuse

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new dealsDetailHolder();
            holder.username = (TextView)row.findViewById(R.id.tv_shopDeals_username);
            holder.dealDetails = (TextView)row.findViewById(R.id.tv_shopDeals_deal);
            holder.deal_createAt = (TextView)row.findViewById(R.id.tv_shopdeals_createdAt);
            row.setTag(holder);
        }
        else
        {
            holder = (dealsDetailHolder)row.getTag();
        }

        ShopDeals shopdeals = data.get(position);
        holder.username.setText(shopdeals.username);
        holder.dealDetails.setText(shopdeals.dealDetails);
        holder.deal_createAt.setText(shopdeals.create_at);


        return row;
    }

    static class dealsDetailHolder
    {
        TextView username;
        TextView dealDetails;
        TextView deal_createAt;
    }
}
