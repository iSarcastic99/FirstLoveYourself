package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class World_Tour extends AppCompatActivity {
    ImageView chailimg,palawanimg,backworld,iguazuimg,moraineimg,greatimg,cliffsimg,dzukouimg,valleyimg,munnarimg,pithoragarhimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world__tour);
        backworld = findViewById(R.id.worldback);
        chailimg = findViewById(R.id.chailimage);
        palawanimg = findViewById(R.id.palawanimage);
        iguazuimg = findViewById(R.id.iguazuimage);
        moraineimg = findViewById(R.id.moraineimage);
        greatimg= findViewById(R.id.greatimage);
        cliffsimg= findViewById(R.id.cliffsimage);
        dzukouimg = findViewById(R.id.dzukouimage);
        valleyimg = findViewById(R.id.valleyimage);
        munnarimg = findViewById(R.id.munnarimage);
        pithoragarhimg = findViewById(R.id.piimage);

        backworld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        chailimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(World_Tour.this,Chail.class);
                startActivity(intent1);
            }
        });
        munnarimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(World_Tour.this,Munnar.class);
                startActivity(intent2);
            }
        });
        dzukouimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(World_Tour.this,Dzukou.class);
                startActivity(intent3);
            }
        });
        valleyimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(World_Tour.this,Valley_Of_Flowers.class);
                startActivity(intent4);
            }
        });
        pithoragarhimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(World_Tour.this,Pithoragarh.class);
                startActivity(intent5);
            }
        });
        palawanimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(World_Tour.this,Palawan.class);
                startActivity(intent6);
            }
        });
        iguazuimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(World_Tour.this,Iguazu.class);
                startActivity(intent7);
            }
        });
        greatimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(World_Tour.this,Great_Barrier.class);
                startActivity(intent8);
            }
        });
        moraineimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9 = new Intent(World_Tour.this,Morain.class);
                startActivity(intent9);
            }
        });
        cliffsimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent10 = new Intent(World_Tour.this,Cliffs_Of_Moher.class);
                startActivity(intent10);
            }
        });
    }
}
