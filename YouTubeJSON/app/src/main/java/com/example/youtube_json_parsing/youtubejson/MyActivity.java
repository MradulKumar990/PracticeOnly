package com.example.youtube_json_parsing.youtubejson;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MyActivity extends Activity {

    ArrayList<YoutubeData> youtubeDatas;
    ListView listView;
    JsonData jj = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        String data = jj.jsonData;
        Log.e("JSONDATA", data);
        listView = (ListView) findViewById(R.id.listView);
        youtubeDatas = new ArrayList<YoutubeData>();
        JSONArray jsonArray = null;
        try {
            JSONObject jsonObject = new JSONObject(data);
            jsonArray = jsonObject.getJSONArray("items");
            JSONObject singlrow;
            for(int i=0 ; i< jsonArray.length(); i++){
                singlrow = jsonArray.getJSONObject(i);
                youtubeDatas.add(new YoutubeData(singlrow.getString("id"),singlrow.getString("uploader"),singlrow.getString("title"),singlrow.getString("description"), singlrow.getJSONObject("thumbnail").getString("sqDefault")));
                Log.e("image url",singlrow.getJSONObject("thumbnail").getString("sqDefault"));
                Log.e("id",singlrow.getString("id"));
            }
            listView.setAdapter(new YoutubeAdapter(this,youtubeDatas));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
/*
    class MyAysinkTaskManager extends AsyncTask<String, Void, JSONObject>{

        InputStream is = null;
        JSONObject jObj = null;
        String json = "";
        ProgressDialog dialog;
        public MyAysinkTaskManager(){
            dialog = new ProgressDialog(MyActivity.this);
            dialog.setTitle("Loadinggg......");
        }

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

       *//* @Override
        protected String doInBackground(String... strings) {
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(strings[0] );
                HttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    Log.e("doinbackground","Working");
                    return EntityUtils.toString(httpEntity);
            }catch (ClientProtocolException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }*//*

        @Override
        protected JSONObject doInBackground(String... strings) {

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(strings[0]);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "n");
                }
                is.close();
                json = sb.toString();

            } catch (Exception e) {
                Log.e("doInBackground", "Error converting result " + e.toString());
            }

            // try parse the string to a JSON object
            try {
                jObj = new JSONObject(json);
            } catch (JSONException e) {
                Log.e("doInBackground", "Error parsing data " + e.toString());
            }

            // return JSON String
            return jObj;

        }

        @Override
        protected void onPostExecute(JSONObject s) {
            dialog.dismiss();
            if(s != null){

                Log.e("DATAA","DATA FOUND BRO : " + s);
               parseData(s);
            }else {
                Log.e("DATAA","DATA NOT FOUND");
            }
        }

        private void parseData(JSONObject data){

            try {
                Log.e("parse",""+data);
                //JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                JSONArray jsonArray = data.getJSONArray("items");
                JSONObject singlrow;
                for(int i=0 ; i< jsonArray.length(); i++){
                    singlrow = jsonArray.getJSONObject(i);
                    youtubeDatas.add(new YoutubeData(singlrow.getString("id"),singlrow.getString("uploader"),singlrow.getString("title"),singlrow.getString("description"), singlrow.getJSONObject("thumbnail").getString("sqDefault")));
                    Log.e("image url",singlrow.getJSONObject("thumbnail").getString("sqDefault"));
                    Log.e("id",singlrow.getString("id"));
                }
               listView.setAdapter(new YoutubeAdapter(getApplicationContext(),youtubeDatas));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }*/

}
