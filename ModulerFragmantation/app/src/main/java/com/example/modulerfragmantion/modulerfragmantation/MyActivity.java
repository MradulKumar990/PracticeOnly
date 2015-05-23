package com.example.modulerfragmantion.modulerfragmantation;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity implements Cumcunicater{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    @Override
    public void response(int i) {
        FragmentManager manager= getFragmentManager();
        FragmentB fb = (FragmentB) manager.findFragmentById(R.id.fragment2);
        fb.show_description(i);

    }
}
