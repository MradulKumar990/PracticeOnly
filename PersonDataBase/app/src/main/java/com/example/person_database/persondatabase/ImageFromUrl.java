package com.example.person_database.persondatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mradul on 3/27/2015.
 */
public class ImageFromUrl extends AsyncTask<String, Integer, Bitmap> {

    private ImageView imageView;

    public ImageFromUrl(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        URL url = null;
        try {
            Bitmap image = BitmapFactory.decodeStream(new URL(strings[0]).openConnection().getInputStream());
            return image;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null)
            imageView.setImageBitmap(bitmap);
    }
}

