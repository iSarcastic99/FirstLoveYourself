package com.example.nav;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Inspiration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Comedy extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView Humimage1, Humimage2, Humimage3, Humimage4, Humimage5;
    DatabaseReference reff;
    ProgressDialog pd;
    TextView titleTV1, titleTV2, titleTV3, titleTV4, titleTV5;
    String ThumbnailURL1, ThumbnailURL2, ThumbnailURL3, ThumbnailURL4, ThumbnailURL5;
    String Title1, Title2, Title3, Title4, Title5;

    public Comedy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Inspiration.
     */
    // TODO: Rename and change types and number of parameters
    public static Comedy newInstance(String param1, String param2) {
        Comedy fragment = new Comedy();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comedy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pd = new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        pd.show();
        Humimage1 = getView().findViewById(R.id.humimage1);
        Humimage2 = getView().findViewById(R.id.humimage2);
        Humimage3 = getView().findViewById(R.id.humimage3);
        Humimage4 = getView().findViewById(R.id.humimage4);
        Humimage5 = getView().findViewById(R.id.humimage5);
        titleTV1 = getView().findViewById(R.id.title1);
        titleTV2 = getView().findViewById(R.id.title2);
        titleTV3 = getView().findViewById(R.id.title3);
        titleTV4 = getView().findViewById(R.id.title4);
        titleTV5 = getView().findViewById(R.id.title5);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        reff = FirebaseDatabase.getInstance().getReference().child("videos");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ThumbnailURL1 = dataSnapshot.child("Comedy1").child("ThumbnailURL").getValue().toString();
                ThumbnailURL2 = dataSnapshot.child("Comedy2").child("ThumbnailURL").getValue().toString();
                ThumbnailURL3 = dataSnapshot.child("Comedy3").child("ThumbnailURL").getValue().toString();
                ThumbnailURL4 = dataSnapshot.child("Comedy4").child("ThumbnailURL").getValue().toString();
                ThumbnailURL5 = dataSnapshot.child("Comedy5").child("ThumbnailURL").getValue().toString();
                Title1 = dataSnapshot.child("Comedy1").child("Title").getValue().toString();
                Title2 = dataSnapshot.child("Comedy2").child("Title").getValue().toString();
                Title3 = dataSnapshot.child("Comedy3").child("Title").getValue().toString();
                Title4 = dataSnapshot.child("Comedy4").child("Title").getValue().toString();
                Title5 = dataSnapshot.child("Comedy5").child("Title").getValue().toString();
                titleTV1.setText(Title1);
                titleTV2.setText(Title2);
                titleTV3.setText(Title3);
                titleTV4.setText(Title4);
                titleTV5.setText(Title5);
                Humimage1.setImageBitmap(getImageBitmap(ThumbnailURL1));
                Humimage2.setImageBitmap(getImageBitmap(ThumbnailURL2));
                Humimage3.setImageBitmap(getImageBitmap(ThumbnailURL3));
                Humimage4.setImageBitmap(getImageBitmap(ThumbnailURL4));
                Humimage5.setImageBitmap(getImageBitmap(ThumbnailURL5));
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

        Humimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(v.getContext(),Humorous_Video_1.class);
                intent1.putExtra("videoNumber", "1");
                startActivity(intent1);
            }
        });
        Humimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(v.getContext(),Humorous_Video_1.class);
                intent2.putExtra("videoNumber", "2");
                startActivity(intent2);
            }
        });
        Humimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(v.getContext(),Humorous_Video_1.class);
                intent3.putExtra("videoNumber", "3");
                startActivity(intent3);
            }
        });
        Humimage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(v.getContext(),Humorous_Video_1.class);
                intent4.putExtra("videoNumber", "4");
                startActivity(intent4);
            }
        });
        Humimage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(v.getContext(),Humorous_Video_1.class);
                intent5.putExtra("videoNumber", "5");
                startActivity(intent5);
            }
        });
    }
    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
        return bm;
    }
}