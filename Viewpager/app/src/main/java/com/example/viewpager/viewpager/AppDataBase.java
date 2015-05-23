package com.example.viewpager.viewpager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Mradul on 4/10/2015.
 */

public class AppDataBase {
    private AppQueries appQueries;
    private SQLiteDatabase sqLiteDatabase;
    private static AppDataBase appDataBase;

    private AppDataBase(Context context) {
        appQueries = new AppQueries(context);
        sqLiteDatabase = appQueries.getWritableDatabase();
    }

    public static AppDataBase singleton(Context context) {
        if (appDataBase == null)
            appDataBase = new AppDataBase(context);
        return appDataBase;
    }

    public long addAppData(String appName, String packageName) {
        Cursor cursor = sqLiteDatabase.query(AppQueries.Table_Name,
                new String[]{AppQueries.NAME, AppQueries.PACKAGE_NAME},
                AppQueries.NAME + " = ? AND " + AppQueries.PACKAGE_NAME + " = ?",
                new String[]{appName, packageName}, null, null, null);

        if (cursor.getCount() > 0)
            return -1;

        ContentValues contentValues = new ContentValues();
        contentValues.put(AppQueries.NAME, appName);
        contentValues.put(AppQueries.PACKAGE_NAME, packageName);
        return sqLiteDatabase.insert(AppQueries.Table_Name, null, contentValues);
    }

    public ArrayList<Application_info> getAllData() {
        Cursor cursor = sqLiteDatabase.query(AppQueries.Table_Name,
                new String[]{AppQueries.UID, AppQueries.NAME, AppQueries.PACKAGE_NAME}
                ,null,null , null, null,AppQueries.NAME, null);

        ArrayList<Application_info> application_info = new ArrayList<Application_info>();
        while (cursor.moveToNext()) {
            Application_info singleData = new Application_info(cursor.getString(1),
                    cursor.getString(2), cursor.getInt(0));
            application_info.add(singleData);
        }
        return application_info;
    }



    public long addAppData2(String appName, String packageName) {
        Cursor cursor = sqLiteDatabase.query(AppQueries.Table_Name2,
                new String[]{AppQueries.NAME, AppQueries.PACKAGE_NAME},
                AppQueries.NAME + " = ? AND " + AppQueries.PACKAGE_NAME + " = ?",
                new String[]{appName, packageName}, null, null, null);

        if (cursor.getCount() > 0)
            return -1;

        ContentValues contentValues = new ContentValues();
        contentValues.put(AppQueries.NAME, appName);
        contentValues.put(AppQueries.PACKAGE_NAME, packageName);
        return sqLiteDatabase.insert(AppQueries.Table_Name2, null, contentValues);
    }
    public ArrayList<Application_info> getAllData2() {
        Cursor cursor = sqLiteDatabase.query(AppQueries.Table_Name2 ,
                new String[]{AppQueries.UID, AppQueries.NAME, AppQueries.PACKAGE_NAME}
                ,null,null , null, null,AppQueries.NAME, null);

        ArrayList<Application_info> application_info2 = new ArrayList<Application_info>();
        int i = 0;
        while (cursor.moveToNext()) {
            if (i<10) {
            i++;
            Application_info singleData = new Application_info(cursor.getString(1),
                    cursor.getString(2), cursor.getInt(0));
            application_info2.add(singleData);
            }
        }
        return application_info2;
    }

    public void updateData(String wVale, String data1, String data2){
        ContentValues values = new ContentValues();
        values.put(AppQueries.NAME,data1);
        values.put(AppQueries.PACKAGE_NAME,data2);
        sqLiteDatabase.update(AppQueries.Table_Name2,values,AppQueries.UID+"=?", new String[]{wVale});
    }


    public void deleteData(String appName, String packageName) {
        sqLiteDatabase.delete(AppQueries.Table_Name,
                AppQueries.NAME + " = ? AND " + AppQueries.PACKAGE_NAME + " = ?",
                new String[]{appName, packageName});
    }

    static public class AppQueries extends SQLiteOpenHelper {
        private static final String DataBaseName = "dbLauncher";
        private static final String Table_Name = "tableLauncher";
        private static final String Table_Name2 = "HomeLauncher";
        private static final int DataBaseVersion = 1;
        private static final String UID = "_id";
        private static final String NAME = "AppName";
        private static final String PACKAGE_NAME = "PackageName";
        private static final String CREATE_TABLE = "CREATE TABLE " + Table_Name + " (" + UID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " + PACKAGE_NAME + " VARCHAR(255));";
        private static final String CREATE_TABLE2 = "CREATE TABLE " + Table_Name2 + " (" + UID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " + PACKAGE_NAME + " VARCHAR(255));";
        private final String DROP_READER_TABLE = "DROP TABLE IF EXISTS " + AppQueries.Table_Name;

        public AppQueries(Context context) {
            super(context, DataBaseName, null, DataBaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            sqLiteDatabase.execSQL(CREATE_TABLE2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
            sqLiteDatabase.execSQL(DROP_READER_TABLE);
            onCreate(sqLiteDatabase);
        }
    }
}
