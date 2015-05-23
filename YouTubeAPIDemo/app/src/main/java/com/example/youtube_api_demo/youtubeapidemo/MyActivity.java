package com.example.youtube_api_demo.youtubeapidemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class MyActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    static private final String DEVELOPER_KEY = "AIzaSyAwxNQQCavSamS8CTNn-YCB48m8DHmf99E";
    static private final String VIDEO = "laMRLP5vl5s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        YouTubePlayerView youTubeView = (YouTubePlayerView)
                findViewById(R.id.youtube_view);
        youTubeView.initialize(DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(VIDEO);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Oh no! " + youTubeInitializationResult.toString(),Toast.LENGTH_LONG).show();
    }

}
