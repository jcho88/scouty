package com.rektgg.salert;

import android.content.Context;
import android.widget.ArrayAdapter;

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

}
