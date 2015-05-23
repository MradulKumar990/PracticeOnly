package com.example.internal_storage.internalstorage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MyActivity extends Activity {

    EditText editText1;
    EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        editText1= (EditText) findViewById(R.id.editText1);
        editText2= (EditText) findViewById(R.id.editText2);
    }

    public void onSave(View view){
        FileOutputStream fileOutputStream = null;
        File file = null;
        String string1 = editText1.getText().toString();
        String string2 = editText2.getText().toString();
        string1 = string1+" ";
        try {
            file=getFilesDir();
            fileOutputStream=openFileOutput("Mytext", MODE_PRIVATE);
            fileOutputStream.write(string1.getBytes());
            fileOutputStream.write(string2.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "data save succesfully"+file+"/Mytext", Toast.LENGTH_SHORT).show();
    }

    public void onSwitch(View view){

        Intent intent = new Intent(this,NextActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Switch to next activity succesfull", Toast.LENGTH_SHORT).show();
    }
}
