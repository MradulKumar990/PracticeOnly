package com.example.viewpager.viewpager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Mradul on 4/8/2015.
 */
public class ApplicationAdapter extends BaseAdapter {

    public static ArrayList<Application_info> applicationInfos;
    Context context;
    String longPress;
    PackageManager packageManager;
    AppDataBase appDataBase;

    public ApplicationAdapter(Context c, ArrayList<Application_info> data,String longPress){
        context = c;
        this.longPress = longPress;
        applicationInfos = data;
        packageManager = context.getPackageManager();
        appDataBase = AppDataBase.singleton(context);
    }

    @Override
    public int getCount() {
        return applicationInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return applicationInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView appName;
        ImageView appIcon;
        ViewHolder(View view){
            appName = (TextView) view.findViewById(R.id.app_name);
            appIcon = (ImageView) view.findViewById(R.id.app_icon);
        }
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = null;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_list_row,viewGroup,false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else{
             holder = (ViewHolder) row.getTag();
        }

        final Application_info data = applicationInfos.get(i);

        holder.appName.setText(data.getName());

        try {
            Drawable icon = packageManager.getApplicationIcon(data.getPackageName());
            holder.appIcon.setImageDrawable(icon);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        /*row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = packageManager.getLaunchIntentForPackage(ApplicationAdapter.applicationInfos.get(i).getPackageName());
                    if (null != intent) {
                        context.startActivity(intent);
                    }
                }catch (Exception e){
                    Toast.makeText(context, "Activity not found", Toast.LENGTH_LONG).show();
                }
            }
        });*/

        if(longPress != null){

            Log.e("adap",longPress);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Long click item"+longPress, Toast.LENGTH_LONG).show();
                    appDataBase.updateData(longPress, data.getName(), data.getPackageName());
                    Intent intent = new Intent(context,MainLauncher.class);
                    context.startActivity(intent);

                }
            });
        }else{
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "normal click", Toast.LENGTH_LONG).show();
                    try {
                        Intent intent = packageManager.getLaunchIntentForPackage(ApplicationAdapter.applicationInfos.get(i).getPackageName());
                        if (null != intent) {
                            context.startActivity(intent);
                        }
                    }catch (Exception e){
                        Toast.makeText(context, "Activity not found", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        return row;
    }
}