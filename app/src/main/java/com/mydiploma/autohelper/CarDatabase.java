package com.mydiploma.autohelper;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Car.class}, version = 2)
public abstract class CarDatabase extends RoomDatabase {
    public abstract CarDao carDao();
}