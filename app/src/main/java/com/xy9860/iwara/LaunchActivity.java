package com.xy9860.iwara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import static android.os.SystemClock.sleep;

public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        sleep(1000);
        startActivity(new Intent(LaunchActivity.this,MainActivity.class));
        LaunchActivity.this.finish();
    }
}
