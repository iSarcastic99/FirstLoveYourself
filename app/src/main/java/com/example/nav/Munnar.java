package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Munnar extends AppCompatActivity {
    Button visitbutton;
    Button mapbutton;
    ImageView backmunnar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_munnar);
        visitbutton = findViewById(R.id.munnarvisit);
        mapbutton = findViewById(R.id.munnarmap);
        backmunnar = findViewById(R.id.munnarback);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent();
                viewintent.setAction(Intent.ACTION_VIEW);
                viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                viewintent.setData(Uri.parse("https://holidayz.makemytrip.com/holidays/india/package?id=25083&listingClassId=8&pkgType=FIT&fromCity=New%20Delhi&depDateMilliSec=1579458600000&defaultPageForOnlineBookableFIT=true"));
                startActivity(viewintent);
            }
        });
        backmunnar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

                mapbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mapintent = new Intent(Intent.ACTION_VIEW);
                        mapintent.setData(Uri.parse("geo:0,0?q=10.0889,77.0595"));
                        startActivity(mapintent);
                    }
                });
    }
}
