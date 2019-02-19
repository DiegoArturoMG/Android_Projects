package com.example.r2d2.finalproject.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.r2d2.finalproject.Dao.Message_Dao;
import com.example.r2d2.finalproject.Dao.Student_Dao;
import com.example.r2d2.finalproject.Dao.Student_Message_Dao;
import com.example.r2d2.finalproject.Entity.Message;
import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.Entity.Student_Message;
import com.example.r2d2.finalproject.MainActivity;

@Database(entities = {Student.class, Message.class, Student_Message.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract Student_Dao studentDao();

    public abstract Message_Dao messageDao();

    public abstract Student_Message_Dao studentmessageDao();

    private static AppDatabase instance;

    public static AppDatabase sharedInstance() {
        if (instance == null) {

            instance = Room.databaseBuilder(
                    MainActivity.instance.getApplicationContext(),
                    AppDatabase.class,
                    "AppDatabase-name").allowMainThreadQueries().build();
        }

        return instance;
    }

}
