package com.example.aisoftware;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;



public class MainActivity extends Activity {
    ProgressBar progressBar = null;
    VideoView videoView = null;
    EditText url;
    String videoUrl = "rtsp://freja.hiof.no:1935/rtplive/_definst_/hessdalen02.stream";
    String getVideoUrl1;
    Button button;
    @Override
    public void onCreate(Bundle iclic) {
        super.onCreate(iclic);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoView);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("Url",videoUrl);
                EditText editText;
                editText = findViewById(R.id.editTextTextPersonName2);
                String data = editText.getText().toString();
                if(!data.isEmpty()){
                    videoUrl = data;
                }

                Log.e("Url",data);
                Log.e("Url",data);
                Uri videoUri = Uri.parse(videoUrl);
                videoView.setVideoURI(videoUri);
                videoView.start();
                progressBar.setVisibility(View.VISIBLE);
                videoView.setOnPreparedListener(new android.media.MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(android.media.MediaPlayer mp) {
                        mp.start();
                        mp.setOnVideoSizeChangedListener(new android.media.MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(android.media.MediaPlayer mp, int width, int height) {
                                progressBar.setVisibility(View.GONE);
                                mp.start();
                            }
                        });
                    }

                });
            }
        });

    }
}
