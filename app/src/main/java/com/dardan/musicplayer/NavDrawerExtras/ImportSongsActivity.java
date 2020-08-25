package com.dardan.musicplayer.NavDrawerExtras;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dardan.musicplayer.Helpers.BottomSheetDialogHelper;
import com.dardan.musicplayer.R;

public class ImportSongsActivity extends AppCompatActivity implements BottomSheetDialogHelper.BottomSheetListener{

    private Menu menu;
    private DrawerLayout mDrawerLayout;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_songs);
        getSupportActionBar().setTitle("Import Songs");

        Button buttonOpenBottomSheet = findViewById(R.id.connect_to_a_source);
        buttonOpenBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialogHelper bottomSheet = new BottomSheetDialogHelper();
                bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.import_menu, menu);
        return true;
    }

    //onClick action

    public void openComputer (View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.16.9.92/")));
    }
    public void openApplemusic(View view) {

    }
    public void openDropbox(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.apple.com/apple-music/")));
    }
    public void openGoogledrive(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dropbox.com/?_hp=c")));
    }
    public void openOnedrive(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.microsoft.com/en-us/microsoft-365/onedrive/online-cloud-storage")));
    }
    public void openFiles(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://files.google.com/")));
    }
    public void onCancel() {}

    public void openConverter(MenuItem item) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://ytmp3.cc/en13/")));
    }
    public void openYoutube(MenuItem item) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/")));
    }
    public void openItunes(MenuItem item) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.apple.com/itunes/")));
    }
    public void openAudiomack(MenuItem item) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://audiomack.com/")));
    }
    @Override
    public void onButtonClicked(String text) {
        mTextView.setText(text);
    }
}