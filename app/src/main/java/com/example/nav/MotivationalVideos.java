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

public class MotivationalVideos extends AppCompatActivity {
   ImageView Motimage1, Motimage2, Motimage3, Motimage4, Motimage5,backmot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivational_videos);
        Motimage1 = findViewById(R.id.motimage1);
        Motimage2 = findViewById(R.id.motimage2);
        Motimage3 = findViewById(R.id.motimage3);
        backmot = findViewById(R.id.motback);
        Motimage4 = findViewById(R.id.motimage4);
        Motimage5 = findViewById(R.id.motimage5);

        backmot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Motimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MotivationalVideos.this,Motivational_Video_1.class);
                startActivity(intent1);
            }
        });
        Motimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MotivationalVideos.this,Motivational_Video_2.class);
                startActivity(intent2);
            }
        });
        Motimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MotivationalVideos.this,Motivational_Video_3.class);
                startActivity(intent3);
            }
        });
        Motimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MotivationalVideos.this,Motivational_Video_4.class);
                startActivity(intent4);
            }
        });
        Motimage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MotivationalVideos.this,Motivational_Video_5.class);
                startActivity(intent5);
            }
        });
    }

}
