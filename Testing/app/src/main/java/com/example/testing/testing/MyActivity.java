package com.example.testing.testing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;


public class MyActivity extends Activity {
    ListView listView;
   static ArrayList<YoutubeData> youtubeDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        listView = (ListView) findViewById(R.id.listView);
        youtubeDatas = new ArrayList<YoutubeData>();
        new MyasynkTask().execute("https://gdata.youtube.com/feeds/api/users/charlieissocoollike/uploads?alt=jsonc&v=2");
    }


    class MyasynkTask extends AsyncTask<String, Void, JSONObject> {

        InputStream is = null;
        JSONObject jObj = null;
        String json = "";
        ProgressDialog dialog;

        MyasynkTask(){
            dialog = new ProgressDialog(MyActivity.this);
            dialog.setTitle("Loading Loading ");
        }

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

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
            } catch (IOException e) {
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
            if (s != null) {
                Log.e("onPostExecute", "DATA FOUND" + s);
                JsonFromString(s);

            } else {
                Log.e("onPostExecute", "DATA NOT FOUND");
            }
        }

        private void JsonFromString(JSONObject jsonObject){
            try {
                JSONObject data = jsonObject.getJSONObject("data");
                JSONArray items = data.getJSONArray("items");
                for(int i=0; i<items.length(); i++){
                    JSONObject singleItem = items.getJSONObject(i);
                    String id = singleItem.getString("id");
                    String loader = singleItem.getString("uploader");
                    String title = singleItem.getString("title");
                    String description = singleItem.getString("description");
                    JSONObject thumbnail = singleItem.getJSONObject("thumbnail");
                    String thumbNailUrl = thumbnail.getString("sqDefault");
                    youtubeDatas.add(new YoutubeData(id,loader,title,description,thumbNailUrl));

                    Log.e("JsonFromString", id);
                    Log.e("JsonFromString", title);
                    Log.e("JsonFromString", description);
                    Log.e("JsonFromString", thumbNailUrl);
                }
                listView.setAdapter(new YoutubeAdapter(MyActivity.this,youtubeDatas));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }


}

