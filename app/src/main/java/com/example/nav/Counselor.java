package com.example.nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
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

public class Counselor extends AppCompatActivity {
    ImageView backcounselor, CounselorImage1, CounselorImage2, CounselorImage3, CounselorImage4, CounselorImage5;
    TextView CounselorTV, CounselorName1, CounselorName2, CounselorName3, CounselorName4, CounselorName5;
    TextView CounselorDesignation1, CounselorDesignation2, CounselorDesignation3, CounselorDesignation4, CounselorDesignation5;
    TextView CounselorLoc1, CounselorLoc2, CounselorLoc3, CounselorLoc4, CounselorLoc5;
    String Name1, Name2, Name3, Name4, Name5, Des1, Des2, Des3, Des4, Des5, Loc1, Loc2, Loc3, Loc4, Loc5, Img1, Img2, Img3, Img4, Img5;
    DatabaseReference reff;
    ProgressDialog pd, pd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counselor);
        CounselorTV = findViewById(R.id.counselor);
        CounselorImage1 = findViewById(R.id.cnslrimage1);
        CounselorImage2 = findViewById(R.id.cnslrimage2);
        CounselorImage3 = findViewById(R.id.cnslrimage3);
        CounselorImage4 = findViewById(R.id.cnslrimage4);
        CounselorImage5 = findViewById(R.id.cnslrimage5);
        CounselorName1 = findViewById(R.id.cnslrname1);
        CounselorName2 = findViewById(R.id.cnslrname2);
        CounselorName3 = findViewById(R.id.cnslrname3);
        CounselorName4 = findViewById(R.id.cnslrname4);
        CounselorName5 = findViewById(R.id.cnslrname5);
        CounselorDesignation1 = findViewById(R.id.cnslrdesignation1);
        CounselorDesignation2 = findViewById(R.id.cnslrdesignation2);
        CounselorDesignation3 = findViewById(R.id.cnslrdesignation3);
        CounselorDesignation4 = findViewById(R.id.cnslrdesignation4);
        CounselorDesignation5 = findViewById(R.id.cnslrdesignation5);
        CounselorLoc1 = findViewById(R.id.cnslrloc1);
        CounselorLoc2 = findViewById(R.id.cnslrloc2);
        CounselorLoc3 = findViewById(R.id.cnslrloc3);
        CounselorLoc4 = findViewById(R.id.cnslrloc4);
        CounselorLoc5 = findViewById(R.id.cnslrloc5);
        backcounselor = findViewById(R.id.contactback);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd2 = new ProgressDialog(this);
        pd2.setMessage("Loading...");
        pd.show();
        backcounselor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        CounselorImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(Counselor.this, CounselorDetails.class);
                intent.putExtra("counselorNumber","1");
                startActivity(intent);
            }
        });
        CounselorImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(Counselor.this, CounselorDetails.class);
                intent.putExtra("counselorNumber","2");
                startActivity(intent);
            }
        });
        CounselorImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(Counselor.this, CounselorDetails.class);
                intent.putExtra("counselorNumber","3");
                startActivity(intent);
            }
        });
        CounselorImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(Counselor.this, CounselorDetails.class);
                intent.putExtra("counselorNumber","4");
                startActivity(intent);
            }
        });
        CounselorImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd2.show();
                Intent intent = new Intent(Counselor.this, CounselorDetails.class);
                intent.putExtra("counselorNumber","5");
                startActivity(intent);
            }
        });
        reff = FirebaseDatabase.getInstance().getReference().child("counselors");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Name1 = dataSnapshot.child("Counselor1").child("Name").getValue().toString();
                Name2 = dataSnapshot.child("Counselor2").child("Name").getValue().toString();
                Name3 = dataSnapshot.child("Counselor3").child("Name").getValue().toString();
                Name4 = dataSnapshot.child("Counselor4").child("Name").getValue().toString();
                Name5 = dataSnapshot.child("Counselor5").child("Name").getValue().toString();
                Des1 = dataSnapshot.child("Counselor1").child("Designation").getValue().toString();
                Des2 = dataSnapshot.child("Counselor2").child("Designation").getValue().toString();
                Des3 = dataSnapshot.child("Counselor3").child("Designation").getValue().toString();
                Des4 = dataSnapshot.child("Counselor4").child("Designation").getValue().toString();
                Des5 = dataSnapshot.child("Counselor5").child("Designation").getValue().toString();
                Loc1 = dataSnapshot.child("Counselor1").child("Location").getValue().toString();
                Loc2 = dataSnapshot.child("Counselor2").child("Location").getValue().toString();
                Loc3 = dataSnapshot.child("Counselor3").child("Location").getValue().toString();
                Loc4 = dataSnapshot.child("Counselor4").child("Location").getValue().toString();
                Loc5 = dataSnapshot.child("Counselor5").child("Location").getValue().toString();
                Img1 = dataSnapshot.child("Counselor1").child("ImageURL").getValue().toString();
                Img2 = dataSnapshot.child("Counselor2").child("ImageURL").getValue().toString();
                Img3 = dataSnapshot.child("Counselor3").child("ImageURL").getValue().toString();
                Img4 = dataSnapshot.child("Counselor4").child("ImageURL").getValue().toString();
                Img5 = dataSnapshot.child("Counselor5").child("ImageURL").getValue().toString();
                CounselorName1.setText(Name1);
                CounselorName2.setText(Name2);
                CounselorName3.setText(Name3);
                CounselorName4.setText(Name4);
                CounselorName5.setText(Name5);
                CounselorDesignation1.setText(Des1);
                CounselorDesignation2.setText(Des2);
                CounselorDesignation3.setText(Des3);
                CounselorDesignation4.setText(Des4);
                CounselorDesignation5.setText(Des5);
                CounselorLoc1.setText(Loc1);
                CounselorLoc2.setText(Loc2);
                CounselorLoc3.setText(Loc3);
                CounselorLoc4.setText(Loc4);
                CounselorLoc5.setText(Loc5);
                CounselorImage1.setImageBitmap(getImageBitmap(Img1));
                CounselorImage2.setImageBitmap(getImageBitmap(Img2));
                CounselorImage3.setImageBitmap(getImageBitmap(Img3));
                CounselorImage4.setImageBitmap(getImageBitmap(Img4));
                CounselorImage5.setImageBitmap(getImageBitmap(Img5));
                CounselorTV.setVisibility(View.VISIBLE);
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Counselor.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        super.onResume();
        pd2.dismiss();
    }
}
