package com.mydiploma.autohelper.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.entity.DiscountCard;

import java.util.List;

@Dao
public interface DiscountCardDao {
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)

    @Query(Constants.SQL_DISCOUNT_CARD_TITLE_INFO)
    List<DiscountCard> getDiscountCardTitle();

    @Query(Constants.SQL_DISCOUNT_CARD_ALL_INFO)
    List<DiscountCard> getAll();

    @Query(Constants.SQL_DISCOUNT_CARD_BY_ID)
    DiscountCard getById(long id);

    @Insert
    void insert(DiscountCard DiscountCard);

    @Update
    void update(DiscountCard DiscountCard);

    @Delete
    void delete(DiscountCard DiscountCard);

}
