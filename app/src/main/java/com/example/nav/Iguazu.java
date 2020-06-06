package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Iguazu extends AppCompatActivity {
    Button visitbutton;
    Button mapbutton;
    ImageView backiguazu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iguazu);
        visitbutton = findViewById(R.id.iguazuvisit);
        backiguazu = findViewById(R.id.iguazuback);
        mapbutton = findViewById(R.id.iguazumap);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent();
                viewintent.setAction(Intent.ACTION_VIEW);
                viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                viewintent.setData(Uri.parse("https://www.goway.com/trips/dest/central-and-south-america/cntry/argentina/reg/iguassu-falls/"));
                startActivity(viewintent);
            }
        });
        backiguazu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapintent = new Intent(Intent.ACTION_VIEW);
                mapintent.setData(Uri.parse("geo:0,0?q=-25.685469,-54.450001"));
                startActivity(mapintent);
            }
        });
    }
}
