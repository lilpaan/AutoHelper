package com.mydiploma.autohelper.util;

import com.mydiploma.autohelper.dao.SparePartDao;
import com.mydiploma.autohelper.database.CarDatabase;
import com.mydiploma.autohelper.entity.SparePart;

public class SparePartUtil {
    public static boolean addSparePart(CarDatabase carDatabase, SparePart sparePart) {
        boolean isSparePartAdded;
        // sparePartSaveThread to save sparePart into db
        SparePartDao sparePartDao = carDatabase.sparePartDao();
        Thread sparePartSaveThread = new Thread() {
            @Override
            public void run() {
                sparePartDao.insert(sparePart);
            }
        };
        try {
            sparePartSaveThread.start();
            isSparePartAdded = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSparePartAdded = false;
        }
        return isSparePartAdded;
    }

    public static boolean deleteSparePart(CarDatabase carDatabase, Long id) {
        boolean isSparePartDeleted;
        Thread deleteSparePartThread = new Thread(){
            @Override
            public void run() {
                SparePartDao sparePartDao = carDatabase.sparePartDao();
                sparePartDao.deleteSparePart(id);
            }
        };
        deleteSparePartThread.start();
        try {
            deleteSparePartThread.join();
            isSparePartDeleted = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            isSparePartDeleted = false;
        }
        return isSparePartDeleted;
    }
}
