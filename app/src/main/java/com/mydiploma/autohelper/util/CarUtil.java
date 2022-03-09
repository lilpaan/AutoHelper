package com.mydiploma.autohelper.util;

import androidx.appcompat.app.AppCompatActivity;

import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.Car;

public class CarUtil extends AppCompatActivity {
    static CarDao carDao;
    private static Car[] cars;
    static int carCount;

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

    public static Car[] showUserCar(CarDatabase carDatabase) {
        // thread to place car title in item
        Thread carGetTitleThread = new Thread() {
            @Override
            public void run() {
                carDao = carDatabase.carDao();
                cars = carDao.getCarTitle().toArray(new Car[0]);
            }
        };
        carGetTitleThread.start();
        try {
            carGetTitleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public static int getCarCount(CarDatabase carDatabase) {
        Thread getCarCount = new Thread() {
            @Override
            public void run() {
                carDao = carDatabase.carDao();
                carCount = carDao.getCarCount();
            }
        };
        getCarCount.start();
        try {
            getCarCount.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return carCount;
    }

}