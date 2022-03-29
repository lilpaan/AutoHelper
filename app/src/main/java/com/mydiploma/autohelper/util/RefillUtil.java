package com.mydiploma.autohelper.util;

import androidx.appcompat.app.AppCompatActivity;

import com.mydiploma.autohelper.dao.RefillDao;
import com.mydiploma.autohelper.database.RefillDatabase;
import com.mydiploma.autohelper.entity.Refill;

public class RefillUtil extends AppCompatActivity {
    static RefillDao refillDao;
    private static Refill[] refills;
    static boolean isRefillDeleted;

    public static boolean deleteRefill(RefillDatabase refillDatabase, Refill refill) {

        Thread deleteRefillThread = new Thread() {
            @Override
            public void run() {
                try {
                    RefillDao refillDao = refillDatabase.refillDao();
                    refillDao.delete(refill);
                    isRefillDeleted = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    isRefillDeleted = false;
                }
            }
        };
        return isRefillDeleted;
    }

    public static Refill[] showRefills(RefillDatabase refillDatabase) {
        // thread to place refill title in item
        Thread refillGetNameThread = new Thread() {
            @Override
            public void run() {
                refillDao = refillDatabase.refillDao();
                refills = refillDao.getRefillName().toArray(new Refill[0]);
            }
        };
        refillGetNameThread.start();
        try {
            refillGetNameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return refills;
    }

}