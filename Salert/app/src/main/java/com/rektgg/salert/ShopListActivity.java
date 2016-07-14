package com.rektgg.salert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ShopListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        //just for testing on UI
        ArrayList<ShopDeals> deals  = new ArrayList<ShopDeals>(){{
            add( new ShopDeals("Draven", "Welcome to the league of Draven"));
            add(new ShopDeals("Teemo", "Captain Teemo!!! Hut, 2, 3, 4"));
            add (new ShopDeals("Lucian", "Everybody dies, some need a little help"));
        }};;

        ShopsProfile shopsProfiles_data[] = new ShopsProfile[]
                {
                        new ShopsProfile("taco", deals,"123 street", "2 miles"),
                        new ShopsProfile("taco", deals,"123 street", "2 miles"),
                        new ShopsProfile("taco", deals,"123 street", "2 miles")
                };

        Log.d("arraylist", Integer.toString(deals.size()));
        ShopListAdaptor adapter = new ShopListAdaptor(this,
                R.layout.listview_shoplist, shopsProfiles_data);

        listView = (ListView)findViewById(R.id.lv_shoplist);
        listView.setAdapter(adapter);
    }
}
