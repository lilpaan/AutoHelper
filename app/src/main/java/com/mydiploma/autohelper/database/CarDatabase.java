package com.mydiploma.autohelper.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.dao.SparePartDao;
import com.mydiploma.autohelper.entity.Car;
import com.mydiploma.autohelper.entity.SparePart;

@Database(entities = {Car.class, SparePart.class}, version = 5)
public abstract class CarDatabase extends RoomDatabase {
    public abstract CarDao carDao();
    public abstract SparePartDao sparePartDao();
}
