package com.example.thefinalproject.businessManager.choosGel;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.R;
import com.example.thefinalproject.businessManager.activities.ChoosGelPolishStyleActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentChoosGel extends Fragment implements ChoosAdpter.OnItemClickListener{

    private RecyclerView main_LST_list_image;
    private ChoosAdpter choosAdpter;

    private ImageView choos_LBL_add_image;

    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_image, container, false);

        main_LST_list_image = view.findViewById(R.id.main_LST_list_image);
        main_LST_list_image.setLayoutManager(new LinearLayoutManager(getActivity()));

        choos_LBL_add_image = view.findViewById(R.id.choos_LBL_add_image);

        choos_LBL_add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChoosGel();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Image"); // Initialize the database reference

        fetchImagesFromDatabase();

        return view;
    }

    private void fetchImagesFromDatabase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> imageUrls = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String imageUrl = snapshot.getValue(Model.class).getImageUri();
                    if (imageUrl != null) {
                        imageUrls.add(imageUrl);
                    }
                }

                // Pass the imageUrls to the adapter and set the item click listener
                choosAdpter = new ChoosAdpter(FragmentChoosGel.this, imageUrls);
                choosAdpter.setOnItemClickListener(FragmentChoosGel.this);
                main_LST_list_image.setAdapter(choosAdpter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error if needed
            }
        });
    }

    private void setupRecyclerView(ArrayList<String> imageUrls) {
        choosAdpter = new ChoosAdpter(this, imageUrls);
        main_LST_list_image.setAdapter(choosAdpter);
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click if needed
    }

    private void openChoosGel(){
        Intent intent = new Intent( getActivity(),  ChoosGelPolishStyleActivity.class);
        startActivity(intent);
    }
}






