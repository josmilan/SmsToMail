package com.github.josmilan.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.josmilan.myapplication.R;
import com.github.josmilan.myapplication.adapter.EmailListAdapter;
import com.github.josmilan.myapplication.fragment.dailog.AddEmailDialogFragment;
import com.github.josmilan.myapplication.utils.PreferenceUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FirstFragment extends Fragment implements AddEmailDialogFragment.OnEmailAddedListener {

    EmailListAdapter adapter;

    FloatingActionButton fab;
    RecyclerView rvEmailList;
    String[] idList = {};

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
        initValues();

    }

    private void initValues() {
        String[] i = PreferenceUtils.getEmailIdList();

        if (i != null && i.length > 0 && i[0] != "") {
            idList = PreferenceUtils.getEmailIdList();
        } else {
            AddEmailDialogFragment editNameDialog = new AddEmailDialogFragment();
            editNameDialog.show(getChildFragmentManager(), "fragment_add_email");
        }
        setRecyclerView();
    }

    private void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEmailDialogFragment editNameDialog = new AddEmailDialogFragment();
                editNameDialog.show(getChildFragmentManager(), "fragment_add_email");
            }
        });
    }

    private void initView(View view) {
        fab = view.findViewById(R.id.fab);
        rvEmailList = view.findViewById(R.id.rvEmailList);
    }

    private void setRecyclerView() {
        adapter = new EmailListAdapter(idList);
        rvEmailList.setAdapter(adapter);
    }

    @Override
    public void onEmailAdded() {
        idList = PreferenceUtils.getEmailIdList();
        adapter.setList(idList);
    }
}