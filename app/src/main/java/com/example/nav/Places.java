package com.example.nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Places extends AppCompatActivity {
    Button visitbutton, mapbutton;
    ImageView backplaces, placeimage;
    DatabaseReference reff;
    TextView TitleTV, DescriptionTV;
    Intent intent;
    String Title, Description, MapURL, VisitURL, PlaceNumber, ImageURL;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        TitleTV = findViewById(R.id.title);
        DescriptionTV = findViewById(R.id.description);
        visitbutton = findViewById(R.id.visit);
        backplaces = findViewById(R.id.placesback);
        mapbutton = findViewById(R.id.map);
        placeimage = findViewById(R.id.placeimage);
        intent = getIntent();
        PlaceNumber = intent.getStringExtra("placeNumber");
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        reff = FirebaseDatabase.getInstance().getReference().child("worldtour").child("Place"+PlaceNumber);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Title = dataSnapshot.child("Title").getValue().toString();
                Description = dataSnapshot.child("Description").getValue().toString();
                VisitURL = dataSnapshot.child("VisitURL").getValue().toString();
                MapURL = dataSnapshot.child("MapURL").getValue().toString();
                ImageURL = dataSnapshot.child("ImageURL").getValue().toString();
                TitleTV.setText(Title);
                DescriptionTV.setText(Description);
                placeimage.setImageBitmap(getImageBitmap(ImageURL));
                visitbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent viewintent = new Intent();
                        viewintent.setAction(Intent.ACTION_VIEW);
                        viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                        viewintent.setData(Uri.parse(VisitURL));
                        startActivity(viewintent);
                    }
                });
                mapbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mapintent = new Intent(Intent.ACTION_VIEW);
                        mapintent.setData(Uri.parse(MapURL));
                        startActivity(mapintent);
                    }
                });
                mapbutton.setVisibility(View.VISIBLE);
                visitbutton.setVisibility(View.VISIBLE);
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Places.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });



        backplaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
        return bm;
    }
}
