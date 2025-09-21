/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.controller;

import co.edu.udistrital.model.ListaCircularDoble;
import co.edu.udistrital.model.Pastor;
import co.edu.udistrital.model.Nodo;
import co.edu.udistrital.view.VentanaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Controller implements ActionListener {

    private final VentanaPrincipal ventana;
    private ListaCircularDoble<Pastor> listaPrincipal;
    // Pila de expulsados, implementada aquí sin crear clase nueva
    private final Deque<Pastor> pilaEliminados;
    // Nodo que representa al pastor cuyo turno está activo
    private Nodo<Pastor> nodoActual;

    public Controller() {
        ventana = new VentanaPrincipal();
        pilaEliminados = new ArrayDeque<>();

        // Vincular listeners a botones (asegúrate de usar la versión del PanelJuego que tiene estos getters)
        asignarOyentes();

        // Crear lista inicial de pastores (puedes cambiar la cantidad)
        listaPrincipal = crearListaPastores(6);
        nodoActual = listaPrincipal.estaVacia() ? null : listaPrincipal.getCabeza();

        // Mostrar vista inicial (actualiza mesa y eliminados)
        actualizarVista();

        // Mostrar el menú por defecto
        ventana.mostrarPanel("MENU");
    }

    private void asignarOyentes() {
        // Menu
        ventana.getpMenu().getBtnJugar().addActionListener(this);
        ventana.getpMenu().getBtnReglas().addActionListener(this);
        ventana.getpMenu().getBtnSalir().addActionListener(this);

        // Reglas
        ventana.getpReglas().getBtnRegresar().addActionListener(this);

        // Juego (estos getters deben existir en tu PanelJuego)
        ventana.getpJuego().getBtnVolver().addActionListener(this);
        ventana.getpJuego().getBtnHistorial().addActionListener(this);
        ventana.getpJuego().getBtnExpulsar().addActionListener(this);
        ventana.getpJuego().getBtnRescatar().addActionListener(this);
        ventana.getpJuego().getBtnRobar().addActionListener(this);
        ventana.getpJuego().getBtnTerminarTurno().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        // Menú
        if (src == ventana.getpMenu().getBtnJugar()) {
            // Iniciar (o reiniciar) juego
            listaPrincipal = crearListaPastores(6);
            pilaEliminados.clear();
            nodoActual = listaPrincipal.estaVacia() ? null : listaPrincipal.getCabeza();
            actualizarVista();
            ventana.mostrarPanel("JUEGO");
            return;
        }
        if (src == ventana.getpMenu().getBtnReglas()) {
            ventana.mostrarPanel("REGLAS");
            return;
        }
        if (src == ventana.getpMenu().getBtnSalir()) {
            System.exit(0);
            return;
        }

        // Reglas -> Volver
        if (src == ventana.getpReglas().getBtnRegresar()) {
            ventana.mostrarPanel("MENU");
            return;
        }

        // Juego: navegación
        if (src == ventana.getpJuego().getBtnVolver()) {
            ventana.mostrarPanel("MENU");
            return;
        }
        if (src == ventana.getpJuego().getBtnHistorial()) {
            ventana.mostrarPanel("HISTORIAL");
            return;
        }

        // Acciones del juego
        if (src == ventana.getpJuego().getBtnExpulsar()) {
            expulsarJugador();
            return;
        }
        if (src == ventana.getpJuego().getBtnRescatar()) {
            rescatarJugador();
            return;
        }
        if (src == ventana.getpJuego().getBtnRobar()) {
            robarJugador();
            return;
        }
        if (src == ventana.getpJuego().getBtnTerminarTurno()) {
            terminarTurno();
        }
    }

    // ------------------------------
    //  Lógica de juego
    // ------------------------------

    private void actualizarVista() {
        // Actualiza la "mesa" (lista de participantes)
        List<String> nombresMesa = new ArrayList<>();
        if (!listaPrincipal.estaVacia()) {
            Nodo<Pastor> temp = listaPrincipal.getCabeza();
            do {
                Pastor p = temp.getDato();
                String marcaTurno = (temp == nodoActual) ? " <- turno" : "";
                nombresMesa.add(p.getNombre() + " (F:" + p.getFeligreses() + ", $:" + p.getDinero() + ")" + marcaTurno);
                temp = temp.getSiguiente();
            } while (temp != listaPrincipal.getCabeza());
        }
        ventana.getpJuego().actualizarMesa(nombresMesa);

        // Actualiza la lista de expulsados (pila)
        List<String> nombresEliminados = new ArrayList<>();
        for (Pastor p : pilaEliminados) {
            nombresEliminados.add(p.getNombre() + " (F:" + p.getFeligreses() + ", $:" + p.getDinero() + ")");
        }
        ventana.getpJuego().actualizarEliminados(nombresEliminados);
    }

    /**
     * Expulsar: muestra dos ventanas emergentes en secuencia:
     *  1) Dirección: Izquierda / Derecha
     *  2) n: cantidad de vecinos (1 .. tamanio-1)
     *
     * Selecciona entre los n vecinos en esa dirección el que tenga menos feligreses
     * y lo expulsa (sus feligreses y dinero pasan al expulsor). El expulsado va a la pila interna.
     */
    private void expulsarJugador() {
        if (listaPrincipal == null || listaPrincipal.estaVacia() || listaPrincipal.getTamanio() <= 1) {
            JOptionPane.showMessageDialog(ventana, "No hay suficientes participantes para expulsar.");
            return;
        }
        if (nodoActual == null) nodoActual = listaPrincipal.getCabeza();

        // 1) Dirección
        String[] opciones = {"Izquierda", "Derecha"};
        String direccion = (String) JOptionPane.showInputDialog(
                ventana,
                "Elige la dirección:",
                "Dirección",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);
        if (direccion == null) return; // cancelado

        // 2) Cantidad n
        int max = listaPrincipal.getTamanio() - 1;
        String input = JOptionPane.showInputDialog(ventana,
                "Ingrese la cantidad de vecinos (1 - " + max + "):",
                "Cantidad de vecinos",
                JOptionPane.QUESTION_MESSAGE);
        if (input == null) return; // cancelado
        int n;
        try {
            n = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ventana, "Número inválido.");
            return;
        }
        if (n < 1 || n > max) {
            JOptionPane.showMessageDialog(ventana, "Debes ingresar un número entre 1 y " + max);
            return;
        }

        // Recolectar los n vecinos en la dirección seleccionada (guardamos nodos)
        List<Nodo<Pastor>> vecinos = new ArrayList<>();
        if (direccion.equalsIgnoreCase("Derecha")) {
            Nodo<Pastor> cur = nodoActual.getSiguiente();
            for (int i = 0; i < n; i++) {
                vecinos.add(cur);
                cur = cur.getSiguiente();
            }
        } else { // Izquierda
            Nodo<Pastor> cur = nodoActual.getAnterior();
            for (int i = 0; i < n; i++) {
                vecinos.add(cur);
                cur = cur.getAnterior();
            }
        }

        // Encontrar el vecino con menos feligreses
        Nodo<Pastor> minNodo = vecinos.get(0);
        for (Nodo<Pastor> nd : vecinos) {
            if (nd.getDato().getFeligreses() < minNodo.getDato().getFeligreses()) {
                minNodo = nd;
            }
        }

        Pastor expulsado = minNodo.getDato();
        Pastor expulsor = nodoActual.getDato();

        // Transferir feligreses y dinero del expulsado al expulsor
        int feligresesTransfer = expulsado.getFeligreses();
        double dineroTransfer = expulsado.getDinero();
        expulsor.setFeligreses(expulsor.getFeligreses() + feligresesTransfer);
        // redondear dinero a 2 decimales
        double nuevoDineroExpulsor = Math.round((expulsor.getDinero() + dineroTransfer) * 100.0) / 100.0;
        expulsor.setDinero(nuevoDineroExpulsor);

        // Dejar expulsado con 0 (está en la pila sin recursos)
        expulsado.setFeligreses(0);
        expulsado.setDinero(0);

        // Eliminar de la lista circular usando el método existente eliminar(T dato)
        boolean ok = listaPrincipal.eliminar(expulsado);
        if (!ok) {
            // por seguridad, si eliminar falló (no encontrado), avisamos
            JOptionPane.showMessageDialog(ventana, "Error al expulsar (no se encontró al pastor).");
            return;
        }

        // Ponerlo en la pila (tope frente de la Deque)
        pilaEliminados.addFirst(expulsado);

        JOptionPane.showMessageDialog(ventana, "Se expulsó a " + expulsado.getNombre() + " hacia " + direccion + ".");

        // Avanzar turno al siguiente (decisión de diseño: avanzamos al siguiente)
        if (!listaPrincipal.estaVacia()) {
            nodoActual = nodoActual.getSiguiente();
        } else {
            nodoActual = null;
        }

        actualizarVista();
    }

    /**
     * Rescatar: si hay expulsados en la pila, se desapila el ultimo expulsado.
     * El rescatado recibe la mitad exacta (enteros para feligreses, dinero con 2 decimales)
     * del que lo rescata. Se inserta al lado del que rescata (después de nodoActual).
     */
    private void rescatarJugador() {
        if (pilaEliminados.isEmpty()) {
            JOptionPane.showMessageDialog(ventana, "No hay pastores para rescatar.");
            return;
        }
        if (listaPrincipal == null) {
            JOptionPane.showMessageDialog(ventana, "La mesa aún no está inicializada.");
            return;
        }
        if (nodoActual == null) nodoActual = listaPrincipal.getCabeza();

        // Desapilar
        Pastor rescatado = pilaEliminados.removeFirst();
        Pastor rescatador = nodoActual.getDato();

        // Transferencia: la mitad exacta de rescatador al rescatado
        int mitadFeligreses = rescatador.getFeligreses() / 2;
        double mitadDinero = Math.round((rescatador.getDinero() / 2.0) * 100.0) / 100.0;

        rescatado.setFeligreses(mitadFeligreses);
        rescatado.setDinero(mitadDinero);

        // Reducir al rescatador
        rescatador.setFeligreses(rescatador.getFeligreses() - mitadFeligreses);
        double restoDinero = Math.round((rescatador.getDinero() - mitadDinero) * 100.0) / 100.0;
        rescatador.setDinero(restoDinero);

        // Insertar rescatado justo después del rescatador (manipulación directa de nodos)
        insertarDespues(nodoActual, rescatado);

        JOptionPane.showMessageDialog(ventana, "Se rescató a " + rescatado.getNombre() + " (está junto al rescatador).");

        actualizarVista();
    }

    /**
     * Robar: permitido solo si el pastor actual es el más pobre (por dinero).
     * Robará 1/3 (enteros para feligreses, dinero con 2 decimales) del pastor más rico.
     */
    private void robarJugador() {
        if (listaPrincipal == null || listaPrincipal.estaVacia()) {
            JOptionPane.showMessageDialog(ventana, "No hay partida activa.");
            return;
        }
        if (nodoActual == null) nodoActual = listaPrincipal.getCabeza();

        // Encontrar más pobre (por dinero) y más rico (por dinero)
        Nodo<Pastor> temp = listaPrincipal.getCabeza();
        Nodo<Pastor> nodoMasPobre = temp;
        Nodo<Pastor> nodoMasRico = temp;
        do {
            if (temp.getDato().getDinero() < nodoMasPobre.getDato().getDinero()) {
                nodoMasPobre = temp;
            }
            if (temp.getDato().getDinero() > nodoMasRico.getDato().getDinero()) {
                nodoMasRico = temp;
            }
            temp = temp.getSiguiente();
        } while (temp != listaPrincipal.getCabeza());

        if (nodoActual != nodoMasPobre) {
            JOptionPane.showMessageDialog(ventana, "Solo puede robar el pastor más pobre en su turno.");
            return;
        }

        // Robar 1/3 del más rico
        Pastor pobre = nodoActual.getDato();
        Pastor rico = nodoMasRico.getDato();

        if (rico == pobre) {
            JOptionPane.showMessageDialog(ventana, "No hay otro pastor para robar.");
            return;
        }

        int feligresesRobados = rico.getFeligreses() / 3;
        double dineroRobado = Math.round((rico.getDinero() / 3.0) * 100.0) / 100.0;

        // Transferir
        pobre.setFeligreses(pobre.getFeligreses() + feligresesRobados);
        pobre.setDinero(Math.round((pobre.getDinero() + dineroRobado) * 100.0) / 100.0);

        rico.setFeligreses(rico.getFeligreses() - feligresesRobados);
        rico.setDinero(Math.round((rico.getDinero() - dineroRobado) * 100.0) / 100.0);

        JOptionPane.showMessageDialog(ventana,
                "El pastor " + pobre.getNombre() + " robó " + feligresesRobados + " feligreses y $" + dineroRobado + " de " + rico.getNombre());

        actualizarVista();
    }

    /**
     * Terminar turno: avanza el nodoActual al siguiente.
     */
    private void terminarTurno() {
        if (listaPrincipal == null || listaPrincipal.estaVacia()) {
            JOptionPane.showMessageDialog(ventana, "No hay partida activa.");
            return;
        }
        if (nodoActual == null) nodoActual = listaPrincipal.getCabeza();
        nodoActual = nodoActual.getSiguiente();
        JOptionPane.showMessageDialog(ventana, "Turno pasado a: " + nodoActual.getDato().getNombre());
        actualizarVista();
    }

    // ------------------------------
    //  Helpers sobre la lista circular (se hacen en el Controller, no se crean clases)
    // ------------------------------

    /**
     * Inserta un Pastor justo después del nodoReferencia en la lista circular.
     * Manipula nodos directamente usando getters/setters públicos del modelo.
     */
    private void insertarDespues(Nodo<Pastor> nodoReferencia, Pastor elemento) {
        if (listaPrincipal == null || listaPrincipal.estaVacia()) {
            // si la lista estaba vacía, agregar como único elemento
            listaPrincipal.agregarInicio(elemento);
            nodoActual = listaPrincipal.getCabeza();
            return;
        }

        Nodo<Pastor> siguiente = nodoReferencia.getSiguiente();
        Nodo<Pastor> nuevo = new Nodo<>(elemento, nodoReferencia, siguiente);

        nodoReferencia.setSiguiente(nuevo);
        siguiente.setAnterior(nuevo);

        // Si el nodoReferencia era la cola, actualizarla
        if (nodoReferencia == listaPrincipal.getCola()) {
            listaPrincipal.setCola(nuevo);
        }

        // Aumentar tamaño
        listaPrincipal.setTamanio(listaPrincipal.getTamanio() + 1);
    }

    // ------------------------------
    //  Métodos auxiliares existentes (creados tomando como base tu código original)
    // ------------------------------

    /**
     * Crea n pastores con datos aleatorios y los agrega al final de una ListaCircularDoble.
     */
    public ListaCircularDoble<Pastor> crearListaPastores(int cantidadPastores) {
        ListaCircularDoble<Pastor> listaPastores = new ListaCircularDoble<>();

        if (cantidadPastores <= 0) {
            System.out.println("Error: La cantidad de pastores debe ser mayor a 0");
            return listaPastores;
        }

        for (int i = 1; i <= cantidadPastores; i++) {
            String nombre = "Pastor " + i;
            int feligreses = (int) (Math.random() * (200 - 10 + 1)) + 10;
            double dinero = Math.round((Math.random() * (1_000_000 - 500) + 500) * 100.0) / 100.0;
            String clero = Pastor.obtenerCleroAleatorio();

            Pastor nuevoPastor = new Pastor(nombre, feligreses, dinero, clero);
            listaPastores.agregarFinal(nuevoPastor);
        }

        System.out.println("Lista circular creada con " + cantidadPastores + " pastores:");
        listaPastores.imprimir();

        return listaPastores;
    }
}
