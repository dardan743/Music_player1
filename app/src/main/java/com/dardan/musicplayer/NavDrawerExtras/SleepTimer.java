package com.dardan.musicplayer.NavDrawerExtras;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dardan.musicplayer.R;

import java.util.Locale;

public class SleepTimer extends AppCompatActivity {

    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    private Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private long mStartTimeInMillis;

    private long mTimeLeftInMills;
    private long mEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_timer);
        getSupportActionBar().setTitle("Sleep Timer");

        mEditTextInput = findViewById(R.id.edit_text_input);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonSet = findViewById(R.id.button_set);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditTextInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(SleepTimer.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0 ) {
                    Toast.makeText(SleepTimer.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(millisInput);
                mEditTextInput.setText("");
            }
        });

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning){
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void setTime(Long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMills;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMills, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMills = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                UpdateWatchInterface();
            }
        }.start();
        mTimerRunning = true;
        UpdateWatchInterface();
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        UpdateWatchInterface();
    }
    private void resetTimer() {
        mTimeLeftInMills = mStartTimeInMillis;
        updateCountDownText();
        UpdateWatchInterface();
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMills / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMills / 1000) % 3600) / 60 ; //turn miliseconds to seconds and minutes and hours
        int seconds = (int) (mTimeLeftInMills / 1000) % 60 ; //turn seconds into modulus
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d",hours, minutes, seconds); //convert to time string
        }else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds); //convert to time string
        }

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void UpdateWatchInterface() {
        if (mTimerRunning) {
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        } else {
            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");
            if (mTimeLeftInMills < 1000){
                mButtonStartPause.setVisibility(View.INVISIBLE);
            }else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }
            if (mTimeLeftInMills < mStartTimeInMillis) {
                mButtonReset.setVisibility(View.VISIBLE);
            } else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }

    //Orientation for sleeptimer(landscape & portrait)
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMills);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);

        editor.apply();

        if (mCountDownTimer !=null ) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        mTimeLeftInMills = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false);

        UpdateWatchInterface();
        updateCountDownText();

        if (mTimerRunning){
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMills = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMills < 0) {
                mTimeLeftInMills = 0;
                mTimerRunning = false;
                updateCountDownText();
                UpdateWatchInterface();
            } else {
                startTimer();
            }

        }
    }
}