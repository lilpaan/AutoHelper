package com.mydiploma.autohelper.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.entity.BusinessCard;

import java.util.List;

@Dao
public interface BusinessCardDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)

    @Query(Constants.SQL_BUSINESS_CARD_TITLE_INFO)
    List<BusinessCard> getBusinessCardTitle();

    @Query(Constants.SQL_BUSINESS_CARD_ALL_INFO)
    List<BusinessCard> getAll();

    @Query(Constants.SQL_BUSINESS_CARD_BY_ID)
    BusinessCard getById(long id);

    @Insert
    void insert(BusinessCard businessCard);

    @Update
    void update(BusinessCard businessCard);

    @Delete
    void delete(BusinessCard businessCard);

}
