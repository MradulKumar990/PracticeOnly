package com.example.youtube_fragment.youtubefegementdemo;
import com.google.android.youtube.player.YouTubePlayer;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;


public class MyActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String key = "AIzaSyAwxNQQCavSamS8CTNn-YCB48m8DHmf99E";
    private static final String vedio_id = "uMT-neszmVU";

    private YouTubePlayerFragment youTubePlayerFragment;
    private  YouTubePlayer youTubePlayer;
    private TextView textvideolog;
    private Button fullscreenbutton;
    private MyPlayerStateChangeListener myPlayerStateChangeListener;
    private MyPlaybackEventListener myPlaybackEventListener;
    private static final int RQS_ErrorDialog = -1;
    String log = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubefragment);
        youTubePlayerFragment.initialize(key,this);

        textvideolog = (TextView) findViewById(R.id.videolog);
        fullscreenbutton = (Button) findViewById(R.id.btnviewfullscreen);
        fullscreenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlayer.setFullscreen(true);
            }
        });

        myPlayerStateChangeListener = new MyPlayerStateChangeListener();
        myPlaybackEventListener = new MyPlaybackEventListener();

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {

        youTubePlayer = player;
        Toast.makeText(this,"onInitializationSuccess()",Toast.LENGTH_SHORT).show();
        youTubePlayer.setPlayerStateChangeListener(myPlayerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(myPlaybackEventListener);

        if(!b){
            player.loadVideo(vedio_id);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        if(result.isUserRecoverableError()){
         result.getErrorDialog(this,RQS_ErrorDialog).show();
        }
        else {
            Toast.makeText(this,result.toString(),Toast.LENGTH_SHORT).show();
        }
    }


    public class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        public void updateLog(String promt){
            log += "MyPlayerStateChangeListener"+"\n"+promt+"\n\n========";
            textvideolog.setText(log);

        }

        @Override
        public void onLoading() {
            updateLog("onLoading()");
        }

        @Override
        public void onLoaded(String s) {
            updateLog("onLoaded()"+s);
        }

        @Override
        public void onAdStarted() {
            updateLog("onAdStarted()");
        }

        @Override
        public void onVideoStarted() {
            updateLog("onVideoStarted()");
        }

        @Override
        public void onVideoEnded() {
            updateLog("onVideoEnded()");
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            updateLog("onError()"+errorReason.toString());
        }
    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener{

        public void updateLog(String promt){
            log += "MyPlaybackEventListener"+"\n"+promt+"\n\n========";
            textvideolog.setText(log);

        }
        @Override
        public void onPlaying() {
            updateLog("onPlaying()");
        }

        @Override
        public void onPaused() {
            updateLog("onPaused()");
        }

        @Override
        public void onStopped() {
            updateLog("onStopped()");
        }

        @Override
        public void onBuffering(boolean b) {
            updateLog("onBuffering()"+String.valueOf(b));
        }

        @Override
        public void onSeekTo(int i) {
            updateLog("onSeekTo()"+String.valueOf(i));
        }
    }
}
