package com.example.shared_preferances.sharedpreferances;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mradul on 3/18/2015.
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
        SharedPreferences sharedPreferences = getSharedPreferences("MyLoginDetails", MODE_PRIVATE);
        String string1 = sharedPreferences.getString("name",Default);
        String string2 = sharedPreferences.getString("password",Default);
        if(string1 == Default || string2 == Default){
            Toast.makeText(this, "No data is there in memory", Toast.LENGTH_SHORT).show();}
        else {
            editText1.setText(string1);
            editText2.setText(string2);
            Toast.makeText(this, "data load sucesssfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSwitch(View view){

        Toast.makeText(this, "Switch to privios activity succesfull", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MyActivity.class);
        startActivity(intent);
    }
}
