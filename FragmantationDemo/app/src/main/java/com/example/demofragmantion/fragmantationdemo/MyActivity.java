package com.example.demofragmantion.fragmantationdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

public class MyActivity extends Activity implements Communicator{

    FragmentA fa;
    FragmentB fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        fa= new FragmentA();
        fb= new FragmentB();
        FragmentManager manager= getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.add(R.id.my_layout,fa,"FragA");
        trans.add(R.id.my_layout,fb,"FragV");
            trans.commit();


    }

    @Override
    public void response(String data) {
        fb.changetext(data);
    }
}
