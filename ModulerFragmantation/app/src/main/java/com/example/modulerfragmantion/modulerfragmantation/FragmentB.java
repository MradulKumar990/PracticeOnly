package com.example.modulerfragmantion.modulerfragmantation;

import android.app.Activity;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentB extends Fragment {

    TextView text;
    String temp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_b,container,false);
        if(savedInstanceState==null){
            temp=null;
        }
        else{
            temp=savedInstanceState.getString("description");
            TextView text = (TextView) view.findViewById(R.id.textView5);
            text.setText(temp);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        text= (TextView) getActivity().findViewById(R.id.textView5);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(temp,"descriiption");
    }

    public void show_description(int i){
        Resources resources=getResources();
        String[] description =resources.getStringArray(R.array.description);
        text.setText(description[i]);
        temp= description[i];


    }
}
