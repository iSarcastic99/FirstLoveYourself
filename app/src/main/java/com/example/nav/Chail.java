package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Chail extends AppCompatActivity {
    Button visitbutton;
    Button mapbutton;
    ImageView backchail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chail);
        visitbutton = findViewById(R.id.chailvisit);
        backchail = findViewById(R.id.chailback);
        mapbutton = findViewById(R.id.chailmap);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent();
                viewintent.setAction(Intent.ACTION_VIEW);
                viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                viewintent.setData(Uri.parse("https://holidayz.makemytrip.com/holidays/india/package?id=24376&listingClassId=8&pkgType=FIT&fromCity=NODEPT&depDateMilliSec=1576434600000&defaultPageForOnlineBookableFIT=true"));
                startActivity(viewintent);
            }
        });

        backchail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapintent = new Intent(Intent.ACTION_VIEW);
                mapintent.setData(Uri.parse("geo:0,0?q=30.9676,77.1916"));
                startActivity(mapintent);
            }
        });
    }
}
