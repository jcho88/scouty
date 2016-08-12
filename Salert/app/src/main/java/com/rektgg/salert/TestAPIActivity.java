package com.rektgg.salert;

import com.google.android.gms.common.ConnectionResult;
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
import android.widget.Toast;

public class TestAPIActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String LOG_TAG = "TestAPIActivity";
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
        setContentView(R.layout.activity_test_api);

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

                Place place = PlacePicker.getPlace(data, this);
                toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void getLocation() {

        if (mGoogleApiClient.isConnected()) {
            if (ContextCompat.checkSelfPermission(TestAPIActivity.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(TestAPIActivity.this,
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
                    }
                } else {
                    Log.d(LOG_TAG, "Location not enabled");

                    Toast.makeText(getApplicationContext(),
                            "Could not get your location. Please enable location on the device", Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    public static LatLngBounds getLatLngBounds(LatLng center) {
        double radius = 150;
        LatLng southwest = SphericalUtil.computeOffset(center, radius * Math.sqrt(4.0), 225);
        LatLng northeast = SphericalUtil.computeOffset(center, radius * Math.sqrt(4.0), 45);
        return new LatLngBounds(southwest, northeast);
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

    //For GooglePlay Services, After being connected.
    @Override
    public void onConnected(Bundle arg0) {

        Log.d(LOG_TAG, "IN ON CONNECTED");
        if (mGoogleApiClient.isConnected()) {
            if (ContextCompat.checkSelfPermission(TestAPIActivity.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(TestAPIActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQUEST_CODE);
            } else {
                getLocation();
            }

        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("connetion", "Failed");
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