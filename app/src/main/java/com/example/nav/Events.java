package com.example.nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class Events extends AppCompatActivity {
    DatabaseReference reff;
    String city = "";
    TextView TitleTV, EventTV1, EventTV2, EventTV3, EventTV4, EventTV5;
    ImageView EventImage1, EventImage2, EventImage3, EventImage4, EventImage5;
    String Username, title1, title2, title3, title4, title5, desc1, desc2, desc3, desc4, desc5;
    String cityNumber, S, imageURL1, imageURL2, imageURL3, imageURL4, imageURL5, URL1, URL2, URL3, URL4, URL5;
    String Date1, Date2, Date3, Date4, Date5;
    String Time1, Time2, Time3, Time4, Time5;
    ProgressDialog pd;
    ImageView backButton;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(S,i);
        Username = preferences.getString("Username","");
        setContentView(R.layout.activity_events);
        TitleTV = findViewById(R.id.title);
        EventTV1 = findViewById(R.id.eventtv1);
        EventTV2 = findViewById(R.id.eventtv2);
        EventTV3 = findViewById(R.id.eventtv3);
        EventTV4 = findViewById(R.id.eventtv4);
        EventTV5 = findViewById(R.id.eventtv5);
        EventImage1 = findViewById(R.id.eventimage1);
        EventImage2 = findViewById(R.id.eventimage2);
        EventImage3 = findViewById(R.id.eventimage3);
        EventImage4 = findViewById(R.id.eventimage4);
        EventImage5 = findViewById(R.id.eventimage5);
        backButton = findViewById(R.id.backbutton);
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

        reff = FirebaseDatabase.getInstance().getReference();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    try{
                    city = dataSnapshot.child("users").child(Username).child("City").getValue().toString();
                    if (!city.equals("")) {
                        try {
                            title1 = dataSnapshot.child("events").child(city).child("Event1").child("Title").getValue().toString();
                            desc1 = dataSnapshot.child("events").child(city).child("Event1").child("Details").getValue().toString();
                            imageURL1 = dataSnapshot.child("events").child(city).child("Event1").child("ImageURL").getValue().toString();
                            URL1 = dataSnapshot.child("events").child(city).child("Event1").child("URL").getValue().toString();
                            Time1 = dataSnapshot.child("events").child(city).child("Event1").child("Time").getValue().toString();
                            Date1 = dataSnapshot.child("events").child(city).child("Event1").child("Date").getValue().toString();
                            backButton.setVisibility(View.VISIBLE);
                            EventTV1.setVisibility(View.VISIBLE);
                            EventImage1.setVisibility(View.VISIBLE);
                            EventImage1.setImageBitmap(getImageBitmap(imageURL1));
                            EventTV1.setText(title1);
                        } catch(Exception e) {
                            pd.dismiss();
                            Toast.makeText(Events.this, "No nearby events right now" + cityNumber, Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        try {
                            title2 = dataSnapshot.child("events").child(city).child("Event2").child("Title").getValue().toString();
                            desc2 = dataSnapshot.child("events").child(city).child("Event2").child("Details").getValue().toString();
                            imageURL2 = dataSnapshot.child("events").child(city).child("Event2").child("ImageURL").getValue().toString();
                            URL2 = dataSnapshot.child("events").child(city).child("Event2").child("URL").getValue().toString();
                            Time2 = dataSnapshot.child("events").child(city).child("Event2").child("Time").getValue().toString();
                            Date2 = dataSnapshot.child("events").child(city).child("Event2").child("Date").getValue().toString();
                            EventTV2.setVisibility(View.VISIBLE);
                            EventImage2.setVisibility(View.VISIBLE);
                            EventImage2.setImageBitmap(getImageBitmap(imageURL2));
                            EventTV2.setText(title2);

                        } catch (Exception e) {

                        }
                        try {
                            title3 = dataSnapshot.child("events").child(city).child("Event3").child("Title").getValue().toString();
                            desc3 = dataSnapshot.child("events").child(city).child("Event3").child("Details").getValue().toString();
                            imageURL3 = dataSnapshot.child("events").child(city).child("Event3").child("ImageURL").getValue().toString();
                            URL3 = dataSnapshot.child("events").child(city).child("Event3").child("URL").getValue().toString();
                            Time3 = dataSnapshot.child("events").child(city).child("Event3").child("Time").getValue().toString();
                            Date3 = dataSnapshot.child("events").child(city).child("Event3").child("Date").getValue().toString();
                            EventTV3.setVisibility(View.VISIBLE);
                            EventImage3.setVisibility(View.VISIBLE);
                            EventImage3.setImageBitmap(getImageBitmap(imageURL3));
                            EventTV3.setText(title3);

                        } catch (Exception e) {

                        }
                        try {
                            title4 = dataSnapshot.child("events").child(city).child("Event4").child("Title").getValue().toString();
                            desc4 = dataSnapshot.child("events").child(city).child("Event4").child("Details").getValue().toString();
                            imageURL4 = dataSnapshot.child("events").child(city).child("Event4").child("ImageURL").getValue().toString();
                            URL4 = dataSnapshot.child("events").child(city).child("Event4").child("URL").getValue().toString();
                            Time4 = dataSnapshot.child("events").child(city).child("Event4").child("Time").getValue().toString();
                            Date4 = dataSnapshot.child("events").child(city).child("Event4").child("Date").getValue().toString();
                            EventTV4.setVisibility(View.VISIBLE);
                            EventImage4.setVisibility(View.VISIBLE);
                            EventImage4.setImageBitmap(getImageBitmap(imageURL4));
                            EventTV4.setText(title4);

                        } catch (Exception e) {

                        }
                        try {
                            title5 = dataSnapshot.child("events").child(city).child("Event5").child("Title").getValue().toString();
                            desc5 = dataSnapshot.child("events").child(city).child("Event5").child("Details").getValue().toString();
                            imageURL5 = dataSnapshot.child("events").child(city).child("Event5").child("ImageURL").getValue().toString();
                            URL5 = dataSnapshot.child("events").child(city).child("Event5").child("URL").getValue().toString();
                            Time5 = dataSnapshot.child("events").child(city).child("Event5").child("Time").getValue().toString();
                            Date5 = dataSnapshot.child("events").child(city).child("Event5").child("Date").getValue().toString();

                            EventTV5.setVisibility(View.VISIBLE);
                            EventImage5.setVisibility(View.VISIBLE);
                            EventImage5.setImageBitmap(getImageBitmap(imageURL5));
                            EventTV5.setText(title5);

                        } catch (Exception e) {

                        }
                         TitleTV.setText(city);
                         pd.dismiss();
                    } else{
                        Intent intent = new Intent(Events.this, EventCities.class);
                        startActivity(intent);
                        pd.dismiss();
                        finish();
                    }

                     } catch(Exception e){

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();
                Toast.makeText(Events.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        EventImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Events.this, EventDetails.class);
                intent.putExtra("City", city);
                intent.putExtra("eventNumber", "1");
                startActivity(intent);
            }
        });
        EventImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Events.this, EventDetails.class);
                intent.putExtra("City", city);
                intent.putExtra("eventNumber", "2");
                startActivity(intent);
            }
        });
        EventImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Events.this, EventDetails.class);
                intent.putExtra("City", city);
                intent.putExtra("eventNumber", "3");
                startActivity(intent);
            }
        });
        EventImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Events.this, EventDetails.class);
                intent.putExtra("City", city);
                intent.putExtra("eventNumber", "4");
                startActivity(intent);
            }
        });
        EventImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Events.this, EventDetails.class);
                intent.putExtra("City", city);
                intent.putExtra("eventNumber", "5");
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
}