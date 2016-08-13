package com.rektgg.salert;

//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.PendingResult;
//import com.google.android.gms.common.api.ResultCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.places.Place;
//import com.google.android.gms.location.places.PlaceLikelihood;
//import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
//import com.google.android.gms.location.places.Places;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class ShopListActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
//
//        GoogleApiClient.OnConnectionFailedListener {
//    private static final String LOG_TAG = "PlacesAPIActivity";
//    private static final int GOOGLE_API_CLIENT_ID = 0;
//    private GoogleApiClient mGoogleApiClient;
//    private static final int PERMISSION_REQUEST_CODE = 100;
//    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
//    private ListView listView;
//    private Location mLastLocation;
//    ArrayList<ShopsProfile> shopsProfiles_data = new ArrayList<ShopsProfile>();
//    private LocationRequest mLocationRequest;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_list);
//
//        // First we need to check availability of play services
//        if (checkPlayServices()) {
////            mGoogleApiClient = new GoogleApiClient
////                    .Builder(this)
////                    .addApi(Places.GEO_DATA_API)
////                    .addApi(Places.PLACE_DETECTION_API)
////                    .enableAutoManage(this, this)
////                    .build();
//
//            mGoogleApiClient = new GoogleApiClient.Builder(this)
//                    .enableAutoManage( this, 0, this )
//                    .addApi( Places.GEO_DATA_API )
//                    .addApi( Places.PLACE_DETECTION_API )
//                    .addConnectionCallbacks(this)
//                    .addOnConnectionFailedListener(this)
//                    .addApi(LocationServices.API).build();
//
//
//        }
//
//
//
//    }
//
//    /**
//     * Method to display the location on UI
//     * */
//    private void displayLocation() {
//        Log.d("asd","asd");
//        if (mGoogleApiClient.isConnected()) {
//            if (ContextCompat.checkSelfPermission(ShopListActivity.this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)
//                    != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(ShopListActivity.this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        PERMISSION_REQUEST_CODE);
//            } else {
//                mLastLocation = LocationServices.FusedLocationApi
//                        .getLastLocation(mGoogleApiClient);
//
////                PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace( mGoogleApiClient, null );
////                result.setResultCallback( new ResultCallback<PlaceLikelihoodBuffer>() {
////                    @Override
////                    public void onResult( PlaceLikelihoodBuffer likelyPlaces ) {
////
////                        PlaceLikelihood placeLikelihood = likelyPlaces.get( 0 );
////                        String content = "";
////
////                        if (mLastLocation != null) {
////                            double latitude = mLastLocation.getLatitude();
////                            double longitude = mLastLocation.getLongitude();
////
////                            textView4.setText(latitude + ", " + longitude);
////
////                        } else {
////
////                            textView4.setText("(Couldn't get the location. Make sure location is enabled on the device)");
////                        }
////
////                        if( placeLikelihood != null && placeLikelihood.getPlace() != null && !TextUtils.isEmpty( placeLikelihood.getPlace().getName() ) )
////                            content = "Most likely place: " + placeLikelihood.getPlace().getName() + "\n";
////                        if( placeLikelihood != null )
////                            content += "Percent change of being there: " + (int) ( placeLikelihood.getLikelihood() * 100 ) + "%";
////                        textView5.setText( content );
////
////                        likelyPlaces.release();
////                    }
////                });
//
//                PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
//                        .getCurrentPlace(mGoogleApiClient, null);
//                result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
//                    @Override
//                    public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
//                        if(likelyPlaces.getStatus().getStatusMessage() == "ERROR") {
//                            Toast.makeText(ShopListActivity.this, "GPS is not enabled", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//
//                            ArrayList<ShopDeals> deals  = new ArrayList<ShopDeals>();
//
//                            for (PlaceLikelihood placeLikelihood : likelyPlaces) {
//
////                                if(placeLikelihood.getPlace().getPlaceTypes().toString().contains("79")) {
//                                shopsProfiles_data.add(
//                                        new ShopsProfile(placeLikelihood.getPlace().getName().toString(), deals,placeLikelihood.getPlace().getAddress().toString(), "(to-do)2 miles"));
////                                }
//
////                                Log.i(LOG_TAG, String.format("Place '%s' has likelihood: %g",
////                                        placeLikelihood.getPlace().getName(),
////                                        placeLikelihood.getLikelihood()));
//                            }
////                            ShopListAdaptor adapter = new ShopListAdaptor(TestCurrentLocationAPI.this,
////                                    R.layout.listview_shoplist, shopsProfiles_data);
////
////                            listView1 = (ListView)findViewById(R.id.lv_shoplist);
////                            listView1.setAdapter(adapter);
//                            ShopListAdaptor adapter = new ShopListAdaptor(ShopListActivity.this,
//                                    R.layout.listview_shoplist, shopsProfiles_data);
//
//                            listView = (ListView)findViewById(R.id.lv_shoplist);
//                            listView.setAdapter(adapter);
//                            likelyPlaces.release();
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        else {
//            Log.d(LOG_TAG, "Permission not Granted");
//        }
//
//    }
//
//    /**
//     * Method to verify google play services on the device
//     * */
//    private boolean checkPlayServices() {
//        int resultCode = GooglePlayServicesUtil
//                .isGooglePlayServicesAvailable(this);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
//                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
//                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
//            } else {
//                Toast.makeText(getApplicationContext(),
//                        "This device is not supported.", Toast.LENGTH_LONG)
//                        .show();
//                finish();
//            }
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (mGoogleApiClient != null) {
//            mGoogleApiClient.connect();
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        checkPlayServices();
//    }
//
//    /**
//     * Google api callback methods
//     */
//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//        Log.i(LOG_TAG, "Connection failed: ConnectionResult.getErrorCode() = "
//                + result.getErrorCode());
//    }
//
//    @Override
//    public void onConnected(Bundle arg0) {
//
//        if (mGoogleApiClient.isConnected()) {
//            if (ContextCompat.checkSelfPermission(ShopListActivity.this,
//                    android.Manifest.permission.ACCESS_FINE_LOCATION)
//                    != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(ShopListActivity.this,
//                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                        PERMISSION_REQUEST_CODE);
//            } else {
//                displayLocation();
//            }
//
//        }
//
//        // Once connected with google api, get the location
////        displayLocation();
//    }
//
//    @Override
//    public void onConnectionSuspended(int arg0) {
//        mGoogleApiClient.connect();
//    }
//
//    public class StableArrayAdapter extends ArrayAdapter<String> {
//
//        final int INVALID_ID = -1;
//
//        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
//
//        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
//            super(context, textViewResourceId, objects);
//            for (int i = 0; i < objects.size(); ++i) {
//                mIdMap.put(objects.get(i), i);
//            }
//        }
//
//        @Override
//        public long getItemId(int position) {
//            if (position < 0 || position >= mIdMap.size()) {
//                return INVALID_ID;
//            }
//            String item = getItem(position);
//            return mIdMap.get(item);
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return true;
//        }
//    }
//
//}

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.SphericalUtil;

import android.*;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

public class ShopListActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String LOG_TAG = "ShopListActivity";
    private GoogleApiClient mGoogleApiClient;
    int PLACE_PICKER_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private Location mLastLocation;
    private String toastMsg;
    private boolean locationCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        if (checkPlayServices()) {
            mGoogleApiClient = new GoogleApiClient
                    .Builder(this)
                    .enableAutoManage( this, 0, this )
                    .addApi( Places.GEO_DATA_API )
                    .addApi( Places.PLACE_DETECTION_API )
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    //After PlacePicker Intent begin called (startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST))
    //This will method will start.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

                //TODO
                //search database with storeID and display the result in ShopProfileActivity

                Place place = PlacePicker.getPlace(this, data);
//                toastMsg = String.format("Place: %s", place.getName());
//                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

                Log.d(LOG_TAG, "GOES IN ACTIVITY RESULT");

                final ProgressDialog dialog = new ProgressDialog(ShopListActivity.this);
                dialog.setMessage(String.format("Getting Deals at %s", place.getName()));
                dialog.show();
                getDeals();
            }
        }
    }

    private void getLocation() {

        Log.d(LOG_TAG, "GOES IN GET LOCATION");

        if (mGoogleApiClient.isConnected()) {
            if (ContextCompat.checkSelfPermission(ShopListActivity.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ShopListActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQUEST_CODE);
            } else {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    mLastLocation = LocationServices.FusedLocationApi
                            .getLastLocation(mGoogleApiClient);

                    if (mLastLocation != null) {

                        Log.d(LOG_TAG, "location check is false");

                        double latitude = mLastLocation.getLatitude();
                        double longitude = mLastLocation.getLongitude();

                        LatLng centerLocation = new LatLng(latitude, longitude);

                        LatLngBounds lastLocation = getLatLngBounds(centerLocation);

                        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                        builder.setLatLngBounds(lastLocation);
                        try {
                            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
                        } catch (GooglePlayServicesRepairableException e) {
                            e.printStackTrace();
                        } catch (GooglePlayServicesNotAvailableException e) {
                            e.printStackTrace();
                        }

                        locationCheck = true;

                    } else if (locationCheck){
                        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                        try {
                            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
                        } catch (GooglePlayServicesRepairableException e) {
                            e.printStackTrace();
                        } catch (GooglePlayServicesNotAvailableException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.d(LOG_TAG, "GPS not enabled");
                        Toast.makeText(getApplicationContext(),
                                "Please enable GPS on the device", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else {
                    Log.d(LOG_TAG, "Location not enabled");
                    Toast.makeText(getApplicationContext(),
                            "Could not get your location. Please enable location on the device", Toast.LENGTH_LONG).show();
                    finish();

                }
            }
        }
    }

    public static LatLngBounds getLatLngBounds(LatLng center) {
        double radius = 100;
        LatLng southwest = SphericalUtil.computeOffset(center, radius * Math.sqrt(2.0), 225);
        LatLng northeast = SphericalUtil.computeOffset(center, radius * Math.sqrt(2.0), 45);
        return new LatLngBounds(southwest, northeast);
    }

    public void getDeals(){
        Intent intent = new Intent(this, ShopProfileActivity.class);
        this.startActivity(intent);
    }

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);;
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GoogleApiAvailability.getInstance().isUserResolvableError(resultCode)) {
                GoogleApiAvailability.getInstance().getErrorDialog(this, resultCode,
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

    //For GooglePlay Services, When the app start connect user
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

//    @Override
//    protected void onPause(){
//        super.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }

    //For GooglePlay Services, After being connected.
    @Override
    public void onConnected(Bundle arg0) {

        Log.d(LOG_TAG, "IN ON CONNECTED");
        if (mGoogleApiClient.isConnected()) {
            if (ContextCompat.checkSelfPermission(ShopListActivity.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ShopListActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQUEST_CODE);
            } else {
                getLocation();
            }

        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
//        mGoogleApiClient.connect();
        Log.d("connetion", "Connection interrupted");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("connetion", "Connection failed");
    }

    //If Determine the result of user's permission in onConnection(). If User
    // Allow us to use location, we display the location. If not. We Toast them
    // to allow us to use thier location
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getLocation();

                } else {

                    Toast.makeText(getApplicationContext(),
                            "Please Allow us to use your Location", Toast.LENGTH_LONG)
                            .show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    // TODO: Please implement GoogleApiClient.OnConnectionFailedListener to
    // handle connection failures.
}