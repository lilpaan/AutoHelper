package com.mydiploma.autohelper.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.entity.Car;

@Database(entities = {Car.class}, version = 2)
public abstract class CarDatabase extends RoomDatabase {
    public abstract CarDao carDao();
}
