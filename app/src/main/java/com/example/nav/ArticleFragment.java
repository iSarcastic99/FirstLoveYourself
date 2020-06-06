package com.example.nav;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ArticleFragment extends AppCompatActivity {
    SearchView mySearchView;
    ListView myList;
    ArrayList<String> al = new ArrayList<>();
    ArrayList<String> arrlist;
    ImageView backarticle;
    String classes[] = {"Article1","Article2"};
    ArrayAdapter<String> arradapter;
    int totalArt = 0;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_articles);

        mySearchView =  findViewById(R.id.searchView);
        myList =  findViewById(R.id.myList);
        backarticle = findViewById(R.id.articleback);
        arrlist = new ArrayList<String>();

        arrlist.add("Dealing With Problems");
        arrlist.add("Overcoming Negativity");
        arrlist.add("Never Quit");
        arrlist.add("The Sky Has Never Been The Limit");
        arrlist.add("Your Past And The Future");
        arrlist.add("Finding Your Purpose");
        arrlist.add("Invest In Yourself");
        arrlist.add("Discover The Real You");
        arrlist.add("The Winning Mindset");
        arrlist.add("To All The Dreamers Out There");
        backarticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        arradapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrlist);
        myList.setAdapter(arradapter);
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
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             switch (position)
             {
                 case 0:
                     Intent intent1 = new Intent(ArticleFragment.this,Article1.class);
                     startActivity(intent1);
                     break;
                 case 1:
                     Intent intent2 = new Intent(ArticleFragment.this,Article2.class);
                     startActivity(intent2);
                     break;
                 case 2:
                     Intent intent3 = new Intent(ArticleFragment.this,Article3.class);
                     startActivity(intent3);
                     break;
                 case 3:
                     Intent intent4 = new Intent(ArticleFragment.this,Article4.class);
                     startActivity(intent4);
                     break;
                 case 4:
                     Intent intent5 = new Intent(ArticleFragment.this,Article5.class);
                     startActivity(intent5);
                     break;
                 case 5:
                     Intent intent6 = new Intent(ArticleFragment.this,Article6.class);
                     startActivity(intent6);
                     break;
                 case 6:
                     Intent intent7 = new Intent(ArticleFragment.this,Article7.class);
                     startActivity(intent7);
                     break;
                 case 7:
                     Intent intent8 = new Intent(ArticleFragment.this,Article8.class);
                     startActivity(intent8);
                     break;
                 case 8:
                     Intent intent9 = new Intent(ArticleFragment.this,Article9.class);
                     startActivity(intent9);
                     break;
                 case 9:
                     Intent intent10 = new Intent(ArticleFragment.this,Article10.class);
                     startActivity(intent10);
                     break;
             }
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

                    totalArt++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }





    }
}
