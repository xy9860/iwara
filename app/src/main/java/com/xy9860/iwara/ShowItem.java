package com.xy9860.iwara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowItem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item);
        Intent item = getIntent();
        String uri = item.getStringExtra("URI");
        TextView textView = findViewById(R.id.show_item);
        textView.setText(uri);
    }
    @Override
    public void onBackPressed() {
        finish();
        /*fsaffs*/
        overridePendingTransition(R.anim.index_in,R.anim.show_item_out);
    }
}