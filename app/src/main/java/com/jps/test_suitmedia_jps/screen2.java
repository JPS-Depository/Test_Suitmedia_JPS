package com.jps.test_suitmedia_jps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class screen2 extends AppCompatActivity {

    private TextView username;

    private Button chooseEvent, chooseGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);

        username = (TextView) findViewById(R.id.username_2);
        String Username = getIntent().getStringExtra("USERNAME");

        username.setText(Username);

        chooseEvent = (Button) findViewById(R.id.choose_Event);
        chooseGuest = (Button) findViewById(R.id.choose_Guest);

        chooseEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreen3(Username);
            }
        });
        chooseGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScreen4(Username);
            }
        });

    }

    public void openScreen3(String aUsername)
    {
        Intent intent = new Intent(this, screen3.class);
        intent.putExtra("USERNAME", aUsername);
        startActivityForResult(intent,100);
    }
    public void openScreen4(String aUsername)
    {
        Intent intent = new Intent(this, screen4.class);
        intent.putExtra("USERNAME", aUsername);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode== Activity.RESULT_OK)
        {
            if(requestCode==100)
            {
                String Username = data.getStringExtra("USERNAME");
                username.setText(Username);
            }
        }
    }

}