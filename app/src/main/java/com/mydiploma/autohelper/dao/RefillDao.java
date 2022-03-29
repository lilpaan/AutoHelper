package com.mydiploma.autohelper.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.entity.Car;
import com.mydiploma.autohelper.entity.Refill;

import java.util.List;

@Dao
public interface RefillDao {

    @Query(Constants.SQL_REFILL_TITLE_INFO)
    List<Refill> getRefillName();

    @Query(Constants.SQL_REFILL_BY_ID)
    Refill getById(long id);

    @Insert
    void insert(Refill refill);

    @Update
    void update(Refill refill);

    @Delete
    void delete(Refill refill);
}
