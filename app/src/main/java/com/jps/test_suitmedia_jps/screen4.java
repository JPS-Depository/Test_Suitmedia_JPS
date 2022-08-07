package com.jps.test_suitmedia_jps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class screen4 extends AppCompatActivity implements GuestAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_First = "firstName";
    public static final String EXTRA_Last = "lastName";
    public static final String EXTRA_Email = "email";

    private RecyclerView mRecyclerView;
    private GuestAdapter mGuestAdapter;
    private ArrayList<GuestItem> mGuestList;
    private RequestQueue mRequestQueue;

    private TextView titlePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        titlePage = findViewById(R.id.page_title);
        titlePage.setText("Guest");

        mRecyclerView = findViewById(R.id.user_card);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        mGuestList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON()
    {
        String url = "https://reqres.in/api/users?page=1&per_page=10";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i< jsonArray.length();i++)
                    {
                        JSONObject data = jsonArray.getJSONObject(i);
                        int id = data.getInt("id");
                        String email = data.getString("email");
                        String firstName = data.getString("first_name");
                        String lastName = data.getString("last_name");
                        String imgUrl = data.getString("avatar");

                        mGuestList.add(new GuestItem(id, email, firstName, lastName, imgUrl));
                    }
                    mGuestAdapter = new GuestAdapter(screen4.this,mGuestList);
                    mRecyclerView.setAdapter(mGuestAdapter);
                    mGuestAdapter.setOnItemClickListener(screen4.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, GuestDetail.class);
        GuestItem clickedItem = mGuestList.get(position);

        detailIntent.putExtra(EXTRA_URL,clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_First,clickedItem.getFirst());
        detailIntent.putExtra(EXTRA_Last,clickedItem.getLast());
        detailIntent.putExtra(EXTRA_Email,clickedItem.getEmail());

        startActivity(detailIntent);
    }
    @Override
    public void onBackPressed() {
        String Username = getIntent().getStringExtra("USERNAME");
        Intent resultIntent = new Intent();
        resultIntent.putExtra("USERNAME", Username);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}