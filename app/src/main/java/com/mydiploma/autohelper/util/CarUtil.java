package com.mydiploma.autohelper.util;

import androidx.appcompat.app.AppCompatActivity;

import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.Car;

public class CarUtil extends AppCompatActivity {
    public static boolean addNewCar(Car car, CarDatabase carDatabase) {
        boolean isCarAdded;
        // carSaveThread to save car into db
        CarDao carDao = carDatabase.carDao();
        Thread carSaveThread = new Thread() {
            @Override
            public void run() {
                carDao.insert(car);
            }
        };
        try {
            carSaveThread.start();
            isCarAdded = true;
        } catch (Exception e) {
            e.printStackTrace();
            isCarAdded = false;
        }
        return isCarAdded;
    }

    public static boolean deleteCar(CarDatabase carDatabase, Car car) {
        boolean isCarDeleted;
        boolean isSparePartDeleted;
        Thread deleteCarThread = new Thread() {
            @Override
            public void run() {
                CarDao carDao = carDatabase.carDao();
                carDao.delete(car);
            }
        };
        deleteCarThread.start();
        try {
            deleteCarThread.join();
            isSparePartDeleted = SparePartUtil.deleteSparePart(carDatabase, car.getId());
            isCarDeleted = isSparePartDeleted;
        } catch (InterruptedException e) {
            e.printStackTrace();
            isCarDeleted = false;
        }
        return isCarDeleted;
    }

}