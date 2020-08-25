package com.dardan.musicplayer.NavDrawerExtras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dardan.musicplayer.R;

public class Equilizer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equilizer);
        getSupportActionBar().setTitle("Equalizer");
    }
}