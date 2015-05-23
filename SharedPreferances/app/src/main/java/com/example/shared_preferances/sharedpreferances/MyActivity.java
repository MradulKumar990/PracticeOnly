package com.example.shared_preferances.sharedpreferances;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


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

        SharedPreferences sharedPreferences = getSharedPreferences("MyLoginDetails",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", editText1.getText().toString());
        editor.putString("password", editText2.getText().toString());
        editor.commit();

        Toast.makeText(this,"data save succesfully", Toast.LENGTH_SHORT).show();
    }

    public void onSwitch(View view){

        Intent intent = new Intent(this,NextActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Switch to next activity succesfull", Toast.LENGTH_SHORT).show();
    }

}
