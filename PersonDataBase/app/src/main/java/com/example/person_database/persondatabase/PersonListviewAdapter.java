package com.example.person_database.persondatabase;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mradul on 3/23/2015.
 */
public class PersonListviewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Person> person;
    PersonListviewAdapter personListviewAdapter;

    public PersonListviewAdapter(Context context, ArrayList<Person> person){
        this.context = context;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.person_listview,viewGroup,false);
        TextView name = (TextView) row.findViewById(R.id.name);
        TextView email = (TextView) row.findViewById(R.id.email);
        TextView phone = (TextView) row.findViewById(R.id.phone);
        TextView address = (TextView) row.findViewById(R.id.address);
        TextView gender = (TextView) row.findViewById(R.id.sex);
        TextView status = (TextView) row.findViewById(R.id.status);
        TextView genderCountTextView = (TextView) row.findViewById(R.id.total);
        RatingBar rating = (RatingBar) row.findViewById(R.id.ratingBar);
        final ImageView profile_pic = (ImageView) row.findViewById(R.id.gender_pic);



        final PersonSQLAdapter personSQLAdapter = new PersonSQLAdapter(context);

        final Person p = person.get(i);

        if(p.getSex().trim().equals("MALE") ){
            profile_pic.setImageResource(R.drawable.male);
        }else{
            profile_pic.setImageResource(R.drawable.female);
        }
//        new ImageFromUrl(profile_pic).execute("http://media.indiedb.com/images/articles/1/130/129132/15446212-demo-icon-on-glossy-blu.jpg");
        Picasso.with(context)
                .load("http://media.indiedb.com/images/articles/1/130/129132/15446212-demo-icon-on-glossy-blu.jpg")
                .into(profile_pic);
        Log.i("pic", p.getName());

        name.setText(p.getName());
        email.setText(p.getEmail());
        phone.setText(p.getPhone());
        address.setText(p.getAddress());
        gender.setText(p.getSex());
        status.setText(p.getStatus());
        rating.setRating(Float.parseFloat(p.getRating()));

        genderCountTextView.setText(p.getSex().trim().equalsIgnoreCase("MALE") ? MainActivity.male+"" : MainActivity.female+"");

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("LLLL", "LIST IS CLICKED");

                Intent intent = new Intent(context, DetailUpdateActivity.class);
                intent.putExtra("id", p.getId());
                intent.putExtra("name", p.getName());
                intent.putExtra("email",p.getEmail());
                intent.putExtra("phone",p.getPhone());
                intent.putExtra("address",p.getAddress());
                intent.putExtra("sex",p.getSex());
                intent.putExtra("status",p.getStatus());
                intent.putExtra("rating",p.getRating());
                context.startActivity(intent);
            }
        });


        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p.getSex().equalsIgnoreCase("MALE")){
//                    Database female update for selected user.
                    p.setSex("FEMALE");
                    personSQLAdapter.updatesex(p.getSex(),p.getName());
                    p.setSex("FEMALE");
                    MainActivity.male--;
                    MainActivity.female++;
                }else if(p.getSex().equalsIgnoreCase("FEMALE")){
//                    Database male update for selected user.
                    p.setSex("MALE");
                    personSQLAdapter.updatesex(p.getSex(),p.getName());
                    MainActivity.male++;
                    MainActivity.female--;
                }
                notifyDataSetChanged();
            }
        });

        return row;
    }
}
