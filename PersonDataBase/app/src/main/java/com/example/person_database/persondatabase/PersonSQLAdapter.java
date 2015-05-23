package com.example.person_database.persondatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mradul on 3/22/2015.
 */
public class PersonSQLAdapter {


        private static Persondetail persondetail;
        private static SQLiteDatabase sqLiteDatabase;
        private static PersonSQLAdapter personSQLAdapter;
        private Context context;
        public PersonSQLAdapter(Context context){

            persondetail = new Persondetail(context);
            this.context = context;
        }

        public Long insert(String name, String address, String phone, String email, String sex, String status, String rating ){

            sqLiteDatabase = persondetail.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(persondetail.NAME,name);
            contentValues.put(persondetail.ADDRESS,address);
            contentValues.put(persondetail.PHONE,phone);
            contentValues.put(persondetail.EMAIL,email);
            contentValues.put(persondetail.GENDER,sex);
            contentValues.put(persondetail.STATUS,status);
            contentValues.put(persondetail.RATING,rating);
            Long id = sqLiteDatabase.insert(persondetail.Table_Name,null,contentValues);
            return id;
        }

        public void updatesex(String sex,String name){
            sqLiteDatabase = persondetail.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(persondetail.GENDER,sex);
            sqLiteDatabase.update(persondetail.Table_Name,contentValues,persondetail.NAME+"=?",new String[]{name});
            Message.message(context,"Gender Updated");
//            "UPDATE " + DBConst.TABLE + " SET "   + DBConst.f_DATETIME_NEXT + "=" + _newVal + " WHERE " + DBConst.f_ID +"=?";
            //String[] arg = {name};
           /* String qury = "UPDATE "+persondetail.Table_Name+" SET "+persondetail.GENDER+"="+sex+" WHERE "+persondetail.NAME+"=?";
            Cursor cu = sqLiteDatabase.rawQuery(qury,new String[]{name});
            cu.moveToFirst();
            cu.close();*/

        }
    public void updatedata(String name, String address, String phone, String email, String sex, String status, String rating ){
        Log.e("updatedata","updatedata call");
        Log.i("updatedata", name);
        Log.i("updatedata", address);
        Log.i("updatedata", email);
        Log.i("updatedata", phone);
        Log.i("updatedata", rating);
        Log.i("updatedata", sex);
        Log.i("updatedata", status);
       sqLiteDatabase = persondetail.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(persondetail.ADDRESS,address);
        contentValues.put(persondetail.PHONE,phone);
        contentValues.put(persondetail.EMAIL,email);
        contentValues.put(persondetail.GENDER,sex);
        contentValues.put(persondetail.STATUS,status);
        contentValues.put(persondetail.RATING,rating);
        sqLiteDatabase.update(persondetail.Table_Name,contentValues,persondetail.NAME+"=?",new String[]{name});

        Message.message(context,"DATA Updated");

    }

    public ArrayList<Person> selectAll(){
        MainActivity.female = 0;
        MainActivity.male = 0;
        sqLiteDatabase = persondetail.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from " + persondetail.Table_Name, null);
        ArrayList<Person> person = new ArrayList<Person>();
        while (cursor.moveToNext()){
                int index0 = cursor.getColumnIndex(persondetail.UID);
                int id = Integer.parseInt(cursor.getString(index0));
                 int index1 = cursor.getColumnIndex(persondetail.NAME);
                String name = cursor.getString(index1);
                int index2 = cursor.getColumnIndex(persondetail.EMAIL);
                String email = cursor.getString(index2);
                int index3 = cursor.getColumnIndex(persondetail.PHONE);
                String phone = cursor.getString(index3);
                int index4 = cursor.getColumnIndex(persondetail.ADDRESS);
                String address = cursor.getString(index4);
                int index5 = cursor.getColumnIndex(persondetail.GENDER);
                String gender = cursor.getString(index5);
                int index6 = cursor.getColumnIndex(persondetail.STATUS);
                String status = cursor.getString(index6);
                int index7 = cursor.getColumnIndex(persondetail.RATING);
                String rating = cursor.getString(index7);
                Person persons = new Person(id, name, email, phone, address, gender, status, rating);
                person.add(persons);
                Log.d("DISPLAY", persons.getName());


                if(gender.trim().equalsIgnoreCase("MALE"))
                    MainActivity.male++;
                else
                    MainActivity.female++;


        }

        return person;
    }

     static class Persondetail extends SQLiteOpenHelper{
        private  static final String DataBaseName = "tipudatabase";
        private  static final String Table_Name = "TIPU";
        private  static final int DataBaseVersion = 1;
        private  static final String UID = "_id";
        private  static final String NAME = "NAME";
        private static final String ADDRESS = "ADDRESS";
        private  static final String EMAIL = "EMAIL";
        private  static final String PHONE = "PHONE";
        private static final String GENDER = "GENDER";
        private  static final String RATING = "RATING";
        private static final String STATUS = "STATUS";
        private static final String CREATE_TABLE = "CREATE TABLE "+Table_Name+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+ADDRESS+" VARCHAR(255), "+PHONE+" VARCHAR(255), "+EMAIL+" VARCHAR(255), "+GENDER+" VARCHAR(255), "+STATUS+" VARCHAR(255), "+RATING+" VARCHAR(255));";
        private Context context;
        public Persondetail(Context c) {
            super(c, DataBaseName, null, DataBaseVersion);
            context = c;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
        Message.message(context, "onCreateCall");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        }
    }
}
