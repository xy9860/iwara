package com.xy9860.iwara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LaunchActivity extends AppCompatActivity {
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TimerTask task = new TimerTask(){
            public void run(){
                 startActivity(new Intent(LaunchActivity.this,MainActivity.class));
                 LaunchActivity.this.finish();
            }
        };
        timer = new Timer();
        timer.schedule(task,1000);
    }
    @Override
    public void onBackPressed() {
        timer.cancel();
        finish();
        System.exit(0);
    }
}
