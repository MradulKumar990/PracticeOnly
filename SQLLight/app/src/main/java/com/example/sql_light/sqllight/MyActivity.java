package com.example.sql_light.sqllight;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MyActivity extends Activity {

    EditText username;
    EditText password;
    Button adddata;
    DataBaseAdapter tipu_dataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        username = (EditText) findViewById(R.id.editName);
        password = (EditText) findViewById(R.id.editPassword);

        tipu_dataBaseAdapter = DataBaseAdapter.getInstance(this);

    }


    public void AddData(View view){

        String name = username.getText().toString();
        String paswrd = password.getText().toString();

        Long id = tipu_dataBaseAdapter.insert(name,paswrd);
        if(id>0){
            Message.message(this,"DATA ENTER SUCESSFULLY");
        }
        else{
            Message.message(this,"SOMETHING IS WRONG DUDE");
        }
    }

    public void dataDelete(View view){
        tipu_dataBaseAdapter.deleteall();
    }

    public void dataSelect(View view){
        tipu_dataBaseAdapter.selectAll();
    }


}
