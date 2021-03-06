package com.example.nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Humorous_Video_1 extends AppCompatActivity {
    VideoView videoView;
    ProgressDialog pd;
    ImageView imgplay, backhum1;
    ProgressBar pb;
    Intent intent;
    TextView curr, tot, videoTitle;
    int dur, current=0;
    String Title, URL, VideoNumber;
    DatabaseReference reff;
    boolean isPlaying = false, isGone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_humorous__video_1);
        Firebase.setAndroidContext(this);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        backhum1 = findViewById(R.id.humback1);
        videoTitle = findViewById(R.id.videotitle);
        videoView = findViewById(R.id.vView);
        pb = findViewById(R.id.humprogressBar1);
        pb.setMax(100);
        curr = findViewById(R.id.humcurrent1);
        curr = findViewById(R.id.humcurrent1);
        tot = findViewById(R.id.humtotal1);
        imgplay = findViewById(R.id.playimg1);
        intent = getIntent();
        VideoNumber = intent.getStringExtra("videoNumber");
        getVideo(VideoNumber);
        backhum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgplay.performClick();
                finish();
            }
        });

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!isGone)
                    hideViews();
                else
                    showViews();
                return false;
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                try {
                    dur = mp.getDuration() / 1000;
                    String duration1 = String.format("%02d:%02d", dur / 60, dur % 60);
                    tot.setText(duration1);
                    autoHide();
                    pd.dismiss();
                }catch(Exception e){
                    tot.setVisibility(View.INVISIBLE);
                }
            }
        });
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying==false){
                    Humorous_Video_1.VideoProgress vp = new Humorous_Video_1.VideoProgress();
                    vp.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            if(what == MediaPlayer.MEDIA_INFO_BUFFERING_START){
                                pd.show();
                            }
                            if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END){
                                pd.dismiss();
                            }
                            return false;
                        }
                    });
                    videoView.start();
                    isPlaying = true;
                    imgplay.setImageResource(R.drawable.ic_pause_black_24dp);

                }
                else{
                    videoView.pause();
                    isPlaying = false;
                    imgplay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                }
            }

        });
        imgplay.performClick();

    }
    @Override
    protected void onStop() {
        super.onStop();
        isPlaying = false;
    }
    public class VideoProgress extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            do {
                if(isPlaying) {
                    current = videoView.getCurrentPosition() / 1000;
                    try {
                        int currentPercent = current * 100 / dur;
                        publishProgress(current);
                    } catch (Exception e) {

                    }
                }
            } while (pb.getProgress() <= 100);

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            try {
                int currentPercent = (values[0]) * 100 / dur;
                pb.setProgress(currentPercent);
                String currentString = String.format("%02d:%02d", ((values[0]) / 60), ((values[0]) % 60));
                curr.setText(currentString);
            } catch (Exception e) {
                curr.setVisibility(View.INVISIBLE);
            }
        }
    }
    @Override
    public void onBackPressed() {
        isPlaying = false;
        imgplay.performClick();
        super.onBackPressed();
    }
    public void getVideo(final String videoNumber){
        reff = FirebaseDatabase.getInstance().getReference().child("videos");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Title = dataSnapshot.child("Comedy"+videoNumber).child("Title").getValue().toString();
                URL = dataSnapshot.child("Comedy"+videoNumber).child("URL").getValue().toString();
                videoTitle.setText(Title);
                Uri uri = Uri.parse(URL);
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.seekTo(1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Humorous_Video_1.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void hideViews(){
        imgplay.setVisibility(View.GONE);
        backhum1.setVisibility(View.GONE);
        curr.setVisibility(View.GONE);
        videoTitle.setVisibility(View.GONE);
        tot.setVisibility(View.GONE);
        pb.setVisibility(View.GONE);
        isGone = true;
    }

    public void showViews(){
        imgplay.setVisibility(View.VISIBLE);
        backhum1.setVisibility(View.VISIBLE);
        curr.setVisibility(View.VISIBLE);
        videoTitle.setVisibility(View.VISIBLE);
        tot.setVisibility(View.VISIBLE);
        pb.setVisibility(View.VISIBLE);
        isGone = false;
        autoHide();
    }
    public void autoHide(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideViews();
            }
        }, 3000);
    }
}
