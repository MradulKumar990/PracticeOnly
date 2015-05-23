package com.example.person_database.persondatabase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

/**
 * Created by Mradul on 3/24/2015.
 */
public class DetailUpdateActivity extends Activity {

    EditText name;
    EditText address;
    EditText email;
    EditText phone;
    RatingBar ratingBar;
    RadioGroup sex , status;
    RadioButton sexbutton;
    RadioButton statusbutton;
    PersonSQLAdapter personSQLAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data);
        String iname = getIntent().getStringExtra("name");
        String iemail = getIntent().getStringExtra("email");
        String iaddress = getIntent().getStringExtra("address");
        String iphone = getIntent().getStringExtra("phone");
        String isex = getIntent().getStringExtra("sex");
        String istatus = getIntent().getStringExtra("status");
        String irating = getIntent().getStringExtra("rating");

        name = (EditText) findViewById(R.id.editname);
        address = (EditText) findViewById(R.id.editaddress);
        email = (EditText) findViewById(R.id.editemail);
        phone = (EditText) findViewById(R.id.editphone);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        sex = (RadioGroup) findViewById(R.id.sex);
        status = (RadioGroup) findViewById(R.id.status);

        name.setText(iname);
        address.setText(iemail);
        email.setText(iaddress);
        phone.setText(iphone);
        ratingBar.setRating(Float.parseFloat(irating));
        if(isex.equalsIgnoreCase("MALE"))
            sex.check(R.id.male);
        else
            sex.check(R.id.female);

        if(istatus.equalsIgnoreCase("SINGLE"))
            status.check(R.id.single);
        else
            status.check(R.id.married);

        personSQLAdapter = new PersonSQLAdapter(this);

    }

    public void update(View view){

        String pname = name.getText().toString();
        String paddress = address.getText().toString();
        String pemail = email.getText().toString();
        String pphone = phone.getText().toString();
        Float  fratingbar = ratingBar.getRating();
        String pratingbar = fratingbar.toString();
        int sextype = sex.getCheckedRadioButtonId();
        sexbutton = (RadioButton) findViewById(sextype);
        String psex = sexbutton.getText().toString();
        int statustype = status.getCheckedRadioButtonId();
        statusbutton = (RadioButton) findViewById(statustype);
        String pstatus = statusbutton.getText().toString();

        Log.i("UPDATE", pname);
        Log.i("UPDATE", paddress);
        Log.i("UPDATE", pemail);
        Log.i("UPDATE", pphone);
        Log.i("UPDATE", pratingbar);
        Log.i("UPDATE", psex);
        Log.i("UPDATE", pstatus);
      /*  String error ="";
        if(pname == ""){
            error = "pleases enter the name ";
        }else if ( paddress == ""){
            error = "pleases enter the address  ";
        }else if ( pemail == ""){
            error = "pleases enter the email  ";
        }else if (pphone  == ""){
            error = "pleases enter the phone ";
        }else if ( pratingbar == ""){
            error = "pleases rating the star  ";
        }else if ( psex == ""){
            error = "pleases choose the sex  ";
        }else if ( pstatus == ""){
            error = "pleases choose the status  ";
        }else{*/
            personSQLAdapter.updatedata(pname,paddress,pphone,pemail,psex,pstatus,pratingbar);
       /* }
        try {
            if (error != "") {
                Message.message(this, error + "");
            }
        }catch (NullPointerException e){}*/





    }
}
