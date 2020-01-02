package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button button;
    private CheckBox checkBox;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USERNAME = "id";
    public static final String PASSWORD = "pass";

    private String id;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.editText6);
        password = (EditText) findViewById(R.id.editText7);
        button = (Button) findViewById(R.id.button2);
        checkBox = (CheckBox) findViewById(R.id.checkBox2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });


        loadData();
        updateViews();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USERNAME, username.getText().toString());
        editor.putString(PASSWORD, password.getText().toString());

        editor.apply();


    }

    public void openActivity() {
        Intent intent;
        intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        id = sharedPreferences.getString(USERNAME, "");
        pass = sharedPreferences.getString(PASSWORD, "");

    }

    public void updateViews() {
        username.setText(id);
        password.setText(pass);


    }
}


