/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.udistrital.model.ListaCircularDoble;
import co.edu.udistrital.model.Pastor;
import co.edu.udistrital.view.VentanaPrincipal;

public class Controller implements ActionListener {

    private final VentanaPrincipal ventana;
    private final ListaCircularDoble<Pastor> listaPrincipal;

    public Controller() {
        ventana = new VentanaPrincipal();
        asignarOyentes();

        // Crear lista de ejemplo para testing - se peude eliminar
        listaPrincipal = crearListaPastores(5);
        System.out.println("Lista principal creada:");
        listaPrincipal.imprimir();

        // Ejemplo de uso de la sublista
        probarSublista();
    }

    // Metodo de testing se peude eliminar
    private void probarSublista() {
        if (!listaPrincipal.estaVacia()) {
            Pastor pastorReferencia = listaPrincipal.getCabeza().getDato();
            System.out.println("\nCreando sublista con 3 pastores a la derecha de: " + pastorReferencia.getNombre());
            crearSublistaDerecha(listaPrincipal, pastorReferencia, 3);
        }
    }

    private void asignarOyentes() {
        ventana.getpMenu().getBtnJugar().addActionListener(this);
        ventana.getpMenu().getBtnReglas().addActionListener(this);
        ventana.getpMenu().getBtnSalir().addActionListener(this);
        ventana.getpReglas().getBtnRegresar().addActionListener(this);
        ventana.getpJuego().getBtnVolver().addActionListener(this);
        ventana.getpJuego().getBtnHistorial().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Reglas":
                ventana.mostrarPanel("REGLAS");
                break;
            case "Jugar":
                ventana.mostrarPanel("JUEGO");
                break;
            case "Salir":
                System.exit(0);
                break;
            case "Volver":
                ventana.mostrarPanel("MENU");
                break;
            case "Historial":
                ventana.mostrarPanel("HISTORIAL");
                break;
        }
    }

    /**
     * Crea una lista circular doble con la cantidad especificada de pastores
     * 
     * @param cantidadPastores La cantidad de pastores que tendrá la lista
     * @return ListaCircularDoble<Pastor> con los pastores creados
     */
    public ListaCircularDoble<Pastor> crearListaPastores(int cantidadPastores) {
        ListaCircularDoble<Pastor> listaPastores = new ListaCircularDoble<>();

        // Validar que la cantidad sea positiva
        if (cantidadPastores <= 0) {
            System.out.println("Error: La cantidad de pastores debe ser mayor a 0");
            return listaPastores;
        }

        // Crear pastores con datos aleatorios
        for (int i = 1; i <= cantidadPastores; i++) {
            String nombre = "Pastor " + i;
            // Entre 10 y 200 feligreses
            int feligreses = (int) (Math.random() * (200 - 10 + 1)) + 10;
            // Dinero entre 500 y 1.000.000, con decimales redondeados a 2 cifras
            double dinero = Math.round((Math.random() * (1_000_000 - 500) + 500) * 100.0) / 100.0;
            String clero = Pastor.obtenerCleroAleatorio();

            Pastor nuevoPastor = new Pastor(nombre, feligreses, dinero, clero);
            listaPastores.agregarFinal(nuevoPastor);
        }

        System.out.println("Lista circular creada con " + cantidadPastores + " pastores:");
        listaPastores.imprimir();

        return listaPastores;
    }

    /**
     * Crea una sublista con n pastores a la derecha del pastor especificado
     * Utiliza el método de la clase ListaCircularDoble
     * 
     * @param listaOriginal    La lista circular original
     * @param pastorReferencia El pastor de referencia (objeto Pastor)
     * @param n                Cantidad de pastores a incluir en la sublista
     * @return ListaCircularDoble<Pastor> con la sublista creada
     */
    public ListaCircularDoble<Pastor> crearSublistaDerecha(ListaCircularDoble<Pastor> listaOriginal,
            Pastor pastorReferencia, int n) {
        if (listaOriginal == null) {
            System.out.println("Error: La lista original no puede ser nula");
            return new ListaCircularDoble<>();
        }

        if (pastorReferencia == null) {
            System.out.println("Error: El pastor de referencia no puede ser nulo");
            return new ListaCircularDoble<>();
        }

        return listaOriginal.crearSublistaDerecha(pastorReferencia, n);
    }

}
