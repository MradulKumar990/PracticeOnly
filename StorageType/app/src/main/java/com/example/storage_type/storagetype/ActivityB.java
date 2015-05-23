package com.example.storage_type.storagetype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Mradul on 3/20/2015.
 */
public class ActivityB extends Activity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        editText= (EditText)findViewById(R.id.edittext);
    }

    public void loadInternalMemory(View view){

        File folder = getCacheDir();
        File file = new File(folder,"Mytext1.txt");
        String data = readData(file);
        if(data != null){
            editText.setText(data);

        }else{
            editText.setText("data not found");
        }

    }

    public void loadExternalMemory(View view){

        File folder = getExternalCacheDir();
        File file = new File(folder,"Mytext2.txt");
        String data = readData(file);
        if(data != null){
            editText.setText(data);

        }else{
            editText.setText("data not found");
        }
    }

    public void loadEnternalPrivate(View view){
        File folder = getExternalFilesDir("Mradul");
        File file = new File(folder,"Mytext3.txt");
        String data = readData(file);
        if(data != null){
            editText.setText(data);

        }else{
            editText.setText("data not found");
        }

    }

    public void loadExternalPublic(View view){

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder,"Mytext4.txt");
        String data = readData(file);
        if(data != null){
            editText.setText(data);

        }else{
            editText.setText("data not found");
        }



    }

    public void previousActivity(View view){

        Intent intent = new Intent(this,MyActivity.class);
        startActivity(intent);
    }

    public String readData(File file){

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            StringBuffer stringBuffer = new StringBuffer();
            int read = -1;
            while((read = fileInputStream.read())!= -1){

                stringBuffer.append((char)read);
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  null;
    }
}
