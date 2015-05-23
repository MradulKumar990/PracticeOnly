package com.example.personworld.personlist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personworld.personlist.R;

/**
 * Created by Mradul on 3/10/2015.
 */
public class UserActivity extends Activity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private ImageView imageview;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name_of_user");
        String email = getIntent().getStringExtra("email_of_user");
        String phone = getIntent().getStringExtra("phone_of_user");
        int image = getIntent().getIntExtra("image_of_user", 0);
        String desc = getIntent().getStringExtra("desc_of_user");

        textView1 = (TextView) findViewById(R.id.textView2);
        textView2= (TextView) findViewById(R.id.textView3);
        textView3 = (TextView) findViewById(R.id.textView4);
        textView4= (TextView) findViewById(R.id.textView5);
        imageview = (ImageView) findViewById(R.id.imageView);
        textView1.setText(name);
        textView2.setText(email);
        textView3.setText(phone);
        textView4.setText(desc);
        imageview.setImageResource(image);
    }
}
