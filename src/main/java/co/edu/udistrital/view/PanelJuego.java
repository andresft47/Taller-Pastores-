/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.view;

import java.awt.*;
import javax.swing.*;

public class PanelJuego extends JPanel {

    private JButton btnVolver;
    private JButton btnHistorial;

    // Botones de decisiones
    private JButton btnExpulsar;
    private JButton btnRescatar;
    private JButton btnRobar;
    private JButton btnTerminarTurno;

    // Zona de mesa (participantes)
    private DefaultListModel<String> modeloMesa;
    private JList<String> listaMesa;

    // Zona de eliminados
    private DefaultListModel<String> modeloEliminados;
    private JList<String> listaEliminados;

    public PanelJuego() {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        // ------- TOP (Botones generales) -------
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.setBackground(Color.BLACK);

        btnVolver = crearBoton("Volver");
        btnHistorial = crearBoton("Historial");

        panelTop.add(btnVolver);
        panelTop.add(btnHistorial);
        add(panelTop, BorderLayout.NORTH);

        // ------- CENTRO (Mesa circular simulada) -------
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(new Color(40, 40, 40));

        JLabel lblMesa = new JLabel("Mesa de Pastores", JLabel.CENTER);
        lblMesa.setForeground(Color.WHITE);
        lblMesa.setFont(new Font("Tahoma", Font.BOLD, 18));
        panelCentro.add(lblMesa, BorderLayout.NORTH);

        modeloMesa = new DefaultListModel<>();
        listaMesa = new JList<>(modeloMesa);
        listaMesa.setBackground(Color.BLACK);
        listaMesa.setForeground(Color.GREEN);
        listaMesa.setFont(new Font("Monospaced", Font.BOLD, 14));
        panelCentro.add(new JScrollPane(listaMesa), BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        // ------- DERECHA (Eliminados) -------
        JPanel panelDerecha = new JPanel(new BorderLayout());
        panelDerecha.setPreferredSize(new Dimension(200, 0));
        panelDerecha.setBackground(new Color(30, 30, 30));

        JLabel lblEliminados = new JLabel("Eliminados", JLabel.CENTER);
        lblEliminados.setForeground(Color.WHITE);
        lblEliminados.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelDerecha.add(lblEliminados, BorderLayout.NORTH);

        modeloEliminados = new DefaultListModel<>();
        listaEliminados = new JList<>(modeloEliminados);
        listaEliminados.setBackground(Color.BLACK);
        listaEliminados.setForeground(Color.RED);
        listaEliminados.setFont(new Font("Monospaced", Font.BOLD, 14));
        panelDerecha.add(new JScrollPane(listaEliminados), BorderLayout.CENTER);

        add(panelDerecha, BorderLayout.EAST);

        // ------- SUR (Acciones de juego) -------
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelAcciones.setBackground(Color.BLACK);

        btnExpulsar = crearBoton("Expulsar");
        btnRescatar = crearBoton("Rescatar");
        btnRobar = crearBoton("Robar");
        btnTerminarTurno = crearBoton("Terminar Turno");

        panelAcciones.add(btnExpulsar);
        panelAcciones.add(btnRescatar);
        panelAcciones.add(btnRobar);
        panelAcciones.add(btnTerminarTurno);

        add(panelAcciones, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Tahoma", Font.BOLD, 12));
        b.setBackground(new Color(70, 130, 180));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        return b;
    }

    // Métodos para que el Controller actualice la vista
    public void actualizarMesa(java.util.List<String> pastores) {
        modeloMesa.clear();
        for (String p : pastores) {
            modeloMesa.addElement(p);
        }
    }

    public void actualizarEliminados(java.util.List<String> eliminados) {
        modeloEliminados.clear();
        for (String p : eliminados) {
            modeloEliminados.addElement(p);
        }
    }

    // Getters para el controlador
    public JButton getBtnVolver() { return btnVolver; }
    public JButton getBtnHistorial() { return btnHistorial; }
    public JButton getBtnExpulsar() { return btnExpulsar; }
    public JButton getBtnRescatar() { return btnRescatar; }
    public JButton getBtnRobar() { return btnRobar; }
    public JButton getBtnTerminarTurno() { return btnTerminarTurno; }
}
