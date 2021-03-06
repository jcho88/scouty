package net.rektapps.scouty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.parse.ParseUser;

import java.util.List;


public class SettingsActivity extends AppCompatActivity {

    private List<Float> availableOptions = Application.getConfigHelper().getSearchDistanceAvailableOptions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        float currentSearchDistance = Application.getSearchDistance();
//        if (!availableOptions.contains(currentSearchDistance)) {
//            availableOptions.add(currentSearchDistance);
//        }
//        Collections.sort(availableOptions);
//
//        // The search distance choices
//        RadioGroup searchDistanceRadioGroup = (RadioGroup) findViewById(R.id.searchdistance_radiogroup);
//
//        for (int index = 0; index < availableOptions.size(); index++) {
//            float searchDistance = availableOptions.get(index);
//
//            RadioButton button = new RadioButton(this);
//            button.setId(index);
//            button.setText(getString(R.string.settings_distance_format, (int)searchDistance));
//            searchDistanceRadioGroup.addView(button, index);
//
//            if (currentSearchDistance == searchDistance) {
//                searchDistanceRadioGroup.check(index);
//            }
//        }
//
//        // Set up the selection handler to save the selection to the application
//        searchDistanceRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Application.setSearchDistance(availableOptions.get(checkedId));
//            }
//        });

        // Set up the log out button click handler
        ImageButton logoutButton = (ImageButton) findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Call the Parse log out method
                ParseUser.logOut();
                // Start and intent for the dispatch activity
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
