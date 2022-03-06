package com.mydiploma.autohelper.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.entity.Car;

import java.util.List;

@Dao
public interface CarDao {


        @Query(Constants.SQL_CAR_TITLE_INFO)
        List<Car> getCarTitle();

        @Query(Constants.SQL_GET_CAR_COUNT)
        int getCarCount();

        @Query(Constants.SQL_CAR_ALL_INFO)
        List<Car> getAll();

        @Query(Constants.SQL_CAR_BY_ID)
        Car getById(long id);

        @Insert
        void insert(Car car);

        @Update
        void update(Car car);

        @Delete
        void delete(Car car);

    }
