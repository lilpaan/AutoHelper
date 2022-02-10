package com.mydiploma.autohelper.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mydiploma.autohelper.dao.BusinessCardDao;
import com.mydiploma.autohelper.entity.BusinessCard;

@Database(entities = {BusinessCard.class}, version = 1)
public abstract class BusinessCardDatabase extends RoomDatabase {
    public abstract BusinessCardDao businessCardDao();
}
