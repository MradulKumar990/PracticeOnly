package com.example.modulerfragmantion.modulerfragmantation;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class FragmentA extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;
    Cumcunicater cum;
    FragmentA fa;
    FragmentB fb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_a,container,false);
        list = (ListView)v.findViewById(R.id.listView);
        Context context= getActivity();
        //ArrayAdapter adapter =ArrayAdapter.createFromResource(getActivity(),R.array.name,android.R.layout.simple_list_item_1);
        ArrayList<Person> person = new ArrayList<Person>();
        Resources res=getResources();
        String name[]= res.getStringArray(R.array.name);
        //String description[]= res.getStringArray(R.array.description);
        int image[]={R.drawable.index14,R.drawable.index15,R.drawable.index16,R.drawable.index17};
        for(int i=0;i<4;i++){
            person.add(new Person(name[i], image[i]));

        }
        final CutomisedAddapter ca=new CutomisedAddapter(person,context);
        list.setAdapter(ca);
        list.setOnItemClickListener(this);
        return  v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cum= (Cumcunicater) getActivity();



    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        cum.response(i);
    }
}
