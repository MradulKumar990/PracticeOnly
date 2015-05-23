package com.example.modulerfragmantion.modulerfragmantation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CutomisedAddapter extends BaseAdapter {
    public ArrayList<Person> person;

    Context context;

    public CutomisedAddapter(ArrayList<Person> person,Context c) {
        this.context=c;
        this.person=person;
    }

    public int getCount() {
        return person.size();
    }

    @Override
    public Object getItem(int i) {
        return person.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.customised_layout,viewGroup,false);
        TextView name= (TextView) v.findViewById(R.id.textView);
        ImageView image= (ImageView) v.findViewById(R.id.imageView);

        Person p=person.get(i);
        name.setText(p.getname());
        image.setImageResource(p.getimage());


        return v;
    }
}
