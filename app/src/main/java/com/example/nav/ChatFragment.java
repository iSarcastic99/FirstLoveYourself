package com.example.nav;

import android.R.layout;
import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ChatFragment extends AppCompatActivity {

    SearchView mySearchView;
    ListView usersList;
    TextView noUsersText;
    ImageView backchat;
    String S;
    ArrayList<String> al = new ArrayList<>();
    ArrayAdapter<String> useradapter;
    int totalUsers = 0, i;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);
        backchat = findViewById(R.id.chatback);
        mySearchView = findViewById(R.id.searchView);
        usersList = findViewById(R.id.usersList);
        noUsersText = findViewById(R.id.noUsersText);

        pd = new ProgressDialog(ChatFragment.this);
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://flyapp-84c6a.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(ChatFragment.this);
        rQueue.add(request);
        backchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = getSharedPreferences(S,i).edit();
                editor.putString("ChatWith",al.get(position));
                editor.apply();
                UserDetails.chatWith = al.get(position);
                startActivity(new Intent(ChatFragment.this, MessageFragment.class));
            }
        });



    }

    public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while(i.hasNext()){
                key = i.next().toString();

                if(!key.equals(UserDetails.username)) {
                    al.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(totalUsers <=1){
            noUsersText.setVisibility(View.VISIBLE);
            usersList.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            usersList.setAdapter(new ArrayAdapter<String>(this, layout.simple_list_item_1, al));
        }

        useradapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al);
        usersList.setAdapter(useradapter);
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                useradapter.getFilter().filter(newText);
                return false;
            }
        });

        pd.dismiss();

    }
}