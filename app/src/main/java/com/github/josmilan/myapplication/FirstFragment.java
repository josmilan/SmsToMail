package com.github.josmilan.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.josmilan.myapplication.adapter.EmailListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment {

    EmailListAdapter adapter;

    FloatingActionButton fab;
    RecyclerView rvEmailList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initListener();

        setRecyclerView();
    }

    private void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initView(View view) {
        fab = view.findViewById(R.id.fab);
        rvEmailList = view.findViewById(R.id.rvEmailList);
    }

    private void setRecyclerView() {
        adapter = new EmailListAdapter();
        rvEmailList.setAdapter(adapter);
    }
}