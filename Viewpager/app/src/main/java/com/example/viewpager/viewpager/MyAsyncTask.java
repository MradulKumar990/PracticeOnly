package com.example.viewpager.viewpager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mradul on 4/11/2015.
 */
public class MyAsyncTask extends AsyncTask<Void, Void, Integer> {

    private AppDataBase appDataBase;
    private Context context;

    public MyAsyncTask(Context context) {
        this.context = context;
        appDataBase = AppDataBase.singleton(context);
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        int result = 0;
        int j = 0;
        ArrayList<Application_info> applicationInfoList = appDataBase.getAllData();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> listAppInfo = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo packageInfo : listAppInfo) {
            if(context.getPackageManager().getLaunchIntentForPackage(packageInfo.packageName) != null) {
                long id = appDataBase.addAppData(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString(), packageInfo.packageName);
        Log.e("PackageName",packageInfo.packageName);
        if(j<10){
            appDataBase.addAppData2(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString(), packageInfo.packageName);
            j++;
        }
        if (id == -1)
            result = -555;
    }
}


        if (listAppInfo.size() < applicationInfoList.size()) {
            for (int i = 0; i < applicationInfoList.size(); i++) {
                Application_info appInfo = applicationInfoList.get(i);
                for(PackageInfo p : listAppInfo){
                    if(!p.packageName.equals(appInfo.getPackageName())) {
                        appDataBase.deleteData(appInfo.getName(), appInfo.getPackageName());
                        result++;
                    }
                }
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if(MyActivity.activity != null) MyActivity.activity.loadData();
        if(MainLauncher.mainLauncher != null) MainLauncher.mainLauncher.loadHomedata();

    }
}