package com.snehpandya.livedatademo.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snehpandya.livedatademo.R;
import com.snehpandya.livedatademo.database.BorrowModel;

import java.util.List;

/**
 * Created by sneh.pandya on 03/11/17.
 */

public class RecyclerViewAdapter extends Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<BorrowModel> mBorrowModels;

    public RecyclerViewAdapter(List<BorrowModel> borrowModels) {
        mBorrowModels = borrowModels;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        BorrowModel borrowModel = mBorrowModels.get(position);
        holder.itemTextView.setText(borrowModel.getItemName());
        holder.nameTextView.setText(borrowModel.getPersonName());
    }

    @Override
    public int getItemCount() {
        return mBorrowModels.size();
    }

    public void addItems(List<BorrowModel> borrowModels) {
        this.mBorrowModels = borrowModels;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTextView;
        private TextView nameTextView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemTextView = (TextView) itemView.findViewById(R.id.itemTextView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }
    }
}
