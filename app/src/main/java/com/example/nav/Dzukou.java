package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Dzukou extends AppCompatActivity {
    Button visitbutton;
    Button mapbutton;
    ImageView backdzukou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzukou);
        visitbutton = findViewById(R.id.dzukouvisit);
        mapbutton = findViewById(R.id.dzukoumap);
        backdzukou = findViewById(R.id.dzukouback);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent();
                viewintent.setAction(Intent.ACTION_VIEW);
                viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                viewintent.setData(Uri.parse("https://www.goibibo.com/destinations/kohima/places-to-visit-in-kohima/dzukou-valley-6474982504430496636/"));
                startActivity(viewintent);
            }
        });

        backdzukou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapintent = new Intent(Intent.ACTION_VIEW);
                mapintent.setData(Uri.parse("geo:0,0?q=25.5071,94.1335"));
                startActivity(mapintent);
            }
        });
    }
}
