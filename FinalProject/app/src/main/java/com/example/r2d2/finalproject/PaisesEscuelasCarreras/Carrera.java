package com.example.r2d2.finalproject.PaisesEscuelasCarreras;

public class Carrera {

    public String nombreCarrera;

    public Carrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                '}';
    }

}
