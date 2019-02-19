package com.example.r2d2.practicagps.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.r2d2.practicagps.Entity.Point;

import java.util.List;

@Dao
public interface PointDao {

    @Query("SELECT * FROM point")
    List<Point> getAll();

    @Insert
    void insert(Point user);

}
