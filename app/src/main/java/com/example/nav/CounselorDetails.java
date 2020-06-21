package com.example.nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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

public class CounselorDetails extends AppCompatActivity {
    Intent intent;
    String CounselorNumber, Title, Description, Phone, Email, ImageURL, Username, city, S;
    DatabaseReference reff, reff2;
    ProgressDialog pd;
    ImageView backCounselor, imageCounselor;
    TextView TitleTV, DescriptionTV;
    Button Call, Mail;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(S,i);
        Username = preferences.getString("Username","");
        setContentView(R.layout.activity_counselor_details);
        backCounselor = findViewById(R.id.counselorback);
        imageCounselor = findViewById(R.id.counselorimage);
        TitleTV = findViewById(R.id.title);
        DescriptionTV = findViewById(R.id.description);
        Call = findViewById(R.id.call);
        Mail = findViewById(R.id.mail);

        intent = getIntent();
        CounselorNumber = intent.getStringExtra("counselorNumber");
        backCounselor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        reff2 = FirebaseDatabase.getInstance().getReference().child("users");
        reff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    city = dataSnapshot.child(Username).child("City").getValue().toString();
                    reff = FirebaseDatabase.getInstance().getReference().child("events").child(city).child("Counselor" + CounselorNumber);
                    reff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Title = dataSnapshot.child("Name").getValue().toString();
                            Description = dataSnapshot.child("Description").getValue().toString();
                            Phone = dataSnapshot.child("Phone").getValue().toString();
                            Email = dataSnapshot.child("Email").getValue().toString();
                            ImageURL = dataSnapshot.child("ImageURL").getValue().toString();
                            imageCounselor.setImageBitmap(getImageBitmap(ImageURL));
                            TitleTV.setText(Title);
                            DescriptionTV.setText(Description);
                            Call.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+Phone));
                                    if (ActivityCompat.checkSelfPermission(CounselorDetails.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        ActivityCompat.requestPermissions(CounselorDetails.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                                        return;
                                    } else
                                        startActivity(intent);
                                }
                            });
                            Mail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String recipient = Email;
                                    Intent intent4 = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                                    intent4.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
                                    startActivity(intent4);
                                }
                            });
                            Call.setVisibility(View.VISIBLE);
                            Mail.setVisibility(View.VISIBLE);
                            pd.dismiss();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(CounselorDetails.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                        }
                    });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CounselorDetails.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
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