package com.dardan.musicplayer.NavDrawerExtras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dardan.musicplayer.R;

public class BassBooster extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bass_booster);
        getSupportActionBar().setTitle("Bass Booster");
    }
}