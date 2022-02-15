package com.mydiploma.autohelper.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mydiploma.autohelper.dao.DiscountCardDao;
import com.mydiploma.autohelper.entity.DiscountCard;

@Database(entities = {DiscountCard.class}, version = 2)
    public abstract class DiscountCardDatabase extends RoomDatabase {
        public abstract DiscountCardDao discountCardDao();
    }
