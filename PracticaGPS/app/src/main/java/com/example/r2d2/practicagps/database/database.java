package com.example.r2d2.practicagps.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.r2d2.practicagps.Dao.PointDao;
import com.example.r2d2.practicagps.Entity.Point;
import com.example.r2d2.practicagps.MainActivity;

@Database(entities = {Point.class}, version = 1)
public abstract class database extends RoomDatabase {

    public abstract PointDao pointDao();

    private static database instance;

    public static database sharedInstance() {
        if (instance == null) {

            instance = Room.databaseBuilder(
                    MainActivity.instance.getApplicationContext(),
                    database.class,
                    "database-name").allowMainThreadQueries().build();
        }

        return instance;
    }
}
