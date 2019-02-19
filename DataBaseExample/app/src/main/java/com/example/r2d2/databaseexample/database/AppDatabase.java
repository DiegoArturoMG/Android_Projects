package com.example.r2d2.databaseexample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.r2d2.databaseexample.MainActivity;
import com.example.r2d2.databaseexample.dao.DocumentDao;
import com.example.r2d2.databaseexample.dao.UserDao;
import com.example.r2d2.databaseexample.entity.DocumentEntity;
import com.example.r2d2.databaseexample.entity.UserEntity;

@Database(entities = {UserEntity.class, DocumentEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract UserDao userDao();

    public abstract DocumentDao documentDao();

    private static AppDatabase instance;

        public static AppDatabase sharedInstance() {
            if (instance == null) {

                instance = Room.databaseBuilder(
                        MainActivity.instance.getApplicationContext(),
                        AppDatabase.class,
                        "database-name").allowMainThreadQueries().build();
            }

            return instance;
        }
    }
