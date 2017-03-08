package com.xdandroid.sample;

import android.graphics.*;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;

import com.xdandroid.materialprogressview.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        final MaterialProgressView progressView = (MaterialProgressView) findViewById(R.id.progress_view);

        findViewById(R.id.button0).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progressView.setColorSchemeColors(new int[]{MainActivity.this.getResources().getColor(R.color.colorPrimary), MainActivity.this.getResources().getColor(R.color.colorAccent)});
                progressView.setProgressBackgroundColor(Color.parseColor("#FAFAFA"));
            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progressView.setColorSchemeColors(new int[]{MainActivity.this.getResources().getColor(android.R.color.darker_gray)});
                progressView.setProgressBackgroundColor(Color.parseColor("#333333"));
            }
        });
    }
}
