package com.rektgg.salert;


import android.content.Context;
import android.support.multidex.MultiDex;

import com.parse.Parse;



/**
 * Created by Justin on 5/11/2016.
 */
public class Application extends android.app.Application{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private static ConfigHelper configHelper;

    public Application() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("1107IiIaKxsLaMxdgCda")
                .clientKey(null)
                .server("http://scouty.mybluemix.net/parse/")
                .build()
        );

        configHelper = new ConfigHelper();
        configHelper.fetchConfigIfNeeded();

/*        Log.d("here", "app");

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("APPLICATION_ID")
                .clientKey("apple")
                .server("http://10.1.92.89:1337/parse/")


                .build()
        );*/
/*
       ParseObject gameScore = new ParseObject("GameScore");
        gameScore.put("score", 1337);
        gameScore.put("playerName", "Sean Plott");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("Parse", "Save Succeeded");
                } else {
                    Log.i("Parse", "Save Failed");
                    e.getMessage();
                }
            }
        });*/
    }

    public static ConfigHelper getConfigHelper() {
        return configHelper;
    }

}
