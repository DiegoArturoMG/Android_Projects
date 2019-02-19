package com.example.r2d2.finalproject.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.r2d2.finalproject.Entity.Student;
import com.example.r2d2.finalproject.Entity.Student_Message;

@Dao
public interface Student_Message_Dao {

    @Insert
    void insert(Student_Message student_message);

}
