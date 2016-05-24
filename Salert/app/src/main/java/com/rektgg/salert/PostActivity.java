package com.rektgg.salert;

import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PostActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText postEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        submitButton = (Button) findViewById(R.id.bt_submit);
        submitButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                post();
            }
        });

//        Intent intent = new Intent(PostActivity.this, ShopProfileActivity.class);
//        intent.putExtra(Application.INTENT_EXTRA_LOCATION, myLoc);
//        startActivity(intent);
    }

    private void post () {

        String text = postEditText.getText().toString().trim();

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(PostActivity.this);
        dialog.setMessage(getString(R.string.progress_post));
        dialog.show();

        DealPost post = new DealPost();

        post.setText(text);
        post.setUser(ParseUser.getCurrentUser());
        ParseACL acl = new ParseACL();

        // Give public read access
        acl.setPublicReadAccess(true);
        post.setACL(acl);

        // Save the post
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                finish();
            }
        });

    }
}
