package com.example.thefinalproject.businessManager.choosGel;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChoosAdpter extends RecyclerView.Adapter<ChoosAdpter.ChoosHolder> {

    private Fragment fragment;
    private ArrayList<String> imageUrls;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ChoosAdpter(Fragment fragment, ArrayList<String> imageUrls) {
        this.fragment = fragment;
        this.imageUrls = imageUrls != null ? imageUrls : new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return imageUrls == null ? 0 : imageUrls.size();
    }

    @Override
    public ChoosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_image, parent, false);
        ChoosHolder choosHolder = new ChoosHolder(view);
        return choosHolder;
    }

    @Override
    public void onBindViewHolder(final ChoosHolder holder, @SuppressLint("RecyclerView") final int position) {
        String imageUrl = imageUrls.get(position);

        Picasso.get().load(imageUrl).into(holder.title_image);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(imageUrl);
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(fragment)
                        .load(uri)
                        .into(holder.title_image);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("FirebaseImageLoading", "Failed to load image from Firebase Storage: " + e.getMessage());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    static class ChoosHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private ImageView title_image;

        public ChoosHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title_image = itemView.findViewById(R.id.title_image);
        }
    }
}






