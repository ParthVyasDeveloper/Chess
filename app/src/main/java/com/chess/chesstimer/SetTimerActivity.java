package com.chess.chesstimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalTime;

public class SetTimerActivity extends AppCompatActivity {

    EditText edHours, edMinutes, edSeconeds;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer);

        edHours = findViewById(R.id.ed_hour);
        edMinutes = findViewById(R.id.ed_minute);
        edSeconeds = findViewById(R.id.ed_second);
        btnNext = findViewById(R.id.btn_set);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                convertTimeToMilli();
            }
        });

        edSeconeds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edSeconeds.getText().toString().trim().equalsIgnoreCase("")) {
                    if (Integer.parseInt(edSeconeds.getText().toString()) > 59) {
                        edSeconeds.setError("Seconds Can Not Bigger Then 60");
                    }
                }

            }
        });

        edMinutes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!edMinutes.getText().toString().trim().equalsIgnoreCase("")) {
                    if (Integer.parseInt(edMinutes.getText().toString()) > 59) {
                        edMinutes.setError("Minutes Can Not Bigger Then 60");
                    }
                }

            }
        });
        edHours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!edHours.getText().toString().trim().equalsIgnoreCase("")) {
                    if (Integer.parseInt(edHours.getText().toString()) > 24) {
                        edHours.setError("Hours Can Not Bigger Then 24");
                    }
                }

            }
        });
    }

    private void convertTimeToMilli() {


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            String hh, mm, ss;
            hh = edHours.getText().toString();
            mm = edMinutes.getText().toString();
            ss = edSeconeds.getText().toString();

            if (hh.trim().equalsIgnoreCase("")) {
                edHours.setError("Enter Hours");
            } else if (mm.trim().equalsIgnoreCase("")) {
                edMinutes.setError("Enter Minutes");
            } else if (ss.trim().equalsIgnoreCase("")) {
                edSeconeds.setError("Enter Seconds");
            }else{

                if (hh.length() == 1) {
                    hh = "0" + hh;
                } else if (mm.length() == 1) {
                    mm = "0" + mm;
                } else if (ss.length() == 1) {
                    ss = "0" + ss;
                }
                if(Integer.parseInt(hh) < 24 || Integer.parseInt(mm) < 59 || Integer.parseInt(ss) < 59){

                    String time = hh + ":" + mm + ":" + ss;

                    LocalTime localTime = LocalTime.parse(time);
                    int millis = localTime.toSecondOfDay() * 1000;

                    if(millis > 10000){
                        Intent intent = new Intent(SetTimerActivity.this, MainActivity.class);
                        intent.putExtra("setTime", String.valueOf(millis));
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Set Timer Bigger Then 1 Minute", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(this, "Invalid Time !", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }


}