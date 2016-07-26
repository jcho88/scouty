package com.rektgg.salert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestAPIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);
    }
}

//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
//import com.google.android.gms.location.places.Places;
//
//import android.support.v4.app.FragmentActivity;
//
//public class TestAPIActivity extends AppCompatActivity
//        implements OnConnectionFailedListener {
//    private GoogleApiClient mGoogleApiClient;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mGoogleApiClient = new GoogleApiClient
//                .Builder(this)
//                .addApi(Places.GEO_DATA_API)
//                .addApi(Places.PLACE_DETECTION_API)
//                .enableAutoManage(this, this)
//                .build();
//    }
//
//    // TODO: Please implement GoogleApiClient.OnConnectionFailedListener to
//    // handle connection failures.
//}