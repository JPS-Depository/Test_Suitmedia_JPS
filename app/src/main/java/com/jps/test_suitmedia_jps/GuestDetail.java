package com.jps.test_suitmedia_jps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.jps.test_suitmedia_jps.screen4.EXTRA_Email;
import static com.jps.test_suitmedia_jps.screen4.EXTRA_First;
import static com.jps.test_suitmedia_jps.screen4.EXTRA_Last;
import static com.jps.test_suitmedia_jps.screen4.EXTRA_URL;

public class GuestDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guest_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String firstName = intent.getStringExtra(EXTRA_First);
        String lastName = intent.getStringExtra(EXTRA_Last);
        String email = intent.getStringExtra(EXTRA_Email);

        ImageView imageView = findViewById(R.id.guest_image_detail);
        TextView textViewFirst = findViewById(R.id.first_name_detail);
        TextView textViewLast = findViewById(R.id.last_name_detail);
        TextView textViewEmail = findViewById(R.id.email_detail);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewFirst.setText(firstName);
        textViewLast.setText(lastName);
        textViewEmail.setText("Email : "+email);
    }
}