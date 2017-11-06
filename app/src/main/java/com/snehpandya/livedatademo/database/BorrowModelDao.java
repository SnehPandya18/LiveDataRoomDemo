package com.snehpandya.livedatademo.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by sneh.pandya on 03/11/17.
 */

@Dao
public interface BorrowModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBorrow(BorrowModel borrowModel);

    //Query for whole table
    @Query("SELECT * FROM " + DbConfig.TABLE_NAME)
    LiveData<List<BorrowModel>> getAllBorrowItems();

    //Query to delete latest entry
    @Query("DELETE FROM " + DbConfig.TABLE_NAME + " WHERE ID = (SELECT MAX(id) FROM " + DbConfig.TABLE_NAME + ")")
    void deleteSingleBorrow();

    //Query to delete all entries
    @Query("DELETE FROM " + DbConfig.TABLE_NAME)
    void deleteAllBorrow();

    //Query to update latest entry
    @Query("UPDATE " + DbConfig.TABLE_NAME + " SET " + DbConfig.COLUMN_PERSON_NAME + " = :personName WHERE ID = (SELECT MAX(id) FROM " + DbConfig.TABLE_NAME + ")")
    void updateBorrow(String personName);

    //Query to update all entries
    @Query("UPDATE " + DbConfig.TABLE_NAME + " SET " + DbConfig.COLUMN_PERSON_NAME + " = :personName")
    void updateAllBorrow(String personName);
}
