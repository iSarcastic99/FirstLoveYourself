package com.example.nav.world_tour;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nav.Places;
import com.example.nav.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

public class WorldTourAdapter extends RecyclerView.Adapter<WorldTourAdapter.MyViewHolder> implements Filterable {
    Context context;
    Bitmap bitmap;
    ArrayList<WorldTourCardView> details;
    ArrayList<WorldTourCardView> fullDetails;

    public WorldTourAdapter(ArrayList<WorldTourCardView> d, Context c) {
        context = c;
        details = d;
        fullDetails = new ArrayList<>(d);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.world_tour_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            try {
                holder.Name.setText(details.get(position).getTitle());
                holder.cardView.setVisibility(View.VISIBLE);
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    getImage(details.get(position).getTitle(), holder);
                                } catch (Exception e) {

                                }

                            }
                        });
                    }
                };
                thread.start();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        final ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();
                        StorageReference mImageRef = FirebaseStorage.getInstance().getReference("WorldTour/" + details.get(position).getTitle() + ".jpg");
                        mImageRef.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Intent intent = new Intent(context, Places.class);
                                intent.putExtra("place", details.get(position).getTitle());
                                intent.putExtra("image", bitmap);
                                view.getContext().startActivity(intent);
                                progressDialog.dismiss();
                            }
                        });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public int getItemCount() {
        return details ==null ? 0 : details.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Name;
        ImageView Profile_Picture;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            Name = itemView.findViewById(R.id.place_name);
            Profile_Picture = itemView.findViewById(R.id.profile_picture);
            cardView = itemView.findViewById(R.id.user_card);
        }
    }

    @Override
    public Filter getFilter() {
        return detailsFilter;
    }

    private Filter detailsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<WorldTourCardView> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0){
                filteredList.addAll(fullDetails);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (WorldTourCardView item : fullDetails){
                    try {
                        if (item.getTitle().toLowerCase().contains(filterPattern)){
                            filteredList.add(item);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            details.clear();
            details.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public void getImage(String name, final MyViewHolder holder){
        final Bitmap[] bm = new Bitmap[1];
        StorageReference mImageRef = FirebaseStorage.getInstance().getReference("WorldTour/" + name + ".jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        mImageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                bm[0] = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.Profile_Picture.setImageBitmap(bm[0]);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });
    }
}
