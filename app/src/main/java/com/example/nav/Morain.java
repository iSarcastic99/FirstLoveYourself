package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Morain extends AppCompatActivity {
    Button visitbutton;
    Button mapbutton;
    ImageView backmorine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morain);
        visitbutton = findViewById(R.id.morainevisit);
        backmorine = findViewById(R.id.moraineback);
        mapbutton = findViewById(R.id.morainemap);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewintent = new Intent();
                viewintent.setAction(Intent.ACTION_VIEW);
                viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                viewintent.setData(Uri.parse("https://www.expedia.com/Moraine-Lake-Lake-Louise.d6260950.Vacation-Attraction"));
                startActivity(viewintent);
            }
        });
        backmorine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapintent = new Intent(Intent.ACTION_VIEW);
                mapintent.setData(Uri.parse("geo:0,0?q=51.321742,-116.186005"));
                startActivity(mapintent);
            }
        });
    }
}
