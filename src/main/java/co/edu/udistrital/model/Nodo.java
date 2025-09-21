/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.model;

import java.util.Objects;

/**
 *
 * @author Andres Felipe Tovar
 */
public class Nodo<T> {

    private T dato;
    private Nodo<T> anterior;
    private Nodo<T> siguiente;

    public Nodo(T dato, Nodo<T> anterior, Nodo<T> siguiente) {
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public Nodo() {
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<T> anterior) {
        this.anterior = anterior;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return Objects.toString(dato, "null");
    }

}
