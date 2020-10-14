package com.github.josmilan.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.josmilan.myapplication.R;

public class EmailListAdapter extends RecyclerView.Adapter<EmailListAdapter.ViewHolder> {

    String[] mIdList = {};
    public EmailListAdapter(String[] idList){
        mIdList = idList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_email, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvEmail.setText(mIdList[position]);
    }

    @Override
    public int getItemCount() {
        return mIdList.length;
    }

    public void setList(String[] idList) {
        mIdList = idList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
