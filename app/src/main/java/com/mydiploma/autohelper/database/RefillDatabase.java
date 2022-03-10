package com.mydiploma.autohelper.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mydiploma.autohelper.dao.RefillDao;
import com.mydiploma.autohelper.entity.Refill;

@Database(entities = {Refill.class}, version = 1)
public abstract class RefillDatabase extends RoomDatabase {
    public abstract RefillDao refillDao();
}