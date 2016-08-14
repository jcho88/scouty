package com.rektgg.salert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class ShopProfileActivity extends AppCompatActivity {
    private ListView listView1;
    private TextView shopAddress;
    private TextView shopeTel;
    private TextView shopeName;
    private TextView shopDistanceFromUser;
    private ArrayList<ShopDeals> shopdeals_data = new ArrayList<ShopDeals>();
    private static final String LOG_TAG = "ShopProfileActivity";
    String userName;
    String userPost;
    String storeID;
    String shopName;
    String shopPhoneNum;
    String shop_Address;
    String shop_DistanceFromUser;
    DealPost post = new DealPost();
    private ParseQuery<DealPost> postQuery;
    Intent shop_list_data;
    Intent Intent_dealPost;
    ParseUser currentUser = ParseUser.getCurrentUser();


//    final ProgressDialog dialog = new ProgressDialog(ShopProfileActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_profile);
        shopAddress = (TextView)findViewById(R.id.tv_shopAddress);
        shopeName = (TextView)findViewById(R.id.tv_shopName);
        shopeTel = (TextView)findViewById(R.id.tv_shopPhone);
        shopDistanceFromUser = (TextView)findViewById(R.id.tv_distanceFromUser);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("DealPost");
        query.include("user");

        //show dialog for querying until callback is done
//        final ProgressDialog dialog = new ProgressDialog(ShopProfileActivity.this);
//        dialog.setMessage(String.format("Getting Deals"));
//        dialog.show();

        /*******Variables passed via Intent are:
         name
         storeID
         address
         phone
         ********/
        shop_list_data = getIntent();
        shopName = shop_list_data.getStringExtra("name");
        shopPhoneNum = shop_list_data.getStringExtra("phone");
        shop_Address = shop_list_data.getStringExtra("address");
        shop_DistanceFromUser = Double.toString(shop_list_data.getDoubleExtra("distance", 0)) + " miles";
        shopeName.setText(shopName);
        shopDistanceFromUser.setText(shop_DistanceFromUser);
        shopAddress.setText(shop_Address);
        shopeTel.setText(shopPhoneNum);

//        query.findInBackground(new FindCallback<ParseObject>() {
//
//            @Override
//            public void done(List<ParseObject> objectList, ParseException e) {
//
//                //                Log.i("PO list size", Integer.toString(objectList.size()));
//                                Log.i("PO error msg", String.valueOf(e));
//                if (e == null) {
//
//                    for (ParseObject object : objectList) {
//                        DealPost temp = new DealPost();
//                        //dialog.dismiss();
////                        Log.d("PO", object.getString("text"));
//
//                        if(object.getParseUser("user") != null) {
//                            temp.setUser(object.getParseUser("user"));
//                            userName = temp.getUser().getUsername();
//                        }else{
//                            userName = "Visitor";
//                        }
//
//                        temp.setText(object.getString("text"));
//
//                        userPost = temp.getText();
//                        storeID = temp.getStoreId();
//
//                        Log.d("doom", userPost);
//                        shopdeals_data.add(new ShopDeals(userName, userPost, storeID));
//
//                        //setting up Array adapter with class ShopDealsAdaptor
//                        ShopDealsAdaptor adapter = new ShopDealsAdaptor(ShopProfileActivity.this,
//                                R.layout.listview_shopdeals, shopdeals_data);
//                        //setting up shops profile
//
//                        //setting up list view
//                        listView1 = (ListView)findViewById(R.id.lv_deals);
//                        listView1.setAdapter(adapter);
//
////                        Log.d("doom", userName);
////                        Log.d("doom", userPost);
//                        //                        currentUser = object.getParseObject("_p_user");
//                        //                        userPost = currentUser.getString("text");
//                    }
//                    Log.d("FCUASDSA","Asdsa");
//                    dialog.dismiss();
//                }
//
//            }
//
//        });
//
//        //new getPostInfo().execute();
//
//        //hide Action Bar
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
//
//        ImageButton postDealButton = (ImageButton) findViewById(R.id.ib_addDeals);
//
//        postDealButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Starts an intent for the sign up activity
//                Intent_dealPost = new Intent(ShopProfileActivity.this, PostActivity.class);
//                shop_list_data = getIntent();
//                Intent_dealPost.putExtra("storeID",shop_list_data.getStringExtra("storeID"));
//                startActivity(Intent_dealPost);
//                //TODO
//                //Not efficient way to exit
//                //finish();
//            }
//        });


    }



    public void onResume() {
        super.onResume();
        shopeName.setText(shopName);
        shopDistanceFromUser.setText(shop_DistanceFromUser);
        shopAddress.setText(shop_Address);
        shopeTel.setText(shopPhoneNum);


        //TODO
        final ProgressDialog dialog2 = new ProgressDialog(ShopProfileActivity.this);
        dialog2.setMessage(String.format("Getting Deals"));
        dialog2.show();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("DealPost");
        query.include("user");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objectList, ParseException e) {

                if (e == null) {

                    for (ParseObject object : objectList) {

                        DealPost temp = new DealPost();
                        //dialog.dismiss();

                        if(object.getParseUser("user") != null) {
                            temp.setUser(object.getParseUser("user"));
                            userName = temp.getUser().getUsername();
                        }else{
                            userName = "Visitor";
                        }

                        temp.setText(object.getString("text"));

                        userPost = temp.getText();
                        storeID = temp.getStoreId();


                        shopdeals_data.add(new ShopDeals(userName, userPost, storeID));

                        //setting up Array adapter with class ShopDealsAdaptor
                        ShopDealsAdaptor adapter = new ShopDealsAdaptor(ShopProfileActivity.this,
                                R.layout.listview_shopdeals, shopdeals_data);
                        //setting up shops profile

                        //setting up list view
                        listView1 = (ListView)findViewById(R.id.lv_deals);
                        listView1.setAdapter(adapter);


                    }
                    dialog2.dismiss();
                }

            }

        });



        //hide Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageButton postDealButton = (ImageButton) findViewById(R.id.ib_addDeals);

        postDealButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Starts an intent for the sign up activity
                Intent_dealPost = new Intent(ShopProfileActivity.this, PostActivity.class);
                shop_list_data = getIntent();
                Intent_dealPost.putExtra("storeID",shop_list_data.getStringExtra("storeID"));
                startActivity(Intent_dealPost);

            }
        });


    }


    public void onPause() {
        super.onPause();

        shop_list_data = getIntent();
        shopName = shop_list_data.getStringExtra("name");
        shopPhoneNum = shop_list_data.getStringExtra("phone");
        shop_Address = shop_list_data.getStringExtra("address");
        shop_DistanceFromUser = Double.toString(shop_list_data.getDoubleExtra("distance", 0)) + " miles";
        Log.d("onStop in here", shopName);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_CANCELED){ //when a user hit back button
                Log.d(LOG_TAG, "Result Canceled");
                finish();
        }
    }

}
