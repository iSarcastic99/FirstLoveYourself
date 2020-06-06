package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Valley_Of_Flowers extends AppCompatActivity {
    Button visitbutton;
    Button mapbutton;
    ImageView backvalley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valley__of__flowers);
        backvalley = findViewById(R.id.valleyback);
        visitbutton = findViewById(R.id.valleyvisit);
        mapbutton = findViewById(R.id.valleymap);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent();
                viewintent.setAction(Intent.ACTION_VIEW);
                viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                viewintent.setData(Uri.parse("https://www.holidayiq.com/holiday-packages/valley-of-flowers-details-pk28281.html"));
                startActivity(viewintent);
            }
        });
        backvalley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapintent = new Intent(Intent.ACTION_VIEW);
                mapintent.setData(Uri.parse("geo:0,0?q=30.7280,79.6053"));
                startActivity(mapintent);
            }
        });
    }
}
