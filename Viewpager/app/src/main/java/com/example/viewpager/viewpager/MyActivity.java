package com.example.viewpager.viewpager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity {

    public static MyActivity activity;
    public GridView gridView;
    private ArrayList<Application_info> application_info;
    private AppDataBase appDataBase;
    private String longPress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if(getActionBar() != null)
            getActionBar().hide();
        activity = this;
        gridView = (GridView) findViewById(android.R.id.list);
        longPress = getIntent().getStringExtra("name");
        if (longPress != null){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Select any one application for replacement");
            dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MyActivity.this,"Ok Clicked", Toast.LENGTH_LONG).show();
                        }
                    });
                    dialog.show();
        }
        try {
            Log.e("myactivity",longPress);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        appDataBase = AppDataBase.singleton(this);
        application_info = appDataBase.getAllData();
        loadData();
        new MyAsyncTask(this).execute();

    }

    public void loadData() {
        application_info = appDataBase.getAllData();
        if (application_info.size() > 0)
            activity.gridView.setAdapter(new ApplicationAdapter(activity, activity.application_info,longPress));
    }
}
