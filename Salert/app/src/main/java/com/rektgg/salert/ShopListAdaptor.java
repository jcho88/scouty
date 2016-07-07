package com.rektgg.salert;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by steve_000 on 5/23/2016.
 */
public class ShopListAdaptor  extends ArrayAdapter {

    Context context;
    int resource;
    ShopsProfile data[] = null;

    public ShopListAdaptor(Context context, int resource, ShopsProfile[] data) {

        super(context, resource, data);
        this.resource = resource;
        this.context = context;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        shopsDetailHolder holder = null; //this holder is for reuse

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new shopsDetailHolder();
            holder.shopname = (TextView)row.findViewById(R.id.tv_shopDeals_username);
            holder.shopdeals = (TextView)row.findViewById(R.id.tv_shopDeals_deal);

            row.setTag(holder);
        }
        else
        {
            holder = (shopsDetailHolder)row.getTag();
        }

        ShopsProfile shopprofile = data[position];
        holder.shopname.setText(shopprofile.shop_name);
        //holder.shopdeals.setText(shopprofile.shop_deals);
        holder.shopaddress.setText(shopprofile.shop_address);
        holder.userDistance.setText(shopprofile.user_distance);

        return row;
    }

    static class shopsDetailHolder
    {
        TextView shopname;
        TextView shopdeals;
        TextView shopaddress;
        TextView userDistance;
    }
}

