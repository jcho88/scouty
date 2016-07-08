package com.rektgg.salert;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ShopProfileActivity extends AppCompatActivity {
    private ListView listView1;
    private TextView shopAddress;
    private TextView shopDistanceFromUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_profile);
        shopAddress = (TextView)findViewById(R.id.tv_shopAddress);
        shopDistanceFromUser = (TextView)findViewById(R.id.tv_distanceFromUser);
        ShopDeals shopdeals_data[] = new ShopDeals[]
                {
                        new ShopDeals("Draven", "Welcome to the league of Draven"),
                        new ShopDeals("Teemo", "Captain Teemo!!! Hut, 2, 3, 4"),
                        new ShopDeals("Lucian", "Everybody dies, some need a little help"),
                        new ShopDeals("Rammus", "ok"),
                        new ShopDeals("Vayne", "Let us hunt those who follow the darkness"),
                        new ShopDeals("me", "Let us hunt those who follow the darkness sfahufhshd6fjhasd6jf;sad6j;klfl;s6dak;lfksl'dkf;sda6jf;khsalidkjvnsc kja6sbfd6lkjfhljsadnflmsnd.mfnsd.kj6bfkj6shdfljk")
                };

        //setting up Array adapter with class ShopDealsAdaptor
        ShopDealsAdaptor adapter = new ShopDealsAdaptor(this,
                R.layout.listview_shopdeals, shopdeals_data);
        //setting up shops profile
        shopDistanceFromUser.setText("10" + " miles");
        shopAddress.setText("123456 Dino road Apt 123, Lake sideDino, California 12345");


        //setting up list view
        listView1 = (ListView)findViewById(R.id.lv_deals);
        listView1.setAdapter(adapter);

        //hide Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}
