package com.example.nav.world_tour;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.nav.R;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;

public class WorldTourActivity extends AppCompatActivity {
    TextView Title;
    int count = 0;
    DatabaseReference reff, reff1;
    RecyclerView users;
    ArrayList<WorldTourCardView> details;
    WorldTourAdapter WorldTourAdapter;
    ProgressDialog pd;
    int size;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_tour);
        users = findViewById(R.id.users);

        getSupportActionBar().setCustomView(R.layout.world_tour_action_bar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View view = getSupportActionBar().getCustomView();
        Title = view.findViewById(R.id.users);
        users.setLayoutManager(new LinearLayoutManager(this));
        details = new ArrayList<>();
        FirebaseApp.initializeApp(this);
        Firebase.setAndroidContext(this);

        final SearchView mySearchView = view.findViewById(R.id.mySearchView);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }
                            @Override
                            public boolean onQueryTextChange(String newText) {
                                WorldTourAdapter.getFilter().filter(newText);
                                return false;
                            }

                        });
                    }
                }, 3000
        );

        int searchCloseButtonId = mySearchView.getContext().getResources().getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeButton = (ImageView) mySearchView.findViewById(searchCloseButtonId);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mySearchView.getQuery().toString().equals("")) {
                    mySearchView.onActionViewCollapsed();
                } else {
                    mySearchView.setQuery("", false);
                    recreate();
                }
            }
        });

        reff = FirebaseDatabase.getInstance().getReference().child("worldtour");
        pd = new ProgressDialog(WorldTourActivity.this);
        pd.setMessage("Fetching data");
        pd.show();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(count == 0) {
                    size = (int) dataSnapshot.getChildrenCount();
                    for (final DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                        reff1 = FirebaseDatabase.getInstance().getReference().child("worldtour").child(childDataSnapshot.getKey());
                        reff1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                WorldTourCardView d = snapshot.getValue(WorldTourCardView.class);
                                    details.add(d);
                                    WorldTourAdapter = new WorldTourAdapter(details, WorldTourActivity.this);
                                    users.setAdapter(WorldTourAdapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(WorldTourActivity.this, "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    count++;
                } else {
                    recreate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(WorldTourActivity.this, "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    pd.dismiss();
                } catch (Exception e) {

                }
            }
        }, 3000);
    }
}
