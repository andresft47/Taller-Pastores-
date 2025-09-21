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
public class ListaCircularDoble<T> {

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamanio;

    public ListaCircularDoble() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null && cola == null;
    }

    public void agregarInicio(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento, this.cola, this.cabeza); // Ant: cola, Sig: cabeza antigua
        if (estaVacia()) {
            // Si es el primer nodo, es cabeza y cola
            this.cabeza = nuevoNodo;
            this.cola = nuevoNodo;

            // como es la cabeza su siguiente y anterior elemento es el mismo o la cola
            this.cabeza.setAnterior(this.cola);
            this.cabeza.setSiguiente(this.cola);
        } else {
            // La cabeza antigua ahora apunta hacia atrás al nuevo nodo
            this.cabeza.setAnterior(nuevoNodo);

            // Ahora la cola apunta hacia al nuevo nodo que es la cabeza
            this.cola.setSiguiente(nuevoNodo);

            // El nuevo nodo es la nueva cabeza
            this.cabeza = nuevoNodo;
        }

        // Aumenta el tamaño de la lista
        this.tamanio++;

    }

    public void agregarFinal(T elemento) {

        if (estaVacia()) {
            agregarInicio(elemento);
            return;

        }

        // El nuevo nodo apunta hacia atrás a la cola antigua, y su siguiente es la
        // cabeza
        Nodo<T> nuevoNodo = new Nodo<>(elemento, this.cola, this.cabeza);

        // La cola antigua ahora apunta hacia adelante al nuevo nodo
        this.cola.setSiguiente(nuevoNodo);

        // La cabeza apunta hacia atras al nuevo nodo que es la cola
        this.cabeza.setAnterior(nuevoNodo);

        // El nuevo nodo se convierte en la nueva cola
        this.cola = nuevoNodo;

        // Aumenta el tamaño de la lista
        this.tamanio++;
    }

    private Nodo<T> buscarNodo(T datoBusqueda) {
        Nodo<T> actual = this.cabeza;
        /*
         * Do-While que se detiene cuando actual vuleve a ser la cabeza
         * que significa que recorrio la lista en su totalidad
         */
        do {
            if (Objects.equals(actual.getDato(), datoBusqueda)) {
                return actual;
            }
            actual = actual.getSiguiente();

        } while (actual != this.cabeza);

        return null; // No encontrado
    }

    public boolean eliminar(T dato) {
        Nodo<T> nodoAEliminar = buscarNodo(dato);
        if (nodoAEliminar == null) {
            return false; // No encontrado
        }
        // Si se encontró, eliminarlo usando el método auxiliar
        eliminarNodo(nodoAEliminar);
        return true;
    }

    private void eliminarNodo(Nodo<T> nodoAEliminar) {
        if (tamanio == 1) { // único nodo
            cabeza = null;
            cola = null;
        } else {
            nodoAEliminar.getAnterior().setSiguiente(nodoAEliminar.getSiguiente());
            nodoAEliminar.getSiguiente().setAnterior(nodoAEliminar.getAnterior());

            if (nodoAEliminar == cabeza) {
                cabeza = nodoAEliminar.getSiguiente();
            }
            if (nodoAEliminar == cola) {
                cola = nodoAEliminar.getAnterior();
            }
        }
        tamanio--;
    }

    // Funcion para la correcta implementacion de cada Pastor
    public void imprimir() {
        if (estaVacia()) {
            System.out.println("Lista Circular Doble Vacía");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("HEAD <-> ");

        Nodo<T> actual = this.cabeza;
        do {
            sb.append(actual); // Usa toString() de Nodo<T>
            actual = actual.getSiguiente();
            if (actual != cabeza) { // Para no repetir la cabeza
                sb.append(" <-> ");
            }
        } while (actual != cabeza);

        sb.append(" <-> HEAD (circular)");
        System.out.println(sb.toString());
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo<T> cabeza) {
        this.cabeza = cabeza;
    }

    public Nodo<T> getCola() {
        return cola;
    }

    public void setCola(Nodo<T> cola) {
        this.cola = cola;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    /**
     * Crea una sublista con n elementos a la derecha del elemento especificado
     * 
     * @param elementoReferencia El elemento de referencia de tipo T
     * @param n                  Cantidad de elementos a incluir en la sublista
     * @return ListaCircularDoble<T> con la sublista creada
     */
    public ListaCircularDoble<T> crearSublistaDerecha(T elementoReferencia, int n) {

        ListaCircularDoble<T> sublista = new ListaCircularDoble<>();

        // Validaciones iniciales
        if (estaVacia()) {
            System.out.println("Error: La lista está vacía");
            return sublista;
        }

        if (n <= 0) {
            System.out.println("Error: n debe ser mayor a 0");
            return sublista;
        }

        if (elementoReferencia == null) {
            System.out.println("Error: El elemento de referencia no puede ser nulo");
            return sublista;
        }

        // Buscar el elemento de referencia en la lista
        Nodo<T> nodoReferencia = buscarNodo(elementoReferencia);

        if (nodoReferencia == null) {
            System.out.println("Error: Elemento no encontrado en la lista");
            return sublista;
        }

        // Verificar que n no exceda el tamaño de la lista
        if (n > this.tamanio) {
            System.out.println("Advertencia: n (" + n + ") excede el tamaño de la lista (" + this.tamanio
                    + "). Se tomarán todos los elementos disponibles.");
            n = this.tamanio;
        }

        // Crear la sublista navegando hacia la derecha
        // Empezar desde el siguiente elemento del pastor de referencia
        Nodo<T> actual = nodoReferencia.getSiguiente();
        int contador = 0;

        do {
            // Agregar el elemento actual a la sublista
            T elementoActual = actual.getDato();
            sublista.agregarFinal(elementoActual);
            contador++;

            // Si ya tenemos n elementos, detener
            if (contador >= n) {
                break;
            }

            // Mover al siguiente elemento (derecha)
            actual = actual.getSiguiente();

        } while (actual != nodoReferencia && contador < n);

        System.out
                .println("Sublista creada con " + contador + " elementos DESPUÉS de " + elementoReferencia
                        + " (sin incluir el de referencia):");
        sublista.imprimir();

        return sublista;
    }

}
