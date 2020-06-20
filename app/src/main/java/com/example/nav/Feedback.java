package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.firebase.client.Firebase;

public class Feedback extends AppCompatActivity {
    EditText etSubject, etFeedback;
    Button   b_submit;
    String   sub, Feedback, Username, S;
    ImageView backfeedback;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(S,i);
        Username = preferences.getString("Username","");
        setContentView(R.layout.activity_feedback);
        backfeedback = findViewById(R.id.feedbackback);
        etSubject = findViewById(R.id.subject);
        etFeedback = findViewById(R.id.feedback);
        b_submit = findViewById(R.id.submit);
        Firebase.setAndroidContext(this);

        backfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        b_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub = etSubject.getText().toString();
                Feedback = etFeedback.getText().toString();

                 if(sub.equals("")){
                    etSubject.setError("Cant be blank");
                }
                else if(Feedback.equals("")){
                    etFeedback.setError("Cant be blank");
                }

                else {
                    Firebase reference = new Firebase("https://flyapp-84c6a.firebaseio.com/feedback");
                    String Subject = etSubject.getText().toString().trim();
                    String Feedback = etFeedback.getText().toString().trim();
                    reference.child(Username).child("Subject").setValue(Subject);
                    reference.child(Username).child("Feedback").setValue(Feedback);
                    Toast.makeText(Feedback.this, "Thanks for giving your feedback", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}
