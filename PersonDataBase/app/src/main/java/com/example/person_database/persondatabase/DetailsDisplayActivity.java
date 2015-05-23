package com.example.person_database.persondatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mradul on 3/23/2015.
 */
public class DetailsDisplayActivity extends Activity{

    ListView listView;
    PersonSQLAdapter personSQLAdapter;
    ArrayList<Person> person;
    PersonListviewAdapter personListviewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_listview_activity);
        listView = (ListView) findViewById(R.id.listViewActivity);
        personSQLAdapter = new PersonSQLAdapter(this);
        ArrayList<Person> person = personSQLAdapter.selectAll();
        personListviewAdapter = new PersonListviewAdapter(this,person);
        listView.setAdapter(personListviewAdapter);


    }
}
