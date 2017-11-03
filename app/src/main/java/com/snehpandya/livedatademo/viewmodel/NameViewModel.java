package com.snehpandya.livedatademo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
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

    public void deleteItem(BorrowModel borrowModel) {
        new deleteAsyncTask(mAppDatabase).execute(borrowModel);
    }

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }

    private static class deleteAsyncTask extends AsyncTask<BorrowModel, Void, Void> {

        private AppDatabase mAppDatabase;

        deleteAsyncTask(AppDatabase appDatabase) {
            mAppDatabase = appDatabase;
        }

        @Override
        protected Void doInBackground(final BorrowModel... params) {
            mAppDatabase.mBorrowModelDao().deleteBorrow(params[0]);
            return null;
        }
    }
}
