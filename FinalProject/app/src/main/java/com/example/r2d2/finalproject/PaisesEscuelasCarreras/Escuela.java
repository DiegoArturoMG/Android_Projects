package com.example.r2d2.finalproject.PaisesEscuelasCarreras;

public class Escuela {

    public String nombreEscuela;

    public Escuela(String nombreEscuela) {
        this.nombreEscuela = nombreEscuela;
    }

    public String getNombreEscuela() {
        return nombreEscuela;
    }

    public void setNombreEscuela(String nombreEscuela) {
        this.nombreEscuela = nombreEscuela;
    }

    @Override
    public String toString() {
        return "Escuela{" +
                "nombreEscuela='" + nombreEscuela + '\'' +
                '}';
    }

}
