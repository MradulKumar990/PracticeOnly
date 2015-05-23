package com.example.helloworld.hello;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.zip.Inflater;


public class Mainactivity extends Activity {

    ListView list;
    int image[]={R.drawable.index1,R.drawable.index2,R.drawable.index3,R.drawable.index4,R.drawable.index5,R.drawable.index6,R.drawable.index7,R.drawable.index8,R.drawable.index9,R.drawable.index10,R.drawable.index11};
    String pername[];
    String perdesc[];
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);

           Resources res=getResources();
           pername=res.getStringArray(R.array.name);
           perdesc=res.getStringArray(R.array.description);

           list= (ListView) findViewById(R.id.listView);
           Customview cv= new Customview(this,image,pername,perdesc);
           list.setAdapter(cv);





    }

    class Customview extends ArrayAdapter<String>{
        Context context;
        int[] image;
        String arrayname[],arraydesc[];

        public Customview(Context c, int[] imag, String pername[], String perdesc[]){
            super(c,R.layout.custom,R.id.textView,pername);
            this.context =c;
            this.image=imag;
            this.arrayname=pername;
            this.arraydesc=perdesc;
        }

        class Viewholder {
            ImageView vimageview;
            TextView vtextview1;
            TextView vtextview2;

            public Viewholder(View v) {
                vimageview = (ImageView) v.findViewById(R.id.imageView);
                vtextview1 = (TextView) v.findViewById(R.id.textView);
                vtextview2 = (TextView) v.findViewById(R.id.textView2);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vv= convertView;
            Viewholder holder =null;
            if(vv==null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                vv = inflater.inflate(R.layout.custom, parent, false);
                holder= new Viewholder(vv);
                vv.setTag(holder);
            }
            else{
                holder= (Viewholder) vv.getTag();
            }

            holder.vimageview.setImageResource(image[position]);
            holder.vtextview1.setText(arrayname[position]);
            holder.vtextview2.setText(arraydesc[position]);

            return vv;
        }
    }
}
