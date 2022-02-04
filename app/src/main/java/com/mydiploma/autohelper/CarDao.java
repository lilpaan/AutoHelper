package com.mydiploma.autohelper;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarDao {

        @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)

        @Query("SELECT id, maker, model, engineVolume, productionYear, fuelAmount FROM car")
        List<Car> getCarTitle();


        @Query("SELECT * FROM car")
        List<Car> getAll();

        @Query("SELECT * FROM car WHERE id = :id")
        Car getById(long id);

        @Insert
        void insert(Car car);

        @Update
        void update(Car car);

        @Delete
        void delete(Car car);

    }
