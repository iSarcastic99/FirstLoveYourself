package com.example.nav;

import android.content.Intent;
import androidx.annotation.NonNull;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    NavigationView Article;
    DatabaseReference reff;
    String email, S, name;
    int i;
    TextView uemail;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Article = findViewById(R.id.nav_articles);
        LayoutInflater inflater = getLayoutInflater();
        Toolbar toolbar = findViewById(R.id.toolbar);
        logout = findViewById(R.id.logout);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.draw_layout);
        Firebase.setAndroidContext(this);
        FirebaseApp.initializeApp(this);
        SharedPreferences preferences = getSharedPreferences(S,i);
        name = preferences.getString("Username","No name");
        reff = FirebaseDatabase.getInstance().getReference().child("users").child(name);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                email = dataSnapshot.child("Email").getValue().toString();
                uemail.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        View myview = inflater.inflate(R.layout.nav_header,null);
        TextView uname = navigationView.getHeaderView(0).findViewById(R.id.name_of_user);
        uemail = navigationView.getHeaderView(0).findViewById(R.id.email_of_user);
        uname.setText(name);
        navigationView.setNavigationItemSelectedListener(this);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.nav_videos:
               Intent intent1 = new Intent(MainActivity.this,VideosFragment.class);
               startActivity(intent1);
               break;
            case R.id.nav_world_tour:
                Intent intent4 = new Intent(MainActivity.this,World_Tour.class);
                startActivity(intent4);
                break;
            case R.id.nav_chat:
                Intent intent = new Intent(MainActivity.this, ChatFragment.class);
                startActivity(intent);
                break;
            case R.id.nav_articles:
                Intent intent3 = new Intent(MainActivity.this, ArticleFragment.class);
                startActivity(intent3);
                break;
            case R.id.nav_events:
                Intent intent6 = new Intent(MainActivity.this, Events.class);
                startActivity(intent6);
                break;
            case R.id.nav_counselor:
                Intent intent5 = new Intent(MainActivity.this, Counselor.class);
                startActivity(intent5);
                break;
            case R.id.nav_contact_us:
              Intent contactIntent = new Intent(MainActivity.this,ContactUs.class);
              startActivity(contactIntent);
                break;
            case R.id.nav_feedback:
                Intent feedbackIntent = new Intent(MainActivity.this,Feedback.class);
                startActivity(feedbackIntent);
                break;
            case R.id.logout_button:
                SharedPreferences.Editor editor = getSharedPreferences(S,i).edit();
                editor.putString("Username","No name");
                editor.apply();
                Intent logoutIntent = new Intent(MainActivity.this,Login.class);
                startActivity(logoutIntent);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer((GravityCompat.START));
        } else {
              finish();
        }
    }

}
