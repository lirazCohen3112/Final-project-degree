package com.example.thefinalproject.client.ListCares;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.businessManager.ManagementCareTypes.Care;
import com.example.thefinalproject.R;
import com.example.thefinalproject.businessManager.ManagementCareTypes.CareList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Client_FragmentListCares extends Fragment {

    private RecyclerView main_LST_list_care;
    private ArrayList<Care> cares;
    private Client_CaresAdapter careAdapter;

    private FirebaseDatabase database;

    private DatabaseReference myRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.client_fragment_list_care, container, false);

        database = FirebaseDatabase.getInstance();

        readData();

        main_LST_list_care= view.findViewById(R.id.main_LST_list_care);

        // CareList.addCare("lak", "wert", "30.5");

        Show();

        return view;
    }

    public void Show() {
        cares = CareList.getCareList();
        careAdapter = new Client_CaresAdapter(this, cares);
        main_LST_list_care.setLayoutManager(new LinearLayoutManager(getContext()));
        main_LST_list_care.setAdapter(careAdapter);

    }

    private void readData() {
        myRef = database.getReference("cares");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CareList.clear();
                for (DataSnapshot m :  dataSnapshot.getChildren())
                {
                    CareList.add(m.getValue(Care.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
