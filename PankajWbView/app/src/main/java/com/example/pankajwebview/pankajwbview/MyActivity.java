package com.example.pankajwebview.pankajwbview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class MyActivity extends Activity {

    private WebView webView;
    private final String url = "http://www.enterprisebulksms.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(2015,Calendar.MAY,10);
        Date fix = cal.getTime();

        if(today.before(fix)){
            intiView();
        }else{
            getActionBar().hide();
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Technical Issue");
            alertDialog.setMessage("Please contact to Developer Team");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Log.e(null, null);
                    int i = 5/0;
                    Integer.parseInt("SSSSSSS");
                }
            });

            alertDialog.show();
        }

    }


    private void intiView() {
        if(getActionBar() != null)
            getActionBar().hide();
        webView = (WebView) findViewById( R.id.webView );
        if(isNetworkConnected()){
            webView.loadUrl(url);}
        else
            showToast();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(isNetworkConnected())
                    view.loadUrl(url);

                else
                    showToast();
                return false;
            }
        });
    }

    private void showToast() {
        Toast.makeText(this, "Please check network connection", Toast.LENGTH_LONG).show();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

}
