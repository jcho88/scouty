package net.rektapps.scouty;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by steve_000 on 5/23/2016.
 */
public class ShopListAdaptor  extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList <ShopsProfile> data = null;

    public ShopListAdaptor(Context context, int resource, ArrayList <ShopsProfile> data) {

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
            holder.shopaddress = (TextView)row.findViewById(R.id.tv_shoplist_shop_address);
            holder.userDistance = (TextView)row.findViewById(R.id.tv_shoplist_distance);
            holder.shopname = (TextView)row.findViewById(R.id.tv_shoplist_shop_name);
            holder.numOfShopdeals = (TextView)row.findViewById(R.id.tv_shoplist_numbersOfDeals);

            row.setTag(holder);
        }
        else
        {
            holder = (shopsDetailHolder)row.getTag();
        }

        ShopsProfile shopprofile = data.get(position);
        holder.shopname.setText(shopprofile.shop_name);
        holder.numOfShopdeals.setText(Integer.toString(shopprofile.shop_deals.size()));
        holder.shopaddress.setText(shopprofile.shop_address);
        holder.userDistance.setText(shopprofile.user_distance);

        return row;
    }

    static class shopsDetailHolder
    {
        TextView shopname;
        TextView numOfShopdeals;
        TextView shopaddress;
        TextView userDistance;
    }
}

