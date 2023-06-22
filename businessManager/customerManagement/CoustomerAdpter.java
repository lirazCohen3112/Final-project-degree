package com.example.thefinalproject.businessManager.customerManagement;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class CoustomerAdpter extends RecyclerView.Adapter<CoustomerAdpter.CoustomerHolder>{

    private Fragment fragment;
    private ArrayList<Coustumer> coustomers;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CoustomerAdpter (Fragment fragment , ArrayList<Coustumer> coustomers){
        this.coustomers = coustomers;
        this.fragment = fragment;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return coustomers == null ? 0 : coustomers.size();
    }

    @Override
    public CoustomerAdpter.CoustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customers, parent, false);
        CoustomerAdpter.CoustomerHolder coustomerHolder = new CoustomerAdpter.CoustomerHolder(view);
        return coustomerHolder;
    }

    @Override
    public void onBindViewHolder(final CoustomerAdpter.CoustomerHolder holder, @SuppressLint("RecyclerView") final int position) {

        Coustumer coustomer = coustomers.get(position);

        holder.list_LBL_name.setText("" + coustomer.getName());
        holder.list_LBL_phoneNumber.setText("" + coustomer.getPhoneNum());
        holder.list_LBL_email.setText("" + coustomer.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    class CoustomerHolder extends RecyclerView.ViewHolder{
        private MaterialTextView list_LBL_name;

        private MaterialTextView  list_LBL_phoneNumber;

        private MaterialTextView list_LBL_email;

        public CoustomerHolder(View itemView) {
            super(itemView);
            list_LBL_name = itemView.findViewById(R.id.list_LBL_name);
            list_LBL_phoneNumber = itemView.findViewById(R.id.list_LBL_phoneNumber);
            list_LBL_email = itemView.findViewById(R.id.list_LBL_email);

        }
    }
}


