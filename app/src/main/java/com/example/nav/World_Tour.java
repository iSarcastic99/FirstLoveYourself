package com.example.nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
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

public class World_Tour extends AppCompatActivity {
    ImageView PlaceImage1, PlaceImage2, PlaceImage3, PlaceImage4, PlaceImage5, PlaceImage6, PlaceImage7, PlaceImage8, PlaceImage9, PlaceImage10;
    Button backworld;
    DatabaseReference reff;
    TextView PlaceTV1, PlaceTV2, PlaceTV3, PlaceTV4, PlaceTV5, PlaceTV6, PlaceTV7, PlaceTV8, PlaceTV9, PlaceTV10, InsideTV;
    ProgressDialog pd, pd2;
    String Title1, Title2, Title3, Title4, Title5, Title6, Title7, Title8, Title9, Title10, ImageURL1, ImageURL2, ImageURL3, ImageURL4, ImageURL5, ImageURL6, ImageURL7, ImageURL8, ImageURL9, ImageURL10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world__tour);
        backworld = findViewById(R.id.worldback);
        PlaceImage1 = findViewById(R.id.placeimage1);
        PlaceImage2 = findViewById(R.id.placeimage2);
        PlaceImage3 = findViewById(R.id.placeimage3);
        PlaceImage4 = findViewById(R.id.placeimage4);
        PlaceImage5 = findViewById(R.id.placeimage5);
        PlaceImage6 = findViewById(R.id.placeimage6);
        PlaceImage7 = findViewById(R.id.placeimage7);
        PlaceImage8 = findViewById(R.id.placeimage8);
        PlaceImage9 = findViewById(R.id.placeimage9);
        PlaceImage10 = findViewById(R.id.placeimage10);
        PlaceTV1 = findViewById(R.id.placetv1);
        PlaceTV2 = findViewById(R.id.placetv2);
        PlaceTV3 = findViewById(R.id.placetv3);
        PlaceTV4 = findViewById(R.id.placetv4);
        PlaceTV5 = findViewById(R.id.placetv5);
        PlaceTV6 = findViewById(R.id.placetv6);
        PlaceTV7 = findViewById(R.id.placetv7);
        PlaceTV8 = findViewById(R.id.placetv8);
        PlaceTV9 = findViewById(R.id.placetv9);
        PlaceTV10 = findViewById(R.id.placetv10);
        InsideTV = findViewById(R.id.wtitle);

        backworld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd2 = new ProgressDialog(this);
        pd2.setMessage("Loading...");
        pd.show();

        int SDK_INT = Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        reff = FirebaseDatabase.getInstance().getReference().child("worldtour");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Title1 = dataSnapshot.child("Place1").child("Title").getValue().toString();
                Title2 = dataSnapshot.child("Place2").child("Title").getValue().toString();
                Title3 = dataSnapshot.child("Place3").child("Title").getValue().toString();
                Title4 = dataSnapshot.child("Place4").child("Title").getValue().toString();
                Title5 = dataSnapshot.child("Place5").child("Title").getValue().toString();
                Title6 = dataSnapshot.child("Place6").child("Title").getValue().toString();
                Title7 = dataSnapshot.child("Place7").child("Title").getValue().toString();
                Title8 = dataSnapshot.child("Place8").child("Title").getValue().toString();
                Title9 = dataSnapshot.child("Place9").child("Title").getValue().toString();
                Title10 = dataSnapshot.child("Place10").child("Title").getValue().toString();
                ImageURL1 = dataSnapshot.child("Place1").child("ImageURL").getValue().toString();
                ImageURL2 = dataSnapshot.child("Place2").child("ImageURL").getValue().toString();
                ImageURL3 = dataSnapshot.child("Place3").child("ImageURL").getValue().toString();
                ImageURL4 = dataSnapshot.child("Place4").child("ImageURL").getValue().toString();
                ImageURL5 = dataSnapshot.child("Place5").child("ImageURL").getValue().toString();
                ImageURL6 = dataSnapshot.child("Place6").child("ImageURL").getValue().toString();
                ImageURL7 = dataSnapshot.child("Place7").child("ImageURL").getValue().toString();
                ImageURL8 = dataSnapshot.child("Place8").child("ImageURL").getValue().toString();
                ImageURL9 = dataSnapshot.child("Place9").child("ImageURL").getValue().toString();
                ImageURL10 = dataSnapshot.child("Place10").child("ImageURL").getValue().toString();
                PlaceTV1.setText(Title1);
                PlaceTV2.setText(Title2);
                PlaceTV3.setText(Title3);
                PlaceTV4.setText(Title4);
                PlaceTV5.setText(Title5);
                PlaceTV6.setText(Title6);
                PlaceTV7.setText(Title7);
                PlaceTV8.setText(Title8);
                PlaceTV9.setText(Title9);
                PlaceTV10.setText(Title10);
                PlaceImage1.setImageBitmap(getImageBitmap(ImageURL1));
                PlaceImage2.setImageBitmap(getImageBitmap(ImageURL2));
                PlaceImage3.setImageBitmap(getImageBitmap(ImageURL3));
                PlaceImage4.setImageBitmap(getImageBitmap(ImageURL4));
                PlaceImage5.setImageBitmap(getImageBitmap(ImageURL5));
                PlaceImage6.setImageBitmap(getImageBitmap(ImageURL6));
                PlaceImage7.setImageBitmap(getImageBitmap(ImageURL7));
                PlaceImage8.setImageBitmap(getImageBitmap(ImageURL8));
                PlaceImage9.setImageBitmap(getImageBitmap(ImageURL9));
                PlaceImage10.setImageBitmap(getImageBitmap(ImageURL10));
                pd.dismiss();
                InsideTV.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(World_Tour.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

        PlaceImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(World_Tour.this, Places.class);
                intent.putExtra("placeNumber", "1");
                startActivity(intent);
            }
        });
        PlaceImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(World_Tour.this, Places.class);
                intent.putExtra("placeNumber", "2");
                startActivity(intent);
            }
        });
        PlaceImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(World_Tour.this, Places.class);
                intent.putExtra("placeNumber", "3");
                startActivity(intent);
            }
        });
        PlaceImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(World_Tour.this, Places.class);
                intent.putExtra("placeNumber", "4");
                startActivity(intent);
            }
        });
        PlaceImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(World_Tour.this, Places.class);
                intent.putExtra("placeNumber", "5");
                startActivity(intent);
            }
        });
        PlaceImage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(World_Tour.this, Places.class);
                intent.putExtra("placeNumber", "6");
                startActivity(intent);
            }
        });
        PlaceImage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(World_Tour.this, Places.class);
                intent.putExtra("placeNumber", "7");
                startActivity(intent);
            }
        });
        PlaceImage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(World_Tour.this, Places.class);
                intent.putExtra("placeNumber", "8");
                startActivity(intent);
            }
        });
        PlaceImage9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(World_Tour.this, Places.class);
                intent.putExtra("placeNumber", "9");
                startActivity(intent);
            }
        });
        PlaceImage10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(World_Tour.this, Places.class);
                intent.putExtra("placeNumber", "10");
                startActivity(intent);
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

    @Override
    protected void onResume() {
        super.onResume();
        pd2.dismiss();
    }
}
