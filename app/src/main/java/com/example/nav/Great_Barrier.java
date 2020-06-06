package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Great_Barrier extends AppCompatActivity {
    Button visitbutton;
    Button mapbutton;
    ImageView backgreat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_great__barrier);
        visitbutton = findViewById(R.id.greatvisit);
        mapbutton = findViewById(R.id.greatmap);
        backgreat = findViewById(R.id.greatback);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent();
                viewintent.setAction(Intent.ACTION_VIEW);
                viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                viewintent.setData(Uri.parse("https://www.qantasvacations.com/australia/whitsundays-tours"));
                startActivity(viewintent);
            }
        });
        backgreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapintent = new Intent(Intent.ACTION_VIEW);
                mapintent.setData(Uri.parse("geo:0,0?q=18.2871,147.6992"));
                startActivity(mapintent);
            }
        });
    }
}
