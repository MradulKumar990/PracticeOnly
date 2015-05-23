package com.example.person_database.persondatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    public static int male = 0, female = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void AddData (View view){

        Intent intent = new Intent(this, DetailInsertionActivity.class);
        startActivity(intent);
    }

    public void viewData (View view){

        Intent intent = new Intent(this, DetailsDisplayActivity.class);
        startActivity(intent);
    }
}
