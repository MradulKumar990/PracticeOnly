package com.example.personworld.personlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personworld.personlist.model.Person;
import com.example.personworld.personlist.R;

import java.util.ArrayList;

/**
 * Created by Mradul on 3/9/2015.
 */
public class CustomList extends BaseAdapter {

    public ArrayList<Person> person;
    Context context;

    public CustomList(Context c, ArrayList<Person> person) {
        this.context = c;
        this.person = person;
    }

    @Override
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

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v= inflater.inflate(R.layout.custamised, viewGroup,false);
        TextView name= (TextView) v.findViewById(R.id.textView);
        TextView phone = (TextView) v.findViewById(R.id.textView2);
        TextView email = (TextView) v.findViewById(R.id.textView3);
        TextView description = (TextView) v.findViewById(R.id.textView4);
        ImageView image = (ImageView) v.findViewById(R.id.imageView);

        Person p =person.get(i);

        name.setText(p.getName());
        phone.setText(p.getPhone());
        email.setText(p.getEmail());
        description.setText(p.getDescription());
        image.setImageResource(p.getImage());

        return v;
    }
}

