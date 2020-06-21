package com.example.nav;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {

    EditText username, password;
    Button registerButton;
    String user, pass, user_email, user_name, date,confirm;
    TextView login;
    EditText TName;
    EditText TEmail;
    EditText TDate;
    EditText TConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.register_button);
        login = findViewById(R.id.login1);
        TDate= findViewById(R.id.dob);
        TEmail= findViewById(R.id.email);
        TConfirm=findViewById(R.id.confirm);
        TName=findViewById(R.id.name);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                user_email = TEmail.getText().toString();
                user_name = TName.getText().toString();
                confirm = TConfirm.getText().toString();
                date = TDate.getText().toString();


                if(user.equals("")){
                    username.setError("can't be blank");
                }
                else if(pass.equals("")){
                    password.setError("can't be blank");
                }
                else if(!user.matches("[A-Za-z0-9]+")){
                    username.setError("only alphabet or number allowed");
                }
                else if(user.length()<5){
                    username.setError("at least 5 characters long");
                }
                else if(pass.length()<5){
                    password.setError("at least 5 characters long");
                } else if(!(user_email.contains("@") && user_email.contains("."))) {
                    Toast.makeText(Register.this, "Please enter a valid Email", Toast.LENGTH_SHORT).show();
                }
                else if(date.length()!=10 || (!date.contains("/"))){
                    Toast.makeText(Register.this, "Incorrect DOB format", Toast.LENGTH_SHORT).show();
                }
                else if(!(pass.equals(confirm))) {
                    Toast.makeText(Register.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(Register.this);
                    pd.setMessage("Loading...");
                    pd.show();


                    String url = "https://flyapp-84c6a.firebaseio.com/users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://flyapp-84c6a.firebaseio.com/users");

                            if(s.equals("null")) {
                                reference.child(user).child("Password").setValue(pass);
                                reference.child(user).child("Email").setValue(user_email);
                                reference.child(user).child("Name").setValue(user_name);
                                reference.child(user).child("DOB").setValue(date);

                                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this,Login.class);
                                startActivity(intent);
                            }
                            else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(user)) {
                                        reference.child(user).child("Password").setValue(pass);
                                        reference.child(user).child("Email").setValue(user_email);
                                        reference.child(user).child("Name").setValue(user_name);
                                        reference.child(user).child("DOB").setValue(date);

                                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Register.this,Login.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(Register.this, "Username Already Exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }

                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError );
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                    rQueue.add(request);
                }
            }
        });
    }
}

