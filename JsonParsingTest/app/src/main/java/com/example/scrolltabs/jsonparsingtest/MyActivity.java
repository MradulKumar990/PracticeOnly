package com.example.scrolltabs.jsonparsingtest;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class MyActivity extends Activity {

    public ListView list;
    public ArrayList<Person> p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        p = new ArrayList<Person>();
        list= (ListView) findViewById(R.id.listView);
        new MyAsyncTask().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String>{

        ProgressDialog dialog;

        public MyAsyncTask() {
            dialog = new ProgressDialog(MyActivity.this);
            dialog.setTitle("Getting details...");
        }

        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(strings[0]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                if(httpResponse.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = httpResponse.getEntity();
                    return  EntityUtils.toString(entity);
                    }
             }catch (ClientProtocolException e){
                     e.printStackTrace();
                     }
              catch (IOException e){
                     e.printStackTrace();
                     }
            return null;
        }

        public void onPostExecute(String data ){
            dialog.dismiss();
            if(data != null)
                parseData(data);
            else
                Log.e("TAU", "No dta found");
        }
    }

    private void parseData(String data) {
        try {
            Log.d("TAU",data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("actors");
            JSONObject singleObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                singleObject = jsonArray.getJSONObject(i);
                p.add(new Person(singleObject.getString("name"), singleObject.getString("dob"), singleObject.getString("height"), singleObject.getString("country"), singleObject.getString("description"), singleObject.getString("spouse"), singleObject.getString("children"), singleObject.getString("image")));
                Log.e("image",singleObject.getString("image"));
            }
            list.setAdapter(new Myadapter(getApplicationContext(), p));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


}