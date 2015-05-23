package com.example.person_database.persondatabase;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Mradul on 3/22/2015.
 */
 public class Message {
   public static void message(Context c, String message){
        message = message + "";
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();


    }
}
