package com.rektgg.salert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewUserActivity extends AppCompatActivity {

    private EditText usernameRegister;
    private EditText emailRegister;
    private EditText passwordRegister;
    private EditText passwordRepeatRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        usernameRegister = (EditText) findViewById(R.id.et_username);
        emailRegister = (EditText) findViewById(R.id.et_email);
        passwordRegister = (EditText) findViewById(R.id.et_password);
        passwordRepeatRegister = (EditText) findViewById(R.id.et_repeat_password);

        //handle when user presses soft key "Done"
        passwordRepeatRegister.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.et_register_action_id ||
                        actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    registerUser();
                    return true;
                }
                return false;
            }
        });

        //handle register button
        Button registerButton = (Button) findViewById(R.id.b_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    //handle invalid inputs & registration
    private void registerUser() {
        String username = usernameRegister.getText().toString().trim();
        String email = emailRegister.getText().toString().trim();
        String password = passwordRegister.getText().toString().trim();
        String passwordRepeat = passwordRepeatRegister.getText().toString().trim();

        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));

        //handle blank username
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_username));
        }

        //handle username already exists

        //handle blank email
        if (email.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_email));
        }

        //handle super basic noob way valid e-mail (contains @)
        if (!email.contains("@")) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_invalid_email_syntax));
        }

        //handle e-mail already exists

        //handle blank password
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_password));
        }

        //handle password mismatch
        if (!password.equals(passwordRepeat)) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_mismatched_passwords));
        }
        validationErrorMessage.append(getString(R.string.error_end));
    }
}
