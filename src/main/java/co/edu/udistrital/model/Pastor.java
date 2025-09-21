/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.model;

/**
 *
 * @author Andres Felipe Tovar
 */
public class Pastor {

    public String nombre;
    public int feligreses;
    public double dinero;
    public String clero;

    public Pastor() {
        this.nombre = "";
        this.feligreses = 0;
        this.dinero = 0;
        this.clero = "";
    }

    public Pastor(String nombre, int feligreses, double dinero, String clero) {
        this.nombre = nombre;
        this.feligreses = feligreses;
        this.dinero = dinero;
        this.clero = clero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFeligreses() {
        return feligreses;
    }

    public void setFeligreses(int feligreses) {
        this.feligreses = feligreses;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public String getClero() {
        return clero;
    }

    public void setClero(String clero) {
        this.clero = clero;
    }

    @Override
    public String toString() {
        return "Pastor{" + "nombre=" + nombre + ", feligreses=" + feligreses + ", dinero=" + dinero + ", clero=" + clero + '}';
    }

}
