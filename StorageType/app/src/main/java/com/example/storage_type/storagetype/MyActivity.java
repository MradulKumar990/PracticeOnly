package com.example.storage_type.storagetype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MyActivity extends Activity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        editText = (EditText) findViewById(R.id.edittext);
    }

    public void SaveInternalMemory(View view){
        String data = editText.getText().toString();
        File folder = getCacheDir();
        File file =  new File(folder,"Mytext1.txt");
        writedata(file,data);


    }

    public void SaveExternalMemory(View view){

        String data = editText.getText().toString();
        File folder = getExternalCacheDir();
        File file =  new File(folder,"Mytext2.txt");
        writedata(file,data);
        Log.e("tipu", file.getAbsolutePath());
    }

    public void SaveEnternalPrivate(View view){
        String data = editText.getText().toString();
        File folder = getExternalFilesDir("Mradul");
        File file =  new File(folder,"Mytext3.txt");
        writedata(file,data);

    }

    public void SaveExternalPublic(View view){
        String data = editText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file =  new File(folder,"Mytext4.txt");
        writedata(file,data);

    }

    private  void writedata(File file, String data){

        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write(data.getBytes());
            message(data+" was sucessfully saved in "+file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void message(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void nextActivity(View view){
        Intent intent = new Intent(this,ActivityB.class);
        startActivity(intent);

    }
}
