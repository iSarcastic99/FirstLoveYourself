package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.firebase.client.Firebase;

public class Humorous_Video_4 extends AppCompatActivity {
    VideoView videoView;
    ProgressDialog pd;
    ImageView imgplay,fs,backhum1;
    ProgressBar pb;
    TextView curr,tot;
    int dur,current=0;
    boolean isPlaying = false, isFull = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humorous__video_4);
        Firebase.setAndroidContext(this);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        backhum1 = findViewById(R.id.humback4);
        videoView = findViewById(R.id.vView4);
        pb = findViewById(R.id.humprogressBar4);
        pb.setMax(100);
        curr = findViewById(R.id.humcurrent4);
        fs = findViewById(R.id.humfs4);
        curr = findViewById(R.id.humcurrent4);
        tot = findViewById(R.id.humtotal4);
        imgplay = findViewById(R.id.playimg4);
        backhum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        String str = "https://firebasestorage.googleapis.com/v0/b/flyapp-84c6a.appspot.com/o/Videos%2FHumorous%2FThe%20Truth%20of%20Engineering%20-%20AIB-%20Honest%20Engineering%20Campus%20Placement.mp4?alt=media&token=097c8e09-bc6a-49d2-8fb1-200947162dc6";
        Uri uri = Uri.parse(str);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.seekTo(1);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                try {
                    dur = mp.getDuration() / 1000;
                    String duration1 = String.format("%02d:%02d", dur / 60, dur % 60);
                    tot.setText(duration1);
                }catch (Exception e){
                    tot.setVisibility(View.INVISIBLE);
                }
            }
        });
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying==false){
                    Humorous_Video_4.VideoProgress vp = new Humorous_Video_4.VideoProgress();
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


    }
    @Override
    protected void onStop() {
        super.onStop();

        isPlaying = false;
        isFull = false;
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
        super.onBackPressed();
    }
}
