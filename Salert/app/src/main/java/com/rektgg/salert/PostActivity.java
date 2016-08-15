package com.rektgg.salert;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PostActivity extends AppCompatActivity {

    private ImageButton submitButton;
    private EditText postEditText;
    private TextView characterCounterTextView;
    Intent shop_profile_data;
    String deal_info;
    String userName;


    private int maxCharacterCount = Application.getConfigHelper().getPostMaxCharacterCount();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        submitButton = (ImageButton) findViewById(R.id.bt_submit_post);
        postEditText = (EditText) findViewById(R.id.et_deal_description);
        characterCounterTextView = (TextView) findViewById(R.id.tv_character_counter);

        postEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                updatePostButtonState();
                updateCharacterCountTextViewText();
            }
        });

        submitButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                post();
            }
        });

        updatePostButtonState();
        updateCharacterCountTextViewText();

    }

    private void post () {

        deal_info = postEditText.getText().toString().trim();

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(PostActivity.this);
        dialog.setMessage(getString(R.string.progress_post));
        dialog.show();

        DealPost post = new DealPost();

        post.setText(deal_info);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null) {
            post.put("userID", ParseUser.getCurrentUser().getObjectId());
            post.setUser(ParseUser.getCurrentUser());
            userName = ParseUser.getCurrentUser().getUsername();
        }else{
            post.put("userID", "-1");
            userName = "Visitor";
        }
        shop_profile_data = getIntent();
        post.setStoreId(shop_profile_data.getStringExtra("storeID"));
        ParseACL acl = new ParseACL();

        // Give public read access
        acl.setPublicReadAccess(true);
        post.setACL(acl);

        //give user repeated access to the user's post as long as the same user is logged in
        ParseACL.setDefaultACL(acl, true);

        // Save the post
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.d("in done", String.valueOf(e));
                dialog.dismiss();
                Intent resultIntent = new Intent(PostActivity.this, ShopProfileActivity.class);
                resultIntent.putExtra("deal",deal_info);
                resultIntent.putExtra("userName",userName);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }

    private String getPostEditTextText () {
        return postEditText.getText().toString().trim();
    }

    private void updatePostButtonState () {
        int length = getPostEditTextText().length();
        boolean enabled = length > 0 && length < maxCharacterCount;
        submitButton.setEnabled(enabled);
    }

    private void updateCharacterCountTextViewText () {
        String characterCountString = String.format("%d/%d", postEditText.length(), maxCharacterCount);
        characterCounterTextView.setText(characterCountString);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }




}
