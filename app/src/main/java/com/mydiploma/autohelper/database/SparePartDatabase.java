package com.mydiploma.autohelper.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mydiploma.autohelper.dao.SparePartDao;
import com.mydiploma.autohelper.entity.SparePart;

@Database(entities = {SparePart.class}, version = 1)
public abstract class SparePartDatabase extends RoomDatabase {
    public abstract SparePartDao sparePartDao();

}
