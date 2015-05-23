package com.example.internal_storage.internalstorage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by Mradul on 3/19/2015.
 */
public class NextActivity extends Activity {
    EditText editText1;
    EditText editText2;
    String Default = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);
        editText1= (EditText) findViewById(R.id.editText1);
        editText2= (EditText) findViewById(R.id.editText2);
    }

    public void onLoad(View view){

        FileInputStream fileInputStream = null;
        try {
            fileInputStream=openFileInput("Mytext");
            int read = -1;
            StringBuffer buffer = new StringBuffer();
            while ((read = fileInputStream.read()) != -1){

                buffer.append((char)read);

            }

            String string1 = buffer.substring(0,buffer.indexOf(" "));
            String string2 = buffer.substring(buffer.indexOf(" ")+1);

            editText1.setText(string1);
            editText2.setText(string2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSwitch(View view){

        Toast.makeText(this, "Switch to privios activity succesfull", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MyActivity.class);
        startActivity(intent);
    }

}
