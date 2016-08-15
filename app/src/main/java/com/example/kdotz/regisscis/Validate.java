package com.example.kdotz.regisscis;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Validate extends AppCompatActivity {

    private EditText firstName = null;
    private EditText lastName = null;
    private TextInputLayout lastNameLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( getSupportActionBar() != null ) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        firstName = (EditText) findViewById(R.id.text_firstName);
        lastName = (EditText) findViewById(R.id.text_lastName);
        lastNameLayout = (TextInputLayout) findViewById(R.id.textLayout_lastName);


        final Button buttonCheckForErrors = (Button) findViewById(R.id.button_errorCheckForErrors);
        buttonCheckForErrors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (firstName.getText().toString().equals("")) {
                    firstName.setError("First Name Required");
                } else {
                    firstName.setError(null);
                }

                if (lastName.getText().toString().equals("")) {
                    lastNameLayout.setError("Last Name Required");
                } else {
                    lastNameLayout.setError(null);
                }
            }
        });
    }

}
