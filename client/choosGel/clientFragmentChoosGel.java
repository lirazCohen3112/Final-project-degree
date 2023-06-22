package com.example.thefinalproject.client.choosGel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.R;
import com.example.thefinalproject.businessManager.choosGel.Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class clientFragmentChoosGel extends Fragment implements clientChoosAdpter.OnItemClickListener {
    private RecyclerView main_LST_list_image;
    private clientChoosAdpter clientChoosAdpter;

    private DatabaseReference databaseReference;
    private ArrayList<String> imageUrls; // List to hold the image URLs

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.client_fragment_choos_gel, container, false);

        main_LST_list_image = view.findViewById(R.id.main_LST_list_image);
        main_LST_list_image.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference = FirebaseDatabase.getInstance().getReference("Image"); // Initialize the database reference

        imageUrls = new ArrayList<>(); // Initialize the imageUrls list

        // Set up the adapter and attach it to the RecyclerView
        clientChoosAdpter = new clientChoosAdpter(this, imageUrls);
        clientChoosAdpter.setOnItemClickListener(this);
        main_LST_list_image.setAdapter(clientChoosAdpter);

        fetchImagesFromFirebase(); // Fetch images from Firebase

        return view;
    }

    private void fetchImagesFromFirebase() {
        // Assuming you have a child node in the Firebase database named "images"
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageUrls.clear(); // Clear the list before adding new data

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String imageUrl = snapshot.getValue(Model.class).getImageUri();
                    imageUrls.add(imageUrl); // Add each image URL to the list
                }

                clientChoosAdpter.notifyDataSetChanged(); // Notify the adapter about the data change
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseError", "Failed to fetch images from Firebase: " + databaseError.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click if needed
    }
}





