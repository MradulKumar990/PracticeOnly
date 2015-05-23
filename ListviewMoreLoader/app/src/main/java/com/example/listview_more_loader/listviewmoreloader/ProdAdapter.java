package com.example.listview_more_loader.listviewmoreloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Mradul on 4/8/2015.
 */
public class ProdAdapter extends ArrayAdapter<Product> {
    private boolean mHasMoreItems;
    private final int mPageSize;
    private final TextView mFooter;

    public ProdAdapter(Context context, int pageSize, TextView footer) {
        super(context, android.R.layout.two_line_list_item);
        mPageSize = pageSize;
        mFooter = footer;
        mHasMoreItems = true;
    }

    public void notifyNoMoreItems(){
        mHasMoreItems = false;
        mFooter.setText("No more Items");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(position == getCount() - 1 && mHasMoreItems){
            LoaderTask t = new LoaderTask(position + 1, position + 1 + mPageSize, getContext(),this);
            t.execute();
            mFooter.setText("Loading . . .");
        }


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item,parent,false);

            TextView t1 = (TextView) convertView.findViewById(R.id.text1);
            TextView t2 = (TextView) convertView.findViewById(R.id.text2);
            TextView t3 = (TextView) convertView.findViewById(R.id.text3);

            convertView.setTag(new Holder(t1,t2,t3));

        }


        Product p = getItem(position);
        Holder h = (Holder) convertView.getTag();

        h.t1.setText("Product "+p.name);
        h.t2.setText("Made by "+p.company);
        h.t3.setText("Delivered to "+p.address+" in "+p.city);

        return convertView;
    }

    private static class Holder{
        public final TextView t1;
        public final TextView t2;
        public final TextView t3;

        private Holder(TextView t1, TextView t2, TextView t3) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
        }
    }
}
