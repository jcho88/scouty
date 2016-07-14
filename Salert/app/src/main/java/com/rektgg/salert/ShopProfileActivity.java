package com.rektgg.salert;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ShopProfileActivity extends AppCompatActivity {
    private ListView listView1;
    private TextView shopAddress;
    private TextView shopDistanceFromUser;
    private ArrayList<ShopDeals> shopdeals_data = new ArrayList<ShopDeals>();
    private String currentUser;
    private String userPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_profile);
        shopAddress = (TextView)findViewById(R.id.tv_shopAddress);
        shopDistanceFromUser = (TextView)findViewById(R.id.tv_distanceFromUser);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("DealPost");
        query.include("_p_user");
        query.include("text");
        query.addDescendingOrder("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objectList, ParseException e) {
                if (e == null) {

                    for (ParseObject object : objectList) {
                        ParseObject username = object.getParseObject("_p_user");
                        String post = username.getString("text");
                        shopdeals_data.add(new ShopDeals(username.toString(), post));
                    }
                }
            };

        });

        ImageButton postDealButton = (ImageButton) findViewById(R.id.ib_addDeals);

        postDealButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Starts an intent for the sign up activity
                startActivity(new Intent(ShopProfileActivity.this, PostActivity.class));
            }
        });


//        shopdeals_data.add(new ShopDeals("TestUser", "TestPost"));

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
