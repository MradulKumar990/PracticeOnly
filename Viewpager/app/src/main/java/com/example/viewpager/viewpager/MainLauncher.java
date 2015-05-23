package com.example.viewpager.viewpager;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ogaclejapan.arclayout.ArcLayout;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mradul on 4/13/2015.
 */
public class MainLauncher extends Activity implements View.OnClickListener, View.OnLongClickListener {


    PackageManager packageManager;
    private ArrayList<Application_info> application_info;
    private AppDataBase appDataBase;
    public Application_info data;
    public ImageView appName1,appName2,appName3,appName4,appName5,appName6,appName7,appName8,appName9,appName10,launch;
    public ImageView[] appNames = new ImageView[10];
    public static MainLauncher mainLauncher;
    public ArcLayout arcLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        if(getActionBar() != null)
            getActionBar().hide();
        mainLauncher = this;
        arcLayout = (ArcLayout) findViewById(R.id.arc_layout);
        TextView date = (TextView) findViewById(R.id.date);
        Date today = new Date();
        date.setText(today.toString());
        /*appName1 = (ImageView) findViewById(R.id.app1);
        appName2 = (ImageView) findViewById(R.id.app2);
        appName3 = (ImageView) findViewById(R.id.app3);
        appName4 = (ImageView) findViewById(R.id.app4);
        appName5 = (ImageView) findViewById(R.id.app5);
        appName6 = (ImageView) findViewById(R.id.app6);
        appName7 = (ImageView) findViewById(R.id.app7);
        appName8 = (ImageView) findViewById(R.id.app8);
        appName9 = (ImageView) findViewById(R.id.app9);
        appName10 = (ImageView) findViewById(R.id.app10);
        launch = (ImageView) findViewById(R.id.launcher);
        appNames[0] = appName1;
        appNames[1] = appName2;
        appNames[2] = appName3;
        appNames[3] = appName4;
        appNames[4] = appName5;
        appNames[5] = appName6;
        appNames[6] = appName7;
        appNames[7] = appName8;
        appNames[8] = appName9;
        appNames[9] = appName10;*/
        packageManager = getPackageManager();
        appDataBase = AppDataBase.singleton(this);
        application_info = appDataBase.getAllData2();
        // gatting arc data
        for(int j=0; j<application_info.size();j++){
            Application_info data = application_info.get(j);
            Log.e("name "+j,data.getName());
        }
        Toast.makeText(this, "size is "+application_info.size(),Toast.LENGTH_LONG).show();
        if(application_info.size() == 0) {
            new MyAsyncTask(this).execute();
            Log.e("mainAsy",String.valueOf(application_info.size()));
        }
        loadHomedata();
    }

    public void loadHomedata(){
        application_info = appDataBase.getAllData2();

        Log.e("SIZE", String.valueOf(application_info.size()));

        for(int i=0; i<application_info.size();i++){
            data = application_info.get(i);
            try {
                if(i<application_info.size()){
                    Log.e("PM",data.getPackageName());
                    CircleImageView icon = new CircleImageView(this);
                    icon.setImageDrawable(packageManager.getApplicationIcon(data.getPackageName()));
                    icon.setBorderWidth(2);
                    icon.setBorderColor(Color.BLACK);
                    icon.setId(i);
                    icon.setLayoutParams(new ArcLayout.LayoutParams(60,60));
                    arcLayout.addView(icon);
                    icon.setOnClickListener(this);/*
                    appNames[i].setImageDrawable(packageManager.getApplicationIcon(data.getPackageName()));*/}
                /*appNames[i].setOnClickListener(this);
                appNames[i].setOnLongClickListener(this);*/

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
//        launch.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        boolean isDefault = false;
        for(int j=0; j<application_info.size();j++){
        Application_info data = application_info.get(j);
            Log.e("imageID",String.valueOf(appNames[j].getId()));
            if(view.getId() == appNames[j].getId()){
                isDefault = true;
                run(data.getPackageName());
                return;
            }
        }

       if(!isDefault){
           run("");
           return;
       }

        if(view.getId() == R.id.launcher){
            Intent intent = new Intent(this, MyActivity.class);
            startActivity(intent);
        }
    }
    public void run (String name){
        Log.e("run",name,null);
            try {
                Intent intent = this.getPackageManager().getLaunchIntentForPackage(name);
                this.startActivity(intent);
            }catch (Exception e){
                Intent intent = new Intent(this,MyActivity.class);
                intent.putExtra("name", String.valueOf(data.getId()));
                Log.e("mainl",String.valueOf(data.getId()));
                this.startActivity(intent);
            }
    }

    @Override
    public boolean onLongClick(View view) {
        for(int j=0; j<application_info.size();j++){
            Application_info data = application_info.get(j);
            if(view.getId() == appNames[j].getId()){
                Intent intent = new Intent(this,MyActivity.class);
                intent.putExtra("name", String.valueOf(data.getId()));
                Log.e("mainl",data.getName());
                this.startActivity(intent);

            }
        }

        return false;
    }
}
