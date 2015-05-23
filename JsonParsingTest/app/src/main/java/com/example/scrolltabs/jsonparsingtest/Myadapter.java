package com.example.scrolltabs.jsonparsingtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Mradul on 3/15/2015.
 */
public class Myadapter extends BaseAdapter {
    String url;
    ArrayList<Person> person;
    Context context;

    public Myadapter(Context c, ArrayList<Person> p) {
        context = c;
        person = p;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.person_layout,viewGroup,false);
        TextView name = (TextView) v.findViewById(R.id.person_name);
        TextView dob = (TextView) v.findViewById(R.id.person_dob);
        TextView country = (TextView) v.findViewById(R.id.person_country);
        TextView children = (TextView) v.findViewById(R.id.person_children);
        TextView height = (TextView) v.findViewById(R.id.person_height);
        TextView description = (TextView) v.findViewById(R.id.person_description);
        TextView spouse = (TextView) v.findViewById(R.id.person_spouse);
        ImageView imageView= (ImageView) v.findViewById(R.id.profile_pic);

        Person p = person.get(i);

        name.setText(p.getName());
        dob.setText(p.getDob());
        country.setText(p.getCountry());
        children.setText(p.getChildren());
        height.setText(p.getHeight());
        description.setText(p.getDescription());
        spouse.setText(p.getSpouse());
        url=p.getImage();
        Log.i("adpterIMage",url);
        Picasso.with(context).load(url).into(imageView);

     // new DownloadAsynTask(imageView).execute(url);
        return v ;
    }

    private  class DownloadAsynTask extends AsyncTask<String, Void , Bitmap>{
            ImageView imageview;
            Bitmap bitmap=null;

        public DownloadAsynTask(ImageView imageView) {
            this.imageview = imageView;
        }

        @Override
        protected Bitmap doInBackground(String[] url) {
            //String urldisplay = url[0];
            try{
               /* URL aUrl = new URL(urldisplay);
                URLConnection urlConnection = aUrl.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(in);
                bitmap = BitmapFactory.decodeStream(bis);
                bis.close();
                in.close();

*/
                bitmap = BitmapFactory.decodeStream(new URL(url[0]).openConnection().getInputStream());
                return bitmap;
            }catch (Exception e){
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap result){
            if(result != null || imageview!= null){
            imageview.setImageBitmap(result);}
        }


    }
}
