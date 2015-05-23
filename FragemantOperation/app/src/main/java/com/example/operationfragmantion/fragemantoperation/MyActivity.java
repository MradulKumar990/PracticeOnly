package com.example.operationfragmantion.fragemantoperation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MyActivity extends Activity implements AdapterView.OnItemClickListener {

    ListView list;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        list= (ListView) findViewById(R.id.listView);
        String data[] = getResources().getStringArray(R.array.person);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.group, new FragametName()).addToBackStack("name").commit();
        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        transaction = manager.beginTransaction();
        if(i==0)
            transaction.add(R.id.group, new FragametName()).addToBackStack("name").commit();
        else if(i==1)
            transaction.add(R.id.group, new FragmentImage()).addToBackStack("image").commit();
        else if(i==2)
            transaction.add(R.id.group, new FragmentNo()).addToBackStack("phone").commit();
        else if(i==3)
            transaction.add(R.id.group, new FragmentDesc()).addToBackStack("desc").commit();
        else if(i==4)
            transaction.add(R.id.group, new FragmentEmail()).addToBackStack("email").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId() == R.id.action_settings){
            Toast.makeText(this,"Setting is clicked",Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.xyz){
            finish();
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
