/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Andres Felipe Tovar
 */
public class PanelMenu extends JPanel {

    private JButton btnReglas;
    private JButton btnJugar;
    private JButton btnSalir;
    private Image imagenFondo;

    public PanelMenu() {
        // Configurar el panel para usar layout absoluto
        setLayout(null);
        setSize(800, 600); // Tamaño del panel

        // Cargar imagen de fondo
        try {
            imagenFondo = ImageIO.read(getClass().getResource("/co/edu/udistrital/imagenes/imagen.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen de fondo: " + e.getMessage());
        }

        // Título centrado y con mejor estilo
        JLabel labelTitulo = new JLabel("Juego de Pastores", JLabel.CENTER);
        labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 36));
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setOpaque(false);
        labelTitulo.setBounds(120, 40, 500, 60);
        add(labelTitulo);

        btnReglas = new JButton("Reglas");
        btnJugar = new JButton("Jugar");
        btnSalir = new JButton("Salir");

        // Configurar fuente y estilo de los botones
        Font fontBotones = new Font("Tahoma", Font.BOLD, 15);
        btnJugar.setFont(fontBotones);
        btnReglas.setFont(fontBotones);
        btnSalir.setFont(fontBotones);

        // Colores de los botones
        btnJugar.setBackground(new Color(8, 93, 166));
        btnReglas.setBackground(new Color(8, 93, 166));
        btnSalir.setBackground(new Color(220, 20, 60));

        // Color de la tipografia de los botones
        btnJugar.setForeground(Color.WHITE);
        btnReglas.setForeground(Color.WHITE);
        btnSalir.setForeground(Color.WHITE);

        // Posicion de los botones
        btnJugar.setBounds(280, 300, 150, 40);
        btnReglas.setBounds(280, 360, 150, 40);
        btnSalir.setBounds(280, 420, 150, 40);

        // Agregar botones al panel
        add(btnJugar);
        add(btnReglas);
        add(btnSalir);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public JButton getBtnReglas() {
        return btnReglas;
    }

    public void setBtnReglas(JButton btnReglas) {
        this.btnReglas = btnReglas;
    }

    public JButton getBtnJugar() {
        return btnJugar;
    }

    public void setBtnJugar(JButton btnJugar) {
        this.btnJugar = btnJugar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JButton btnSalir) {
        this.btnSalir = btnSalir;
    }

    public Image getImagenFondo() {
        return imagenFondo;
    }

    public void setImagenFondo(Image imagenFondo) {
        this.imagenFondo = imagenFondo;
    }

}
