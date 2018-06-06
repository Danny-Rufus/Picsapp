package com.example.paul.q;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.paul.q.MainActivity.EXTRA_CREATOR;
import static com.example.paul.q.MainActivity.EXTRA_LIKES;
import static com.example.paul.q.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageurl = intent.getStringExtra(EXTRA_URL);
        String creatorname = intent.getStringExtra(EXTRA_CREATOR);
        int likes = intent.getIntExtra(EXTRA_LIKES,0);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textView = findViewById(R.id.Text_view_detail);
        TextView textView1 = findViewById(R.id.Text_view_likes_detail);

        Picasso.with(this).load(imageurl).fit().centerInside().into(imageView);
        textView.setText(creatorname);
        textView1.setText("Likes: " +likes);
    }
}
