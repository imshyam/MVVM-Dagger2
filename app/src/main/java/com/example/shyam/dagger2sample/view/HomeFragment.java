package com.example.shyam.dagger2sample.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shyam.dagger2sample.adapter.UserDetailsAdapter;
import com.example.shyam.dagger2sample.viewmodel.MyViewModel;
import com.example.shyam.dagger2sample.R;
import com.example.shyam.dagger2sample.model.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    UserDetailsAdapter adapter;
    ItemClickInteractionListener itemClickInteractionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof ItemClickInteractionListener) {
            itemClickInteractionListener = (ItemClickInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.user_list_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final MyViewModel viewModel =
                ViewModelProviders.of(this).get(MyViewModel.class);

        observeViewModel(viewModel);
        adapter = new UserDetailsAdapter(new ArrayList<>(), itemClickInteractionListener);
        recyclerView.setAdapter(adapter);

        return view;
    }


    private void observeViewModel(MyViewModel viewModel) {
        viewModel.getUserDetailsLiveDataObservable().observe(this, userList -> {
            List<UserDetails> userDetails = userList.getList();
            if(userDetails.size() > 0) {
                adapter.updateData(userDetails);
            }
        });
    }
}
