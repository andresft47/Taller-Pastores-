/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Andres Felipe Tovar
 */
public class PanelJuego extends JPanel {

    private JButton btnVolver;
    private JButton btnHistorial;

    public PanelJuego() {
        // Configurar el panel
        setLayout(null);
        setSize(800, 600);
        setBackground(Color.BLACK);

        // Botón de volver en la esquina superior izquierda
        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnVolver.setBackground(Color.WHITE);
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFocusPainted(false);
        btnVolver.setBounds(20, 20, 100, 40);
        add(btnVolver);

        // Botón de historial debajo del botón volver
        btnHistorial = new JButton("Historial");
        btnHistorial.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnHistorial.setBackground(Color.WHITE);
        btnHistorial.setForeground(Color.BLACK);
        btnHistorial.setFocusPainted(false);
        btnHistorial.setBounds(20, 70, 100, 40);
        add(btnHistorial);

    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public JButton getBtnHistorial() {
        return btnHistorial;
    }

    public void setBtnHistorial(JButton btnHistorial) {
        this.btnHistorial = btnHistorial;
    }

}
