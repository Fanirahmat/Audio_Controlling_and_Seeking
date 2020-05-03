package com.mfanir.audiocontrollingandseeking;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    AudioManager audio;
    public void play(View view)
    {
       player.start();
    }

    public void pause(View view)
    {
        player.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audio = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

        player = MediaPlayer.create(this, R.raw.pradaban);

        SeekBar volumeControl = (SeekBar) findViewById(R.id.volume);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currentVolume);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, progress , 0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar progressSeekBar = (SeekBar) findViewById(R.id.progress);
        progressSeekBar.setMax(player.getDuration());
        progressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                player.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                progressSeekBar.setProgress(player.getCurrentPosition());
            }
        }, 0, 1000);

    }


}
