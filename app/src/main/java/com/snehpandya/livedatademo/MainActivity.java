package com.snehpandya.livedatademo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.snehpandya.livedatademo.adapter.RecyclerViewAdapter;
import com.snehpandya.livedatademo.database.BorrowModel;
import com.snehpandya.livedatademo.databinding.ActivityMainBinding;
import com.snehpandya.livedatademo.viewmodel.NameViewModel;

import java.util.ArrayList;
import java.util.List;

    // https://android.jlelse.eu/android-architecture-components-room-livedata-and-viewmodel-fca5da39e26b

    //TODO: Implement AddItemActivity

public class MainActivity extends AppCompatActivity {

    private NameViewModel mNameViewModel;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mRecyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<BorrowModel>());
        mBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerview.setAdapter(mRecyclerViewAdapter);

        mNameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);

        mNameViewModel.getItemAndPersonList().observe(this, new Observer<List<BorrowModel>>() {
            @Override
            public void onChanged(@Nullable List<BorrowModel> borrowModels) {
                mRecyclerViewAdapter.addItems(borrowModels);
            }
        });

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mBinding.textview.setText(s);
            }
        };

        mNameViewModel.getCurrentName().observe(this, nameObserver);

        mBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = "Jon Skeet";
                mNameViewModel.getCurrentName().setValue(newName);
            }
        });
    }
}
