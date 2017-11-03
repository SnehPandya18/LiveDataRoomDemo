package com.snehpandya.livedatademo.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by sneh.pandya on 03/11/17.
 */

@Entity
public class BorrowModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String itemName;

    private String personName;

    public BorrowModel(String itemName, String personName) {
        this.itemName = itemName;
        this.personName = personName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
