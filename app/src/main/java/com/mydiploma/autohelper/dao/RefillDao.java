package com.mydiploma.autohelper.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.mydiploma.autohelper.entity.Refill;

@Dao
public interface RefillDao {
    @Insert
    void insert(Refill refill);

    @Update
    void update(Refill refill);

    @Delete
    void delete(Refill refill);
}
