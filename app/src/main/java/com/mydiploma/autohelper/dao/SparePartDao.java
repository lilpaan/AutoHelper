package com.mydiploma.autohelper.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.entity.SparePart;

import java.util.List;

@Dao
public interface SparePartDao {

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)

    @Query(Constants.SQL_SPARE_PART_TITLE_INFO)
    List<SparePart> getSparePartTitle(long carID);

    @Query(Constants.SQL_SPARE_PART_ALL_INFO)
    List<SparePart> getAll();

    @Query(Constants.SQL_SPARE_PART_BY_ID)
    SparePart getById(long id);

    @Query(Constants.SQL_DELETE_SPARE_PART)
    void deleteSparePart(long id);

    @Insert
    void insert(SparePart sparePart);

    @Update
    void update(SparePart sparePart);

    @Delete
    void delete(SparePart sparePart);
}
