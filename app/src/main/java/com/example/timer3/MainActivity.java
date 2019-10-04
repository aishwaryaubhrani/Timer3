package com.example.timer3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar s1;
    TextView t1;
    Button b1;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        t1.setText("00:00");
        b1.setText("Start");
        s1.setEnabled(true);
        s1.setProgress(1);
        countDownTimer.cancel();
        counterIsActive = false;
    }
    public  void updateTimer(int leftSeconds){
        int minutes = (int) leftSeconds /60;
        int seconds = leftSeconds - minutes *60;
        String secondString = Integer.toString(seconds);
        if(seconds <= 9){
            secondString = "0"+secondString;
        }
        t1.setText(Integer.toString(minutes)+":"+secondString);
    }
    public void controlTimer(View view){
        if(counterIsActive == false) {
            counterIsActive = true;
            s1.setEnabled(false);
            b1.setText("STOP");
            countDownTimer = new CountDownTimer(s1.getProgress() * 1000 + 100, 1000) {
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                public void onFinish() {
                    t1.setText("00:00");
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    player.start();
                    b1.setText("Start");
                    s1.setEnabled(true);
                    counterIsActive = false;
                }
            }.start();
        }
        else{
            resetTimer();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1 = findViewById(R.id.seekBar);
        t1 = findViewById(R.id.textView);
        b1 = findViewById(R.id.button);

        s1.setMax(1800);
        s1.setProgress(1);
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
