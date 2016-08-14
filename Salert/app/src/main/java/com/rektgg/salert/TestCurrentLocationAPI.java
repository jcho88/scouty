package com.rektgg.salert;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TestCurrentLocationAPI extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String LOG_TAG = "PlacesAPIActivity";
    private static final int GOOGLE_API_CLIENT_ID = 0;
    private GoogleApiClient mGoogleApiClient;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private TextView textView4, textView5;
    private ListView listView1;
    private Location mLastLocation;
    ArrayList<ShopsProfile> shopsProfiles_data = new ArrayList<ShopsProfile>();
    private LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_current_location_api);
        Button currentButton = (Button) findViewById(R.id.currentButton);
        textView4 =(TextView)findViewById(R.id.textView4);
        listView1 = (ListView) findViewById(R.id.listView1);

        // First we need to check availability of play services
        if (checkPlayServices()) {

//            mGoogleApiClient = new GoogleApiClient
//                    .Builder(this)
//                    .addApi(Places.GEO_DATA_API)
//                    .addApi(Places.PLACE_DETECTION_API)
//                    .enableAutoManage(this, this)
//                    .build();

            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage( this, 0, this )
                    .addApi( Places.GEO_DATA_API )
                    .addApi( Places.PLACE_DETECTION_API )
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();


        }

        currentButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                displayLocation();

            }
        });
    }

    /**
     * Method to display the location on UI
     * */
    private void displayLocation() {

        if (mGoogleApiClient.isConnected()) {
            if (ContextCompat.checkSelfPermission(TestCurrentLocationAPI.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(TestCurrentLocationAPI.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQUEST_CODE);
            } else {
                mLastLocation = LocationServices.FusedLocationApi
                        .getLastLocation(mGoogleApiClient);

//                PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace( mGoogleApiClient, null );
//                result.setResultCallback( new ResultCallback<PlaceLikelihoodBuffer>() {
//                    @Override
//                    public void onResult( PlaceLikelihoodBuffer likelyPlaces ) {
//
//                        PlaceLikelihood placeLikelihood = likelyPlaces.get( 0 );
//                        String content = "";
//
//                        if (mLastLocation != null) {
//                            double latitude = mLastLocation.getLatitude();
//                            double longitude = mLastLocation.getLongitude();
//
//                            textView4.setText(latitude + ", " + longitude);
//
//                        } else {
//
//                            textView4.setText("(Couldn't get the location. Make sure location is enabled on the device)");
//                        }
//
//                        if( placeLikelihood != null && placeLikelihood.getPlace() != null && !TextUtils.isEmpty( placeLikelihood.getPlace().getName() ) )
//                            content = "Most likely place: " + placeLikelihood.getPlace().getName() + "\n";
//                        if( placeLikelihood != null )
//                            content += "Percent change of being there: " + (int) ( placeLikelihood.getLikelihood() * 100 ) + "%";
//                        textView5.setText( content );
//
//                        likelyPlaces.release();
//                    }
//                });

                PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                        .getCurrentPlace(mGoogleApiClient, null);
                result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                    @Override
                    public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                        if(likelyPlaces.getStatus().getStatusMessage() == "ERROR") {
                            Toast.makeText(TestCurrentLocationAPI.this, "GPS is not enabled", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            ArrayList<ShopDeals> deals  = new ArrayList<ShopDeals>();

                            if (mLastLocation != null) {
                                double latitude = mLastLocation.getLatitude();
                                double longitude = mLastLocation.getLongitude();

                                textView4.setText(latitude + ", " + longitude);

                            } else {

                                textView4.setText("(Couldn't get the location. Make sure location is enabled on the device)");
                            }

                            for (PlaceLikelihood placeLikelihood : likelyPlaces) {

                                if(placeLikelihood.getPlace().getPlaceTypes().toString().contains("79")) {
                                   // shopsProfiles_data.add(
                                    //new ShopsProfile(placeLikelihood.getPlace().getName().toString(), deals,placeLikelihood.getPlace().getAddress().toString(), "(to-do)2 miles"));
                                }

//                                Log.i(LOG_TAG, String.format("Place '%s' has likelihood: %g",
//                                        placeLikelihood.getPlace().getName(),
//                                        placeLikelihood.getLikelihood()));
                            }
//                            ShopListAdaptor adapter = new ShopListAdaptor(TestCurrentLocationAPI.this,
//                                    R.layout.listview_shoplist, shopsProfiles_data);
//
//                            listView1 = (ListView)findViewById(R.id.lv_shoplist);
//                            listView1.setAdapter(adapter);
//                            Intent intent = new Intent(TestCurrentLocationAPI.this, ShopListActivity.class);
                            //intent.putExtra("deals", deals);
//                            intent.putExtra("shopList", shopsProfiles_data);
//                            startActivity(intent);

                        }

                    }
                });

            }

        }
        else {
            Log.d(LOG_TAG, "Permission not Granted");
        }

    }

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkPlayServices();
    }

    /**
     * Google api callback methods
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(LOG_TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    @Override
    public void onConnected(Bundle arg0) {

        // Once connected with google api, get the location
//        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }

    public class StableArrayAdapter extends ArrayAdapter<String> {

        final int INVALID_ID = -1;

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            if (position < 0 || position >= mIdMap.size()) {
                return INVALID_ID;
            }
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}