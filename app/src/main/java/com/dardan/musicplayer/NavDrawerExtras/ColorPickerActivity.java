package com.dardan.musicplayer.NavDrawerExtras;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.RelativeLayout;

import com.dardan.musicplayer.R;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ColorPickerActivity extends AppCompatActivity  {

    RelativeLayout mLayout;
    int mDefaultColor;
    SwitchCompat mSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        getSupportActionBar().setTitle("Set Theme");

        mLayout = (RelativeLayout) findViewById(R.id.layout);
        mDefaultColor = ContextCompat.getColor(ColorPickerActivity.this, R.color.colorPrimary);
        mSwitch = (SwitchCompat) findViewById(R.id.colorpicker);
        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 openColorPicker();
            }
        });
    }

    public void openColorPicker() {
        final AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;
                mLayout.setBackgroundColor(mDefaultColor);
            }
        });
        colorPicker.show();
    }
}


