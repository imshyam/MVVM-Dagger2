package com.example.shyam.dagger2sample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.shyam.dagger2sample.R;
import com.example.shyam.dagger2sample.model.UserDetails;

public class UserDetailsViewHolder extends RecyclerView.ViewHolder {

    private View view;

    UserDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }

    public void bindItems(UserDetails userDetails) {
        TextView name = view.findViewById(R.id.name);
        TextView email = view.findViewById(R.id.email);
        TextView phone = view.findViewById(R.id.phone);
        name.setText(userDetails.getName());
        email.setText(userDetails.getEmail());
        phone.setText(userDetails.getPhone());
    }
}
