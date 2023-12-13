package com.precioso.group_7_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import broadcast.AlarmService;

public class StopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        // Implement your UI and logic here
        Button stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(v -> {
            // Stop the AlarmService
            Intent stopServiceIntent = new Intent(this, AlarmService.class);
            stopService(stopServiceIntent);

            // Navigate to MainActivity
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);

            // Close the StopAlarmActivity
            finish();
        });
    }
}