package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Cliffs_Of_Moher extends AppCompatActivity {
    Button visitbutton;
    Button mapbutton;
    ImageView backcliffs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliffs__of__moher);
        visitbutton = findViewById(R.id.cliffsvisit);
        backcliffs = findViewById(R.id.cliffsback);
        mapbutton = findViewById(R.id.cliffsmap);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent();
                viewintent.setAction(Intent.ACTION_VIEW);
                viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                viewintent.setData(Uri.parse("https://www.cliffsofmoher.ie"));
                startActivity(viewintent);
            }
        });

        backcliffs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapintent = new Intent(Intent.ACTION_VIEW);
                mapintent.setData(Uri.parse("geo:0,0?q=52.97188,-9.42651"));
                startActivity(mapintent);
            }
        });
    }
}
