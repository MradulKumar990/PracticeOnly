package com.example.scrolltabs_fragmantion.scrolltabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mradul on 3/13/2015.
 */
public class FragmentB extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b,container,false);
        TextView text= (TextView) v.findViewById(R.id.frag2);
        try{
            text.setText(getArguments().getString("TAU"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return v;
    }
}
