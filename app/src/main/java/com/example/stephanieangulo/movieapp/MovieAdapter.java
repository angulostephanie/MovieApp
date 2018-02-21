package com.example.stephanieangulo.movieapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by stephanieangulo on 2/11/18.
 */

public class MovieAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList) {
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int i) {
        return mMovieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            // inflate
            view = mInflater.inflate(R.layout.movie_list_item, viewGroup, false);

            holder = new ViewHolder();

            holder.titleTextView = view.findViewById(R.id.movie_item_title);
            holder.descriptionTextView = view.findViewById(R.id.movie_item_description);
            holder.characterTextView = view.findViewById(R.id.movie_item_characters);
            holder.hasSeenTextView = view.findViewById(R.id.movie_item_question);
            holder.posterImageView = view.findViewById(R.id.movie_item_image);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

            TextView titleTextView = holder.titleTextView;
            TextView descriptionTextView = holder.descriptionTextView;
            ImageView posterImageView = holder.posterImageView;
            TextView characterTextView = holder.characterTextView;
            TextView hasSeenTextView = holder.hasSeenTextView;

            Movie movie = (Movie) getItem(i);

            titleTextView.setText(movie.title);
            titleTextView.setTextSize(20);
            descriptionTextView.setText(movie.description);
            descriptionTextView.setTextSize(9);
            characterTextView.setText(movie.characters);
            characterTextView.setTextSize(13);
            hasSeenTextView.setTextSize(11);
            hasSeenTextView.setText(movie.dynamicText);
            hasSeenTextView.setTextColor(Color.GRAY);
            if(movie.dynamicText.equals("Already Seen")){
                hasSeenTextView.setTextColor(Color.GREEN);
            } else if(movie.dynamicText.equals("Want to See")) {
                hasSeenTextView.setTextColor(Color.BLUE);
            } else if(movie.dynamicText.equals("Don't like")) {
                hasSeenTextView.setTextColor(Color.RED);
            }

            Picasso.with(mContext).load(movie.poster).into(posterImageView);
        }

        return view;
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView characterTextView;
        public TextView hasSeenTextView;
        public ImageView posterImageView;

    }
}
