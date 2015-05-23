package com.example.personworld.personlist.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.personworld.personlist.model.Person;
import com.example.personworld.personlist.R;
import com.example.personworld.personlist.adapter.CustomList;

import java.util.ArrayList;


public class MyActivity extends Activity implements AdapterView.OnItemClickListener {

    ListView list;
    CustomList cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        list = (ListView) findViewById(R.id.listView);

        ArrayList<Person> person = new ArrayList<Person>();
        Resources res = getResources();
        String arrayname[] = res.getStringArray(R.array.name);
        String arraydesc[] = res.getStringArray(R.array.description);
        String arrayphone[] = res.getStringArray(R.array.phone);
        String arrayemail[] = res.getStringArray(R.array.Email);
        int arrayimage[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9, R.drawable.image10,};
        for (int i = 0; i < 10; i++) {
            String description = arraydesc[i];
            for(int x=0; x<5; x++)
                description = description + "\n" + description;
            person.add(new Person(arrayname[i], arrayphone[i], arrayemail[i],description, arrayimage[i]));
        }
        cv = new CustomList(this, person);
        list.setAdapter(cv);
        list.setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        if(i < list.getCount()-1){
//            Person P= cv.person.get(i);
//            cv.person.set(i, cv.person.get(i+1));
//            cv.person.set(i+1, P);
//        }else{
//            cv.person.add(0,cv.person.get(i));
//            cv.person.remove(i+1);
//        }
//        cv.notifyDataSetChanged();

        Intent intent = new Intent(this, UserActivity.class);
        Person p = cv.person.get(i);
        intent.putExtra("name_of_user",p.getName());
        intent.putExtra("email_of_user",p.getEmail());
        intent.putExtra("phone_of_user",p.getPhone());
        intent.putExtra("image_of_user",p.getImage());
        intent.putExtra("desc_of_user",p.getDescription());
        startActivity(intent);
    }
}




