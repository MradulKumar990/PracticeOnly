package com.example.listview_more_loader.listviewmoreloader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        /*FrameLayout footerLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.footer,null);
        TextView footer = (TextView) footerLayout.findViewById(R.id.footer);*/
        TextView footer = new TextView(this);
        footer.setText("Loading...");
        footer.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        footer.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        footer.setTextSize(20);
        ListView lv = (ListView) findViewById(R.id.list_view);
        lv.addFooterView(footer);

        //--page size = 10--
        ProdAdapter ad = new ProdAdapter(this,10, footer);
        lv.setAdapter(ad);

        //--load first 10 items--
        LoaderTask t = new LoaderTask(0,10,this,ad);
        t.execute();
    }

}
