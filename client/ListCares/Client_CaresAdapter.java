package com.example.thefinalproject.client.ListCares;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.R;
import com.example.thefinalproject.businessManager.ManagementCareTypes.Care;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Client_CaresAdapter extends RecyclerView.Adapter<Client_CaresAdapter.CaresHolder>{

    private Fragment fragment;
    private ArrayList<Care> cares;


    public Client_CaresAdapter(Fragment fragment , ArrayList<Care> cares){
        this.cares=cares;
        this.fragment = fragment;
    }

    @Override
    public int getItemCount() {
        return cares == null ? 0 : cares.size();
    }

    @Override
    public Client_CaresAdapter.CaresHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_list_care, parent, false);
        Client_CaresAdapter.CaresHolder caresHolder = new Client_CaresAdapter.CaresHolder(view);
        return caresHolder;
    }

    @Override
    public void onBindViewHolder(final Client_CaresAdapter.CaresHolder holder, final int position) {
        Care care = cares.get(position);

        holder.list_LBL_care_name.setText("" + care.getName());
        holder.list_LBL_care_explanation.setText("  explanation: " + care.getExplanation());
        holder.list_LBL_care_cost.setText("  cost: " + care.getCost());

     //   int resourceId = fragment.getResources().getIdentifier(care.getImage() , "drawable" , fragment.getActivity().getPackageName());
     //   holder.polish_image.setImageResource(resourceId);
    }

    class CaresHolder extends RecyclerView.ViewHolder{
     //   private AppCompatImageView polish_image;
        private MaterialTextView list_LBL_care_name;
        private MaterialTextView  list_LBL_care_explanation;
        private MaterialTextView list_LBL_care_cost;
        private MaterialTextView list_LBL_care_time;

        public CaresHolder(View itemView) {
            super(itemView);
         //   polish_image = itemView.findViewById(R.id.polish_image);
            list_LBL_care_name = itemView.findViewById(R.id.list_LBL_care_name);
            list_LBL_care_explanation = itemView.findViewById(R.id.list_LBL_care_explanation);
            list_LBL_care_cost = itemView.findViewById(R.id.list_LBL_care_cost);
            list_LBL_care_time = itemView.findViewById(R.id.list_LBL_care_time);
        }
    }
}
