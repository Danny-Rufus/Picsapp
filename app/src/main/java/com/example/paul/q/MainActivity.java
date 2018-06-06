package com.example.paul.q;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements NewAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageurl";
    public static final String EXTRA_CREATOR = "creatorname";
    public static final String EXTRA_LIKES = "likes";


    private RecyclerView recycler;
    private NewAdapter mAdapter;
    private ArrayList<ModelClass> modelClassArrayList;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler=findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        modelClassArrayList = new ArrayList<>();

        queue= Volley.newRequestQueue(this);
        parsing();
    }

    private void parsing() {

        String url="https://pixabay.com/api/?key=8928084-315b66739dba70b81cb9d0612&q=birds+flying&image_type=photo&cat=animals&min_height=720&min_width=720&order=popular";

        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray= response.getJSONArray("hits");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject hit=jsonArray.getJSONObject(i);

                                String creatorname=hit.getString("user");
                                String imageurl=hit.getString("webformatURL");
                                int likes=hit.getInt("likes");

                                modelClassArrayList.add(new ModelClass(imageurl,creatorname,likes));
                            }

                            mAdapter = new NewAdapter(MainActivity.this,modelClassArrayList);
                            recycler.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(MainActivity.this);

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
        queue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this,DetailActivity.class);
        ModelClass clickedItem = modelClassArrayList.get(position);

        detailIntent.putExtra(EXTRA_URL,clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR,clickedItem.getmCreator());
        detailIntent.putExtra(EXTRA_LIKES,clickedItem.getmLikes());

        startActivity(detailIntent);

    }
}
