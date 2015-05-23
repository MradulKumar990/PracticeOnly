package com.example.shinesoft_tech.shinessofttech;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


public class MyActivity extends Activity {


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            String filePath = data.getPath();
            Log.e("filepath",filePath);
        }


    }

}
