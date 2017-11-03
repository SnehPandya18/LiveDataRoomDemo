package com.snehpandya.livedatademo.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by sneh.pandya on 03/11/17.
 */

@Dao
public interface BorrowModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBorrow(BorrowModel borrowModel);

    @Query("SELECT * FROM " + DbConfig.TABLE_NAME)
    LiveData<List<BorrowModel>> getAllBorrowItems();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateBorrow(BorrowModel borrowModel);

    @Delete
    void deleteBorrow(BorrowModel borrowModel);
}
