package com.example.r2d2.finalproject.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.r2d2.finalproject.Entity.Student;

@Dao
public interface Student_Dao {

    @Query("SELECT * FROM student WHERE sid IN (:studentId)")
    Student studentById(String studentId);

    @Query("SELECT * FROM student WHERE city LIKE (:city) AND school LIKE (:school) AND career LIKE (:career)")
    Student[] studentByCitySchoolCareer(String city, String school, String career);

    @Insert
    void insert(Student student);

    @Update
    void update(Student student);

}
