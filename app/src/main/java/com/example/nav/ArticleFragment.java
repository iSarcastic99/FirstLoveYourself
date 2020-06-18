package com.example.nav;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ArticleFragment extends AppCompatActivity {
   // SearchView mySearchView;
    ListView myList;
    ArrayList<String> al = new ArrayList<>();
    ArrayList<String> arrlist;
    ImageView backarticle;
    int size;
    SearchView mySearchView;
    String classes[] = {"Article1","Article2"};
    ArrayAdapter<String> arradapter;
    DatabaseReference reff;
    int j;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_articles);

        mySearchView =  findViewById(R.id.searchView);
        myList =  findViewById(R.id.myList);
        backarticle = findViewById(R.id.articleback);
        arrlist = new ArrayList<>();
        Firebase.setAndroidContext(this);
        pd = new ProgressDialog(ArticleFragment.this);
        pd.setMessage("Loading...");
        pd.show();
        reff = FirebaseDatabase.getInstance().getReference();

        backarticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arradapter.getFilter().filter(newText);
                return false;
            }
        });

        reff.child("articles").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arradapter = new ArrayAdapter<>(ArticleFragment.this, android.R.layout.simple_list_item_1, arrlist);
                myList.setAdapter(arradapter);
                size = (int) dataSnapshot.getChildrenCount();
                for(j=1;j<=size;j++) {
                    String i = Integer.toString(j);
                    arrlist.add(dataSnapshot.child("Article"+i).child("Title").getValue().toString());
                    pd.dismiss();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                pd.dismiss();
                Toast.makeText(ArticleFragment.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(ArticleFragment.this, Article1.class);
                String pos = Integer.toString(position+1);
                intent1.putExtra("articleNumber", pos);
                startActivity(intent1);
            }
        });
    }
}
