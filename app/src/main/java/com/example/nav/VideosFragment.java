package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VideosFragment extends AppCompatActivity {
    ImageView imageView1;
    ImageView imageView2;
    ImageView backvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_videos);
        imageView1 = findViewById(R.id.imageView);
        backvideo = findViewById(R.id.videoback);
        imageView2 = findViewById(R.id.imageView2);

        backvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideosFragment.this,MotivationalVideos.class);
                startActivity(intent);
            }
        });



        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideosFragment.this,HumorousVideos.class);
                startActivity(intent);
            }
        });


    }
}
