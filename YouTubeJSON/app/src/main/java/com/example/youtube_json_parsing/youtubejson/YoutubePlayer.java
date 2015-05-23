package com.example.youtube_json_parsing.youtubejson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

/**
 * Created by Mradul on 4/3/2015.
 */
public class YoutubePlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    static private final String DEVELOPER_KEY = "AIzaSyAwxNQQCavSamS8CTNn-YCB48m8DHmf99E";
//    Intent i = getIntent();
//    YoutubeData data = (YoutubeData) i.getSerializableExtra("data");
    static public String id;
    static private String title;
    static private String desc;
    ListView listView;
    ArrayList<YoutubeData> youtubeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_player);
        id = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        Log.e("activityB",id);
        Log.e("activityB",title);
        Log.e("activityB",desc);
        try {
        TextView youtube_title = (TextView) findViewById(R.id.video_player_title);
        youtube_title.setText(title);
        TextView youtube_desc = (TextView) findViewById(R.id.video_player_descrition);
        youtube_desc.setText(desc);

        YouTubePlayerView player = (YouTubePlayerView) findViewById(R.id.youtube_plaer);

                player.initialize(DEVELOPER_KEY, this);

            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new YoutubeAdapter(this,youtubeData));
            }catch (NullPointerException e){
                e.printStackTrace();
            }


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(id);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getApplicationContext(), "Oh no! " + youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
    }
}
