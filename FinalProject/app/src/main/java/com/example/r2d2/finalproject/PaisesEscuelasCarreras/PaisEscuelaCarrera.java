package com.example.r2d2.finalproject.PaisesEscuelasCarreras;

import java.util.List;

public class PaisEscuelaCarrera {

    public List<String> pais;
    public List<String> escuela;
    public List<String> carrera;

    public PaisEscuelaCarrera(List pais, List<String> escuela, List<String> carrera) {
        this.pais = pais;
        this.escuela = escuela;
        this.carrera = carrera;
    }

    public List<String> getPais() {
        return pais;
    }

    public void setPais(List<String> pais) {
        this.pais = pais;
    }

    public List<String> getEscuela() {
        return escuela;
    }

    public void setEscuela(List<String> escuela) {
        this.escuela = escuela;
    }

    public List<String> getCarrera() {
        return carrera;
    }

    public void setCarrera(List<String> carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "PaisEscuelaCarrera{" +
                "pais='" + pais + '\'' +
                ", escuela=" + escuela +
                ", carrera=" + carrera +
                '}';
    }

}
