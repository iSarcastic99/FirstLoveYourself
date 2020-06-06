package com.example.nav;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.firebase.client.Firebase;

public class HumorousVideos extends AppCompatActivity {
    ImageView Humimage1, Humimage2, Humimage3, Humimage4, Humimage5, backhum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humorous_videos);
        Humimage1 = findViewById(R.id.humimage1);
        Humimage2 = findViewById(R.id.humimage2);
        Humimage3 = findViewById(R.id.humimage3);
        Humimage4 = findViewById(R.id.humimage4);
        Humimage5 = findViewById(R.id.humimage5);
        backhum = findViewById(R.id.humback);
        backhum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Humimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HumorousVideos.this,Humorous_Video_1.class);
                startActivity(intent1);
            }
        });
        Humimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(HumorousVideos.this,Humorous_Video_2.class);
                startActivity(intent2);
            }
        });
        Humimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(HumorousVideos.this,Humorous_Video_3.class);
                startActivity(intent3);
            }
        });
        Humimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(HumorousVideos.this,Humorous_Video_4.class);
                startActivity(intent4);
            }
        });
        Humimage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(HumorousVideos.this,Humorous_Video_5.class);
                startActivity(intent5);
            }
        });
    }

}
