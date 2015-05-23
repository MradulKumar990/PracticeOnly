package com.example.image_loading.imageloading;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;


public class MyActivity extends Activity implements View.OnClickListener {

    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    ImageView img;
    Button button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        img = (ImageView)findViewById(R.id.ImageView01);
        button = (Button) findViewById(R.id.Button01);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
//            File file = new File(getPath(data.getData()));
//            if(file.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(getPath(data.getData()));
                img.setImageBitmap(bitmap);
//            }
//            img.setImageURI(data.getData());
            Log.e("TAG", data.getData().toString());
            Log.e("TAG", getPath(data.getData()));
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}


