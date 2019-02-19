package com.example.r2d2.practicagps.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="point")
public class Point implements Serializable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int id;
    //private int uid; //UserID

    @ColumnInfo(name = "Latitud")
    private double latitud;

    @ColumnInfo(name = "Longitud")
    private double longitud;

    public Point() {
    }

    public Point(int id, double latitud, double longitud) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Point(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                '}';
    }
}
