package com.example.nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventCities extends AppCompatActivity {
    Button CityButton1, CityButton2, CityButton3, CityButton4, CityButton5, CityButton6, CityButton7, OtherButton;
    DatabaseReference reff;
    Firebase firebase;
    String S, Username;
    ProgressDialog pd;
    TextView TitleTV;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(S,i);
        Username = preferences.getString("Username","");
        setContentView(R.layout.activity_event_cities);
        CityButton1 = findViewById(R.id.city1);
        CityButton2 = findViewById(R.id.city2);
        CityButton3 = findViewById(R.id.city3);
        CityButton4 = findViewById(R.id.city4);
        CityButton5 = findViewById(R.id.city5);
        CityButton6 = findViewById(R.id.city6);
        CityButton7 = findViewById(R.id.city7);
        OtherButton = findViewById(R.id.other);
        TitleTV = findViewById(R.id.title);
        FirebaseApp.initializeApp(this);
        Firebase.setAndroidContext(this);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.show();

        firebase = new Firebase("https://flyapp-84c6a.firebaseio.com/users");
        reff = FirebaseDatabase.getInstance().getReference().child("events");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CityButton1.setText(dataSnapshot.child("City1").child("Name").getValue().toString());
                CityButton2.setText(dataSnapshot.child("City2").child("Name").getValue().toString());
                CityButton3.setText(dataSnapshot.child("City3").child("Name").getValue().toString());
                CityButton4.setText(dataSnapshot.child("City4").child("Name").getValue().toString());
                CityButton5.setText(dataSnapshot.child("City5").child("Name").getValue().toString());
                CityButton6.setText(dataSnapshot.child("City6").child("Name").getValue().toString());
                CityButton7.setText(dataSnapshot.child("City7").child("Name").getValue().toString());
                OtherButton.setText("Other");
                pd.dismiss();
                TitleTV.setVisibility(View.VISIBLE);
                CityButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebase.child(Username).child("City").setValue(CityButton1.getText().toString());
                        Intent intent = new Intent(EventCities.this, Events.class);
                        intent.putExtra("cityNumber", "1");
                        startActivity(intent);
                        finish();
                    }
                });

                CityButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebase.child(Username).child("City").setValue(CityButton2.getText().toString());
                        Intent intent = new Intent(EventCities.this, Events.class);
                        intent.putExtra("cityNumber", "2");
                        startActivity(intent);
                        finish();
                    }
                });

                CityButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebase.child(Username).child("City").setValue(CityButton3.getText().toString());
                        Intent intent = new Intent(EventCities.this, Events.class);
                        intent.putExtra("cityNumber", "3");
                        startActivity(intent);
                        finish();
                    }
                });

                CityButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebase.child(Username).child("City").setValue(CityButton4.getText().toString());
                        Intent intent = new Intent(EventCities.this, Events.class);
                        intent.putExtra("cityNumber", "4");
                        startActivity(intent);
                        finish();
                    }
                });

                CityButton5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebase.child(Username).child("City").setValue(CityButton5.getText().toString());
                        Intent intent = new Intent(EventCities.this, Events.class);
                        intent.putExtra("cityNumber", "5");
                        startActivity(intent);
                        finish();
                    }
                });

                CityButton6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebase.child(Username).child("City").setValue(CityButton6.getText().toString());
                        Intent intent = new Intent(EventCities.this, Events.class);
                        intent.putExtra("cityNumber", "6");
                        startActivity(intent);
                        finish();
                    }
                });

                CityButton7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebase.child(Username).child("City").setValue(CityButton7.getText().toString());
                        Intent intent = new Intent(EventCities.this, Events.class);
                        intent.putExtra("cityNumber", "7");
                        startActivity(intent);
                        finish();
                    }
                });

                OtherButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(EventCities.this, "This service is currently not available in your city", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EventCities.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                finish();
            }
        });
    }
}
