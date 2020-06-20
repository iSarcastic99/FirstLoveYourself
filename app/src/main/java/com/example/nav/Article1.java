package com.example.nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Article1 extends AppCompatActivity {
    ImageView nightMode, backButton;
    Intent intent;
    String ArticleNumber, Title, Body;
    TextView TitleTV, BodyTV;
    DatabaseReference reff;
    ConstraintLayout OuterLayout;
    LinearLayout InnerLayout;
    Boolean isNightMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article1);
        intent = getIntent();
        ArticleNumber = intent.getStringExtra("articleNumber");
        backButton = findViewById(R.id.article1back);
        nightMode = findViewById(R.id.nightmode);
        TitleTV = findViewById(R.id.title);
        BodyTV = findViewById(R.id.body);
        OuterLayout = findViewById(R.id.outerLayout);
        InnerLayout = findViewById(R.id.innerLayout);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!isNightMode) {
                   OuterLayout.setBackgroundColor(getResources().getColor(R.color.Gray));
                   InnerLayout.setBackgroundColor(getResources().getColor(R.color.Black));
                   TitleTV.setTextColor(getResources().getColor(R.color.White));
                   BodyTV.setTextColor(getResources().getColor(R.color.White));
                   nightMode.setImageDrawable(getDrawable(R.drawable.day_mode));
                   backButton.setImageDrawable(getDrawable(R.drawable.white_arrow));
                   isNightMode = true;
               } else{
                   OuterLayout.setBackground(getDrawable(R.drawable.bgf));
                   InnerLayout.setBackgroundColor(getResources().getColor(R.color.White));
                   TitleTV.setTextColor(getResources().getColor(R.color.Black));
                   BodyTV.setTextColor(getResources().getColor(R.color.Black));
                   nightMode.setImageDrawable(getDrawable(R.drawable.night_mode));
                   backButton.setImageDrawable(getDrawable(R.drawable.ic_keyboard_backspace_black_24dp));
                   isNightMode = false;
               }
            }
        });

        getTitle(ArticleNumber);
        getBody(ArticleNumber);

    }
    public void getTitle(final String articleNumber){
        reff = FirebaseDatabase.getInstance().getReference().child("articles");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Title = dataSnapshot.child("Article"+articleNumber).child("Title").getValue().toString();
                 TitleTV.setText(Title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Article1.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void getBody(final String articleNumber){
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Body = dataSnapshot.child("Article"+articleNumber).child("Body").getValue().toString();
                BodyTV.setText(Body);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Article1.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
