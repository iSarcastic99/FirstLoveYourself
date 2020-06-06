package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Pithoragarh extends AppCompatActivity {
    Button visitbutton;
    Button mapbutton;
    ImageView backpithoragarh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pithoragarh);
        visitbutton = findViewById(R.id.pithoragarhvisit);
        backpithoragarh = findViewById(R.id.pithoragarhback);
        mapbutton = findViewById(R.id.pithoragarhmap);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent();
                viewintent.setAction(Intent.ACTION_VIEW);
                viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                viewintent.setData(Uri.parse("https://www.yatra.com/india-tour-packages/holidays-in-pithoragarh"));
                startActivity(viewintent);
            }
        });
        backpithoragarh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapintent = new Intent(Intent.ACTION_VIEW);
                mapintent.setData(Uri.parse("geo:0,0?q=29.5829,80.2182"));
                startActivity(mapintent);
            }
        });
    }
}
