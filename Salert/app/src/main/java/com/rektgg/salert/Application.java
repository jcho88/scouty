package com.rektgg.salert;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Justin on 5/11/2016.
 */
public class Application extends android.app.Application{

    public Application() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("APPLICATION_ID")
                .clientKey(null)
                .server("http://10.1.92.42:1337/parse/")

                .build()
        );

    }
}
