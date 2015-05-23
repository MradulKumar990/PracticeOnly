package com.example.mradul.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class MyActivity extends Activity{

    ListView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        l= (ListView) findViewById(R.id.listview);
        l.setAdapter(new CustomBaseAdapter(this));

    }

    class Singlerow{
        int image;
        String name;
        String description;

        public Singlerow(int image, String name,String description){
            this.image=image;
            this.name=name;
            this.description=description;
        }
    }

    class CustomBaseAdapter extends BaseAdapter {

        ArrayList<Singlerow> list = null;
        Context context;

        public CustomBaseAdapter(Context c) {
            context = c;
            list = new ArrayList<Singlerow>();
            Resources res = c.getResources();
            String[] pername = res.getStringArray(R.array.name);
            String[] perdesc = res.getStringArray(R.array.description);
            int image[] = {R.drawable.index1, R.drawable.index2, R.drawable.index3, R.drawable.index4, R.drawable.index5, R.drawable.index6, R.drawable.index7, R.drawable.index8, R.drawable.index9, R.drawable.index10, R.drawable.index11,};
            for (int i = 0; i < 11; i++) {
                list.add(new Singlerow(image[i], pername[i], perdesc[i]));
            }

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.custom,viewGroup,false);
            TextView name= (TextView) row.findViewById(R.id.textView);
            TextView description = (TextView) row.findViewById(R.id.textView2);
            ImageView image = (ImageView) row.findViewById(R.id.imageView);

           Singlerow sr =list.get(i);

            name.setText(sr.name);
            description.setText(sr.description);
            image.setImageResource(sr.image);

            return row;
        }
    }

}
