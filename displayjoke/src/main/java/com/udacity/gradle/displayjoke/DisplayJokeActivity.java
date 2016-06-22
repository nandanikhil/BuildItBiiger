package com.udacity.gradle.displayjoke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by nikhil on 20/06/16.
 */
public class DisplayJokeActivity extends Activity {

    public static String JOKE_STRING = "JOKE_STRING";
    private String joke;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_joke);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(JOKE_STRING)) {
            joke = intent.getStringExtra(DisplayJokeActivity.JOKE_STRING);
        }
        TextView jokeTextView = (TextView) findViewById(R.id.joke_textview);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }
    }
}
