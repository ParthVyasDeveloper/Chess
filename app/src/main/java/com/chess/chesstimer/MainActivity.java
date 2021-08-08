package com.chess.chesstimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView txtWhiteTime,txtBlackTime;
    ImageView imgStart,imgRestart;
    String player ="white";
    private CountDownTimer countDownTimer,countDownTimer1;
    private long pauseSecond;
    long userTotalTime=7000000;
    long whiteUserTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        txtWhiteTime = findViewById(R.id.tv_white_timer);
        txtBlackTime = findViewById(R.id.tv_black_timer);
        imgStart = findViewById(R.id.img_start);
        imgRestart = findViewById(R.id.img_restart);


        txtBlackTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imgStart.getTag().equals("pause")){

                    txtBlackTime.setEnabled(false);
                    pauseTimer();
                    txtWhiteTime.setEnabled(true);
                    startWhiteTimer();
                }
            }
        });

        txtWhiteTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imgStart.getTag().equals("pause")){
                    txtWhiteTime.setEnabled(false);
                    pauseWhiteTimer();
                    txtBlackTime.setEnabled(true);
                    startTimer();
                }
            }
        });

        userTotalTime = Long.parseLong(getIntent().getStringExtra("setTime"));
        whiteUserTime = Long.parseLong(getIntent().getStringExtra("setTime"));


        txtBlackTime.setText(String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(userTotalTime),
                TimeUnit.MILLISECONDS.toMinutes(userTotalTime) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(userTotalTime)), TimeUnit.MILLISECONDS.toSeconds(userTotalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(userTotalTime))));


        txtWhiteTime.setText(String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(whiteUserTime),
                TimeUnit.MILLISECONDS.toMinutes(whiteUserTime) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(whiteUserTime)), TimeUnit.MILLISECONDS.toSeconds(whiteUserTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(whiteUserTime))));



        imgStart.setImageResource(R.drawable.ic_pause);
        imgStart.setTag("pause");

        txtBlackTime.setEnabled(false);
        txtWhiteTime.setEnabled(true);

        startWhiteTimer();

        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(imgStart.getTag().toString().trim().equalsIgnoreCase("play")){
                    imgStart.setImageResource(R.drawable.ic_pause);
                    imgStart.setTag("pause");

                    if(txtWhiteTime.isEnabled()){
                        startWhiteTimer();
                    }else{
                        startTimer();
                    }


                }else{
                    imgStart.setImageResource(R.drawable.ic_play);
                    imgStart.setTag("play");

                    if(txtWhiteTime.isEnabled()){
                       pauseWhiteTimer();
                    }else{
                        pauseTimer();
                    }


                }
            }
        });

        imgRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgStart.setTag("play");
                imgStart.setImageResource(R.drawable.ic_play);
                userTotalTime = Long.parseLong(getIntent().getStringExtra("setTime"));
                whiteUserTime = Long.parseLong(getIntent().getStringExtra("setTime"));

                txtBlackTime.setText(String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(userTotalTime),
                        TimeUnit.MILLISECONDS.toMinutes(userTotalTime) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(userTotalTime)), TimeUnit.MILLISECONDS.toSeconds(userTotalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(userTotalTime))));


                txtWhiteTime.setText(String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(whiteUserTime),
                        TimeUnit.MILLISECONDS.toMinutes(whiteUserTime) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(whiteUserTime)), TimeUnit.MILLISECONDS.toSeconds(whiteUserTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(whiteUserTime))));


                try{
                    countDownTimer.cancel();

                }catch (Exception e){

                }

                try{
                    countDownTimer1.cancel();
                }catch (Exception e){

                }

                txtWhiteTime.setEnabled(true);
                imgStart.performClick();

            }
        });
    }

    private void pauseTimer() {

        countDownTimer.cancel();

    }

    private void pauseWhiteTimer() {

        countDownTimer1.cancel();

    }

    private void startTimer() {



            countDownTimer = new CountDownTimer(userTotalTime, 1000) {

                public void onTick(long millisUntilFinished) {

                    Log.e("userBothTime", millisUntilFinished + " " + userTotalTime);


                    txtBlackTime.setText(String.format("%02d:%02d:%02d",
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    //here you can have your logic to set text to edittext

                    userTotalTime = millisUntilFinished;

                }

                public void onFinish() {
                    //txtBlackTime.setText("done!");
                    if (txtBlackTime.getText().toString().trim().equalsIgnoreCase("00:00:00")) {
                        Toast.makeText(MainActivity.this, "Black Time Over", Toast.LENGTH_SHORT).show();
                    }
                }

            }.start();

    }

    private void startWhiteTimer() {



            countDownTimer1 = new CountDownTimer(whiteUserTime, 1000) {

                public void onTick(long millisUntilFinished) {


                    txtWhiteTime.setText(String.format("%02d:%02d:%02d",
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    //here you can have your logic to set text to edittext

                    whiteUserTime = millisUntilFinished;


                }

                public void onFinish() {
                    //txtBlackTime.setText("done!");
                    if(txtWhiteTime.getText().toString().trim().equalsIgnoreCase("00:00:00")){
                        Toast.makeText(MainActivity.this, "White Time Over", Toast.LENGTH_SHORT).show();
                    }
                }

            }.start();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
        countDownTimer1.cancel();
    }

}

