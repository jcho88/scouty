package net.rektapps.scouty;


import android.content.Context;
import android.support.multidex.MultiDex;
import android.content.SharedPreferences;

import com.parse.Parse;
import com.parse.ParseObject;


/**
 * Created by Justin on 5/11/2016.
 */
public class Application extends android.app.Application{

    //this is for Apps with Over 64K Methods fixed
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    // Debugging tag for the application
    public static final String APPTAG = "Scouty";

    //Google API key
    public static final String API = "AIzaSyDzH3TBEZgiRNK8d_2sDdCBbHHFA69uL38";


    // Debugging switch
    public static final boolean APPDEBUG = false;

    // Used to pass location from MainActivity to PostActivity
    public static final String INTENT_EXTRA_LOCATION = "location";

    // Key for saving the search distance preference
    private static final String KEY_SEARCH_DISTANCE = "searchDistance";

    private static final float DEFAULT_SEARCH_DISTANCE = 250.0f;

    private static SharedPreferences preferences;

    // detect change in text input helper
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
        ParseObject.registerSubclass(DealPost.class);

//        Log.i("Parse", "Save asdasdas");
//        ParseObject gameScore = new ParseObject("GameScore");
//        gameScore.put("score", 1337);
//        gameScore.put("playerName", "Sean Plott");
//        gameScore.put("cheatMode", false);
//        gameScore.saveInBackground(new SaveCallback() {
//            public void done(ParseException e) {
//                if (e == null) {
//                    Log.i("Parse", "Save Succeeded");
//                } else {
//                    Log.i("Parse", "Save Failed");
//                    e.getMessage();
//                }
//            }
//        });

        configHelper = new ConfigHelper();
        configHelper.fetchConfigIfNeeded();

/*        Log.d("here", "app");

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("APPLICATION_ID")
                .clientKey("apple")
                .server("http://10.1.92.89:1337/parse/")


                .build()
        );*/

    }

//    public static float getSearchDistance() {
//        return preferences.getFloat(KEY_SEARCH_DISTANCE, DEFAULT_SEARCH_DISTANCE);
//    }
//
//    public static void setSearchDistance(float value) {
//        preferences.edit().putFloat(KEY_SEARCH_DISTANCE, value).commit();
//    }

    public static ConfigHelper getConfigHelper() {
        return configHelper;
    }

}