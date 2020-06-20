package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class Intro extends AppCompatActivity {
int time = 2000, i;
String S, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(S,i);
        name = preferences.getString("Username","No name");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);
        setContentView(R.layout.activity_intro);
        new Handler().postDelayed((new Runnable() {
            @Override
            public void run() {
                if(name.equals("No name")) {
                    Intent intro = new Intent(Intro.this, Login.class);
                    startActivity(intro);
                }
                else{
                    Intent intent = new Intent(Intro.this,MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }),time);
    }
}
