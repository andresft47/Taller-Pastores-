/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.model;

import java.util.Random;

/**
 *
 * @author Andres Felipe Tovar
 */
public class Pastor {

    public String nombre;
    public int feligreses;
    public double dinero;
    public String clero;

    // Cada pastor solo puede tener uno de los cuatro cleros validos
    private static final String[] cleros = { "Católico", "Protestante", "Ortodoxo", "Anglicano" };

    private static Random random = new Random();

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

    /**
     * Retorna un clero aleatorio del array de cleros
     * 
     * @return Un clero aleatorio
     */
    public static String obtenerCleroAleatorio() {
        int indiceAleatorio = random.nextInt(cleros.length);
        return cleros[indiceAleatorio];
    }

    @Override
    public String toString() {
        return "Pastor{" + "nombre=" + nombre + ", feligreses=" + feligreses + ", dinero=" + dinero + ", clero=" + clero
                + '}';
    }

}
