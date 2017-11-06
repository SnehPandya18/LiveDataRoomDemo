package com.snehpandya.livedatademo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.snehpandya.livedatademo.database.AppDatabase;
import com.snehpandya.livedatademo.database.BorrowModel;

import java.util.List;

/**
 * Created by sneh.pandya on 03/11/17.
 */

public class NameViewModel extends AndroidViewModel {

    private final LiveData<List<BorrowModel>> itemAndPersonList;
    private MutableLiveData<String> mCurrentName;
    private AppDatabase mAppDatabase;

    public NameViewModel(@NonNull Application application) {
        super(application);

        mAppDatabase = AppDatabase.getInstance(this.getApplication());

        itemAndPersonList = mAppDatabase.mBorrowModelDao().getAllBorrowItems();
    }

    public LiveData<List<BorrowModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void addItem() {
        mAppDatabase.mBorrowModelDao().addBorrow(new BorrowModel("Google", "Jon Skeet"));
    }

    public void updateItem(String string) {
        mAppDatabase.mBorrowModelDao().updateBorrow(string);
    }

    public void updateMultipleItems(String string) {
        mAppDatabase.mBorrowModelDao().updateAllBorrow(string);
    }

    public void deleteAllItems() {
        mAppDatabase.mBorrowModelDao().deleteAllBorrow();
    }

    public void deleteItem() {
        mAppDatabase.mBorrowModelDao().deleteSingleBorrow();
    }

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }
}
