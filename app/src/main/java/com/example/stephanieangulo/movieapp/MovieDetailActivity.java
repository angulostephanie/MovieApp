package com.example.stephanieangulo.movieapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by stephanieangulo on 2/13/18.
 */

public class MovieDetailActivity extends AppCompatActivity {
    Context mContext;
    TextView titleTextView;
    TextView descriptionTextView;
    ImageView posterImageView;
    Button submitBtn;

    boolean alreadySeen;
    boolean wantToSee;
    boolean doNotLike;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mContext = this;

        String title = this.getIntent().getExtras().getString("title");
        String url = this.getIntent().getExtras().getString("poster");
        String description = this.getIntent().getExtras().getString("description");
        i = this.getIntent().getExtras().getInt("position");
        setTitle(title);

        titleTextView = findViewById(R.id.detail_movie_item_title);
        posterImageView = findViewById(R.id.detail_movie_item_image);
        descriptionTextView = findViewById(R.id.detail_movie_item_description);
        submitBtn = findViewById(R.id.detail_submit_btn);

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        Picasso.with(mContext).load(url).into(posterImageView);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent radioBtnIntent = new Intent();

                radioBtnIntent.putExtra("alreadySeen", alreadySeen);
                radioBtnIntent.putExtra("wantToSee", wantToSee);
                radioBtnIntent.putExtra("doNotLike", doNotLike);
                radioBtnIntent.putExtra("position", i);
                setResult(RESULT_OK, radioBtnIntent);
                finish();
            }
        });
    }

    public void alreadySeenChecked(View view) {
        alreadySeen = ((RadioButton) view).isChecked();
    }

    public void wantToSeeChecked(View view) {
        wantToSee = ((RadioButton) view).isChecked();
    }

    public void doNotLikeChecked(View view) {
        doNotLike = ((RadioButton) view).isChecked();
    }


}
