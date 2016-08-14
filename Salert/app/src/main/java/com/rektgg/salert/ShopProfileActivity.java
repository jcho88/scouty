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
    DealPost post = new DealPost();
    private ParseQuery<DealPost> postQuery;


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
        final ProgressDialog dialog = new ProgressDialog(ShopProfileActivity.this);
        dialog.setMessage(String.format("Getting Deals"));
        dialog.show();

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objectList, ParseException e) {
                Log.d("PO", "in done");
                //                Log.i("PO list size", Integer.toString(objectList.size()));
                //                Log.i("PO error msg", e.getMessage());
                if (e == null) {

                    for (ParseObject object : objectList) {
                        //dialog.dismiss();
//                        Log.d("PO", object.getString("text"));

                        ParseUser theUser = object.getParseUser("user");
                        userName = theUser.getUsername();
                        userPost = object.getString("text");
                        storeID = object.getString("store_id");

//                        Log.d("doom", userPost);
                        shopdeals_data.add(new ShopDeals(userName, userPost, storeID));

                        //setting up Array adapter with class ShopDealsAdaptor
                        ShopDealsAdaptor adapter = new ShopDealsAdaptor(ShopProfileActivity.this,
                                R.layout.listview_shopdeals, shopdeals_data);
                        //setting up shops profile


                        /*******Variables passed via Intent are:
                        name
                        storeID
                        address
                        phone
                        ********/
                        Intent shop_list_data = getIntent();
                        shopeName.setText(shop_list_data.getStringExtra("name"));
                        shopDistanceFromUser.setText(Double.toString(shop_list_data.getDoubleExtra("distance", 0)) + " miles");
                        shopAddress.setText(shop_list_data.getStringExtra("address"));
                        shopeTel.setText(shop_list_data.getStringExtra("phone"));

                        //setting up list view
                        listView1 = (ListView)findViewById(R.id.lv_deals);
                        listView1.setAdapter(adapter);
                        dialog.dismiss();
//                        Log.d("doom", userName);
//                        Log.d("doom", userPost);
                        //                        currentUser = object.getParseObject("_p_user");
                        //                        userPost = currentUser.getString("text");
                    }
                }

            }

        });

        //new getPostInfo().execute();

        //hide Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageButton postDealButton = (ImageButton) findViewById(R.id.ib_addDeals);

        postDealButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Starts an intent for the sign up activity
                startActivity(new Intent(ShopProfileActivity.this, PostActivity.class));
            }
        });


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_CANCELED){ //when a user hit back button
                Log.d(LOG_TAG, "Result Canceled");
                finish();
        }
    }

}
