package com.example.thefinalproject.businessManager.ManagementCareTypes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class CareAdapter  extends RecyclerView.Adapter<CareAdapter.CaresHolder>{
    private Fragment fragment;
    private ArrayList<Care> cares;

    public CareAdapter(Fragment fragment , ArrayList<Care> cares){
        this.cares=cares;
        this.fragment = fragment;
    }

    @Override
    public int getItemCount() {
        return cares == null ? 0 : cares.size();
    }

    @Override
    public CareAdapter.CaresHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_list_care, parent, false);
        CareAdapter.CaresHolder caresHolder = new CareAdapter.CaresHolder(view);
        return caresHolder;
    }

    @Override
    public void onBindViewHolder(final CareAdapter.CaresHolder holder, final int position) {

        Care care = cares.get(position);

        holder.list_LBL_care_name.setText("" + care.getName());
        holder.list_LBL_care_explanation.setText("" + care.getExplanation());
        holder.list_LBL_care_cost.setText(String.valueOf(care.getCost()));
    }

    class CaresHolder extends RecyclerView.ViewHolder{
        private MaterialTextView list_LBL_care_name;
        private MaterialTextView  list_LBL_care_explanation;
        private MaterialTextView list_LBL_care_cost;

        public CaresHolder(View itemView) {
            super(itemView);
            list_LBL_care_name = itemView.findViewById(R.id.list_LBL_care_name);
            list_LBL_care_explanation = itemView.findViewById(R.id.list_LBL_care_explanation);
            list_LBL_care_cost = itemView.findViewById(R.id.list_LBL_care_cost);
        }
    }
}
