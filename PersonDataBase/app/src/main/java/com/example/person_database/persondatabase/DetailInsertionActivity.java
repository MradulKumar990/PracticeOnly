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
 * Created by Mradul on 3/22/2015.
 */
public class DetailInsertionActivity extends Activity {

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
        setContentView(R.layout.add_data);

        name = (EditText) findViewById(R.id.editname);
        address = (EditText) findViewById(R.id.editaddress);
        email = (EditText) findViewById(R.id.editemail);
        phone = (EditText) findViewById(R.id.editphone);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        sex = (RadioGroup) findViewById(R.id.sex);
        status = (RadioGroup) findViewById(R.id.status);

        personSQLAdapter = new PersonSQLAdapter(this);

    }

  public void submit(View view){
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
      Log.i("INSERT", pname);
      Log.i("INSERT", paddress);
      Log.i("INSERT", pemail);
      Log.i("INSERT", pphone);
      Log.i("INSERT", pratingbar);
      Log.i("INSERT", psex);
      Log.i("INSERT", pstatus);
      String error ="";
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
      }else{
          Long id = personSQLAdapter.insert(pname,paddress,pphone,pemail,psex,pstatus,pratingbar);

          if(id > 0){
              Message.message(this,"DATA INSERT SUCCESFULLY");
          }else{
              Message.message(this,"SOMETHING IS WRONG BRO");
          }
      }
      try {
          if (error != "") {
              Message.message(this, error + "");
          }
      }catch (NullPointerException e){}





  }
}
