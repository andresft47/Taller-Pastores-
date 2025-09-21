/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.udistrital.view.VentanaPrincipal;

/**
 *
 * @author Andres Felipe Tovar
 */
public class Controller implements ActionListener {

    private VentanaPrincipal ventana;

    public Controller() {
        ventana = new VentanaPrincipal();
        asignarOyentes();

    }

    private void asignarOyentes() {
        ventana.getpMenu().getBtnJugar().addActionListener(this);
        ventana.getpMenu().getBtnReglas().addActionListener(this);
        ventana.getpMenu().getBtnSalir().addActionListener(this);
        ventana.getpReglas().getBtnRegresar().addActionListener(this);
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
        }
    }

}
