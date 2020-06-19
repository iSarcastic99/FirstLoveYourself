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

public class EventDetails extends AppCompatActivity {
    Button detailButton;
    ImageView eventimage;
    DatabaseReference reff;
    TextView TitleTV, DetailsTV;
    Intent intent;
    String Title, Details, City, VisitURL, EventNumber, ImageURL;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        TitleTV = findViewById(R.id.title);
        DetailsTV = findViewById(R.id.detailstv);
        detailButton = findViewById(R.id.detailsButton);
        eventimage = findViewById(R.id.eventimage);
        intent = getIntent();
        EventNumber = intent.getStringExtra("eventNumber");
        City = intent.getStringExtra("City");
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
        reff = FirebaseDatabase.getInstance().getReference().child("events").child(City).child("Event"+EventNumber);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Details = dataSnapshot.child("Details").getValue().toString();
                VisitURL = dataSnapshot.child("URL").getValue().toString();
                ImageURL = dataSnapshot.child("ImageURL").getValue().toString();
                TitleTV.setText(Title);
                DetailsTV.setText(Details);
                eventimage.setImageBitmap(getImageBitmap(ImageURL));
                detailButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent viewintent = new Intent();
                        viewintent.setAction(Intent.ACTION_VIEW);
                        viewintent.addCategory(Intent.CATEGORY_BROWSABLE);
                        viewintent.setData(Uri.parse(VisitURL));
                        startActivity(viewintent);
                    }
                });
                eventimage.setVisibility(View.VISIBLE);
                detailButton.setVisibility(View.VISIBLE);
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EventDetails.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                pd.dismiss();
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
