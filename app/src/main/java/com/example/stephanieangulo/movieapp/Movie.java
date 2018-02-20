package com.example.stephanieangulo.movieapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by stephanieangulo on 2/11/18.
 */

public class Movie {
    static final int CHARACTER_LIMIT = 3;
    String title, episodeNum, description, poster, url, characters;
    ArrayList<String> charactersList = new ArrayList<>();
    String dynamicText;

    public static ArrayList<Movie> getMoviesFromJSON(String filename, Context context) {
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            String x = loadJsonFromAsset(filename, context);
            JSONObject json = new JSONObject(x);
            JSONArray movies = json.getJSONArray("movies");
            for(int i = 0; i < movies.length(); i++) {
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.episodeNum = movies.getJSONObject(i).getString("episode_number");
                movie.poster = movies.getJSONObject(i).getString("poster");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.url = movies.getJSONObject(i).getString("url");
                movie.dynamicText = "Has Seen?";
                JSONArray characters_arr = movies.getJSONObject(i).getJSONArray("main_characters");
                for(int j = 0; j < characters_arr.length(); j++) {
                    movie.charactersList.add(characters_arr.getString(j));
                }
                movie.characters = getCharacterString(movie.charactersList);
                movieList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieList;
    }
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private static String getCharacterString(ArrayList<String> list) {
        String characters = "";
        int limit = CHARACTER_LIMIT;

        if(CHARACTER_LIMIT > list.size()) {
            limit = list.size();
        }

        for(int i = 0; i < limit; i++) {
            characters = characters + list.get(i) + ", ";
        }
        characters = characters.substring(0, characters.length() - 2);
        return characters;
    }
}
