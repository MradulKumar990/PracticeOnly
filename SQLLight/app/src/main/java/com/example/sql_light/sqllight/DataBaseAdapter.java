package com.example.sql_light.sqllight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by Mradul on 3/21/2015.
 */
public class DataBaseAdapter {
    DataBase dataBase;
    private static DataBaseAdapter dataBaseAdapter;
    public  static SQLiteDatabase sqLiteDatabase;

    private DataBaseAdapter(Context c){
        dataBase = new DataBase(c);
    }

    public static DataBaseAdapter getInstance(Context c){
        if(dataBaseAdapter == null){
            dataBaseAdapter = new DataBaseAdapter(c);
        }
       return dataBaseAdapter;
    }

    public Long insert(String name, String pass){
        sqLiteDatabase = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase.NAME,name);
        contentValues.put(DataBase.PASS, pass);
        Long id = sqLiteDatabase.insert(DataBase.TableName, null, contentValues);
      return id;
    }

    public void selectAll(){
        sqLiteDatabase = dataBase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from " + dataBase.TableName, null);
        Log.e("TAU", cursor.getCount()+"");
        Log.e("TAU","GGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
        while (cursor.moveToNext()){
            for(int i=0; i<cursor.getColumnCount(); i++){
                Log.d("TAU", cursor.getString(i));
            }
            Log.e("TAU","****************************");
        }
    }

    public void deleteall(){
        sqLiteDatabase = dataBase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("DELETE FROM "+dataBase.TableName,null);
        Log.d("DELETE", cursor.getCount()+"");
        Log.e("DELETE","ddddddddddddddddddddddddd");
        while (cursor.moveToNext()){
            for(int i=0; i<cursor.getColumnCount(); i++){
                Log.d("DELETE", cursor.getString(i));
            }
            Log.e("DELETE","-------------------------------");
        }
    }

//
//    public  long Delete(String name,String pass){
//        SQLiteDatabase db = dataBase.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DataBase.NAME,name);
//        contentValues.put(DataBase.PASS, pass);
//        db.delete(DataBase.TableName, DataBase.NAME,);
//    }

 class DataBase extends SQLiteOpenHelper{
     private  static final String DataBaseName = "tipudatabase";
     private  static final String TableName = "TIPU";
     private  static final int DataBaseVersion = 2;
     private  static final String UID = "_id";
     private  static final String NAME = "NAME";
     private static final String PASS = "PASS";
     private  static final String CreateTable = "CREATE TABLE  "+TableName+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255),"+PASS+" VARCHAR(255)); ";
     private  static final String DropTable = "DROP TABLE IF EXISTS " + TableName + ";";
     private Context context;

     public DataBase(Context context) {
         super(context, DataBaseName, null, DataBaseVersion);
         this.context = context;
         Message.message(context,"Constructor called");
     }

     @Override
     public void onCreate(android.database.sqlite.SQLiteDatabase db)  {
         db.execSQL(CreateTable);
         Message.message(context, "onCreate called");
     }

     @Override
     public void onUpgrade(android.database.sqlite.SQLiteDatabase sqLiteDatabase, int i, int i1) {

         Message.message(context,"onUpgrade called");
         sqLiteDatabase.execSQL(DropTable);
         onCreate(sqLiteDatabase);
     }
 }
}
