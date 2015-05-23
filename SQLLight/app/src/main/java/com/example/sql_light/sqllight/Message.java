package com.example.sql_light.sqllight;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Mradul on 3/21/2015.
 */
public class Message {
    public static  void message(Context context, String message){
        message = message + "";
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }

}
