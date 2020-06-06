package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Palawan extends AppCompatActivity {
    Button visitbutton;
    Button mapbutton;
    ImageView backpalawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palawan);
        backpalawan = findViewById(R.id.palawanback);
        visitbutton = findViewById(R.id.palawanvisit);
        mapbutton = findViewById(R.id.palawanmap);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent();
                viewintent.setAction(Intent.ACTION_VIEW);
                viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                viewintent.setData(Uri.parse("https://www.expedia.co.in/Palawan.d601955.Holidays-City-Breaks"));
                startActivity(viewintent);
            }
        });
        backpalawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapintent = new Intent(Intent.ACTION_VIEW);
                mapintent.setData(Uri.parse("geo:0,0?q=9.4462,118.3929"));
                startActivity(mapintent);
            }
        });
    }
}
