package com.example.thefinalproject.businessManager.financialManagement;

import android.accounts.Account;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thefinalproject.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class MovementAdapter extends RecyclerView.Adapter<MovementAdapter.MovementHolder> {

    private Fragment fragment;
    private ArrayList<Movement> movements;

    public MovementAdapter(Fragment fragment , ArrayList<Movement> movements){
        this.movements = movements;
        this.fragment = fragment;
    }

    @Override
    public int getItemCount() {
        return movements == null ? 0 : movements.size();
    }

    @Override
    public MovementAdapter.MovementHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movement, parent, false);
        MovementAdapter.MovementHolder movementHolder = new MovementAdapter.MovementHolder(view);
        return movementHolder;
    }

    @Override
    public void onBindViewHolder(final MovementHolder holder, final int position) {

        Movement movement = movements.get(position);

        holder.list_LBL_typeMovement.setText(""+ movement.getTypeMovement());
        holder.list_LBL_size.setText(""+ movement.getSize());
        holder.list_LBL_description.setText(""+ movement.getDescription());

    }

    class MovementHolder extends RecyclerView.ViewHolder{
        private MaterialTextView account_EDT_balance;
        private MaterialTextView list_LBL_typeMovement;
        private MaterialTextView list_LBL_size;
        private MaterialTextView list_LBL_description;

        public MovementHolder(View itemView) {
            super(itemView);
            account_EDT_balance = itemView.findViewById(R.id. account_EDT_balance);
            list_LBL_typeMovement = itemView.findViewById(R.id.list_LBL_typeMovement);
            list_LBL_size = itemView.findViewById(R.id.list_LBL_size);
            list_LBL_description = itemView.findViewById(R.id.list_LBL_description);
        }
    }
}
