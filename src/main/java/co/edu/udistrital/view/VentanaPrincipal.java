/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

//Responsable de administar a todos los panales del juego
public class VentanaPrincipal extends JFrame {

    private JPanel contenedor; // Panel que contendra a todos los paneles
    private PanelMenu pMenu; // Panel del menú con botones
    private PanelJuego pJuego; // Panel del tablero central del juego
    private PanelReglas pReglas;
    private CardLayout cardLayout;

    public VentanaPrincipal() {
        setTitle("Juego de Pastores");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        // Creacion de cada panel del juego
        pMenu = new PanelMenu();
        pJuego = new PanelJuego();
        pReglas = new PanelReglas();

        // Se agregan todos los paneles al contenedor
        contenedor.add(pMenu, "MENU");
        contenedor.add(pJuego, "JUEGO");
        contenedor.add(pReglas, "REGLAS");

        add(contenedor); // el contenedor ocupa toda la ventana

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(contenedor, nombre);
    }

    public JPanel getContenedor() {
        return contenedor;
    }

    public void setContenedor(JPanel contenedor) {
        this.contenedor = contenedor;
    }

    public PanelMenu getpMenu() {
        return pMenu;
    }

    public void setpMenu(PanelMenu pMenu) {
        this.pMenu = pMenu;
    }

    public PanelJuego getpJuego() {
        return pJuego;
    }

    public void setpJuego(PanelJuego pJuego) {
        this.pJuego = pJuego;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public PanelReglas getpReglas() {
        return pReglas;
    }

    public void setpReglas(PanelReglas pReglas) {
        this.pReglas = pReglas;
    }

}
