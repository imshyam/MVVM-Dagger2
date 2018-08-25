package com.example.shyam.dagger2sample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shyam.dagger2sample.R;
import com.example.shyam.dagger2sample.model.UserDetails;
import com.example.shyam.dagger2sample.view.ItemClickInteractionListener;

import java.util.List;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsViewHolder>{

    private List<UserDetails> userDetailsList;
    private ItemClickInteractionListener itemClickInteractionListener;

    public UserDetailsAdapter(List<UserDetails> userDetails, ItemClickInteractionListener itemClickInteractionListener) {
        this.userDetailsList = userDetails;
        this.itemClickInteractionListener = itemClickInteractionListener;
    }

    @NonNull
    @Override
    public UserDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        return new UserDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailsViewHolder userDetailsViewHolder, int i) {
        UserDetails userDetails = this.userDetailsList.get(i);

        userDetailsViewHolder.bindItems(userDetails);
        userDetailsViewHolder.itemView.findViewById(R.id.user_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickInteractionListener.onUserDetailsClick(String.valueOf(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.userDetailsList.size();
    }

    public void updateData(List<UserDetails> userDetails) {
        this.userDetailsList = userDetails;
        notifyDataSetChanged();
    }
}
