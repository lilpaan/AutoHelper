package com.mydiploma.autohelper.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.mydiploma.autohelper.Constants;
import com.mydiploma.autohelper.adapter.CarAdapter;
import com.mydiploma.autohelper.dao.CarDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.Car;

public class CarUtil extends AppCompatActivity {
    static CarDao carDao;
    private static Car[] cars;

    public static boolean addNewCar(Car car, CarDatabase carDatabase) {
        boolean isCarAdded;

        // carSaveThread to save car into db
        carDao = carDatabase.carDao();
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

}