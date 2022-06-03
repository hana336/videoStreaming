package com.example.videostreaming;

import androidx.appcompat.app.AppCompatActivity;

import android.media.browse.MediaBrowser;
import android.os.Bundle;

import com.example.videostreaming.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    String videoLink = "https://firebasestorage.googleapis.com/v0/b/firstproject-8340a.appspot.com/o/(1268)%204K%20Relaxing%20Nature%20Sounds%20-%20Short%20Video%20Clips%20of%20Nature%20-%20YouTube.mkv?alt=media&token=0520ce09-bca8-4338-9919-75155021be25";

    ActivityMainBinding binding;
    PlayerView pv;

    SimpleExoPlayer player;

    boolean playWhenReady = true;
    long currentPosition = 0;
    int currentWindow = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       pv = binding.playerView;
    }

    private void initPlayer(){
        player = new SimpleExoPlayer.Builder(this).build();
        pv.setPlayer(player);

        MediaItem item = MediaItem.fromUri(videoLink);
        player.setMediaItem(item);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, currentPosition);
        player.prepare();
    }


    private void releasePlayer(){
        if(player != null){
            playWhenReady = player.getPlayWhenReady();
            currentWindow = player.getCurrentwindowIndex();
            currentPosition = player.getCurrentPosition();
            player= null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player != null){
            initPlayer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }
}