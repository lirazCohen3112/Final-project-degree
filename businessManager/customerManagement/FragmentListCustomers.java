package com.example.thefinalproject.businessManager.customerManagement;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.Login.LoginPageActivity;
import com.example.thefinalproject.R;
import com.example.thefinalproject.businessManager.activities.customerProfileActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentListCustomers extends Fragment implements CoustomerAdpter.OnItemClickListener{

    private FirebaseDatabase database;

    private DatabaseReference myRef;
    private RecyclerView main_LST_list_customers;

    private ArrayList <Coustumer> coustomers;

    private CoustomerAdpter coustomersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_customers , container, false);

        database = FirebaseDatabase.getInstance();

        main_LST_list_customers = view.findViewById(R.id.main_LST_list_customers);
        main_LST_list_customers.setLayoutManager(new LinearLayoutManager(getActivity()));

        readData();

        Show();

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Coustumer coustomer = coustomers.get(position);

        Intent intent = new Intent(getActivity(), customerProfileActivity.class);
        intent.putExtra("name", coustomer.getName());
        intent.putExtra("phoneNum", coustomer.getPhoneNum());
        intent.putExtra("email", coustomer.getEmail());
        startActivity(intent);
    }

    public void Show() {
        coustomers = CoustumerList.getCoustomersList();
        coustomersAdapter = new CoustomerAdpter(this, coustomers);
        coustomersAdapter.setOnItemClickListener(this);
        main_LST_list_customers.setAdapter(coustomersAdapter);
    }

    public void readData() {
        myRef = database.getReference("customers");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CoustumerList.clear();
                for (DataSnapshot c :  dataSnapshot.getChildren()) {
                    CoustumerList.add(c.getValue(Coustumer.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}


