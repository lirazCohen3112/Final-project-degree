package com.example.thefinalproject.client.activities;


 import android.os.Bundle;

 import androidx.fragment.app.Fragment;

 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;

 import com.example.thefinalproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragmentAbout extends Fragment {


    public fragmentAbout() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.client_fragment_about, container, false);
    }

}
