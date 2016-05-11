package com.rektgg.salert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


        Button registerButton = (Button) findViewById(R.id.b_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        String username = usernameRegister.getText().toString().trim();
        String email = emailRegister.getText().toString().trim();
        String password = passwordRegister.getText().toString().trim();
        String passwordRepeat = passwordRepeatRegister.getText().toString().trim();

        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));

        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_username));
        }


    }
}
