package com.mydiploma.autohelper.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.entity.BusinessCard;
import com.mydiploma.autohelper.entity.Car;

import java.util.List;

public interface BusinessCardDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)

    @Query(Constants.SQL_CAR_TITLE_INFO)
    List<BusinessCard> getCarTitle();

    @Query(Constants.SQL_CAR_ALL_INFO)
    List<BusinessCard> getAll();

    @Query(Constants.SQL_CAR_BY_ID)
    BusinessCard getById(long id);

    @Insert
    void insert(BusinessCard businessCard);

    @Update
    void update(BusinessCard businessCard);

    @Delete
    void delete(BusinessCard businessCard);

}
