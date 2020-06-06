package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.firebase.client.Firebase;

public class Feedback extends AppCompatActivity {
    EditText etName;
    EditText etEmail;
    EditText etSubject;
    EditText etFeedback;
    Button   b_submit;
    String name,email,sub,Feedback;
    ImageView backfeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        etName = findViewById(R.id.name2);
        etEmail = findViewById(R.id.email2);
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
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                sub = etSubject.getText().toString();
                Feedback = etFeedback.getText().toString();

                if(name.equals("")){
                    etName.setError("Cant be blank");
                }
                else if(email.equals("")){
                    etEmail.setError("Cant be blank");
                }
                else if(sub.equals("")){
                    etSubject.setError("Cant be blank");
                }
                else if(Feedback.equals("")){
                    etFeedback.setError("Cant be blank");
                }

                else {
                    Firebase reference = new Firebase("https://flyapp-84c6a.firebaseio.com/feedback");
                    String Name = etName.getText().toString().trim();
                    String Email = etEmail.getText().toString().trim();
                    String Subject = etSubject.getText().toString().trim();
                    String Feedback = etFeedback.getText().toString().trim();

                    reference.child(Name).child("Email").setValue(Email);
                    reference.child(Name).child("Subject").setValue(Subject);
                    reference.child(Name).child("Feedback").setValue(Feedback);

                    Toast.makeText(Feedback.this, "Thanks for giving your feedback", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
