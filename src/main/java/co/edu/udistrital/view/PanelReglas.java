/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Andres Felipe Tovar
 */
public class PanelReglas extends JPanel {

    private JButton btnRegresar;

    public PanelReglas() {
        // Configurar el panel
        setLayout(null);
        setSize(800, 600);
        setBackground(Color.BLACK);

        // Botón de regreso en la esquina superior izquierda
        btnRegresar = new JButton("Volver");
        btnRegresar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.BLACK);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setBounds(20, 20, 100, 40);
        add(btnRegresar);

        // Título del panel
        JLabel labelTitulo = new JLabel("Reglas del Juego", JLabel.CENTER);
        labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setOpaque(false);
        labelTitulo.setBounds(100, 15, 600, 40);
        add(labelTitulo);

        // Área de texto con las reglas
        JTextArea areaReglas = new JTextArea();
        areaReglas.setEditable(false);
        areaReglas.setFont(new Font("Tahoma", Font.PLAIN, 12));
        areaReglas.setForeground(Color.WHITE);
        areaReglas.setOpaque(false);
        areaReglas.setLineWrap(true);
        areaReglas.setWrapStyleWord(true);

        // Contenido de las reglas
        String reglas = "1. INICIO DEL JUEGO\n\n" +
                "• Cada pastor comienza sentado alrededor de una mesa circular.\n" +
                "• Cada pastor tiene un número inicial de feligreses y dinero.\n" +
                "• Se define quién comienza el primer turno.\n\n" +
                "2. DINÁMICA DE TURNOS\n\n" +
                "En su turno, un pastor debe elegir una de tres acciones: expulsar, rescatar o, si aplica, robar.\n\n" +
                "A. EXPULSAR A UN VECINO\n" +
                "• El pastor elige la dirección: izquierda o derecha.\n" +
                "• Considera hasta n vecinos próximos en esa dirección.\n" +
                "• Selecciona al vecino con menos feligreses.\n" +
                "• Se expulsa a ese vecino:\n" +
                "  - Sus feligreses y su dinero pasan al pastor que expulsó.\n" +
                "  - El expulsado se coloca en la pila de expulsados.\n" +
                "  - La mesa se reorganiza para mantener las reglas de vecindad.\n\n" +
                "B. RESCATAR DE LA PILA\n" +
                "• Si la pila no está vacía, el pastor puede optar por rescatar al pastor en la cima (último expulsado).\n"
                +
                "• El pastor rescatado regresa a la mesa.\n" +
                "• El que lo rescató entrega la mitad exacta de sus feligreses y dinero al rescatado.\n" +
                "• El rescatado se sienta al lado del pastor que lo rescató, restaurando la circularidad.\n\n" +
                "C. ROBAR (CONDICIÓN ESPECIAL)\n" +
                "• Solo puede robar el pastor que sea el más pobre en su turno.\n" +
                "• Puede hacerlo una sola vez por turno.\n" +
                "• Roba un tercio de los feligreses y dinero del pastor más rico.\n\n" +
                "3. RESTRICCIONES DE POSICIÓN\n\n" +
                "• Un pastor puede tener a su izquierda a otro de la misma congregación.\n" +
                "• Nunca puede tener a su derecha a otro de la misma congregación.\n" +
                "• Después de cada acción (expulsión, rescate o robo), se reorganiza la mesa para cumplir esta regla.\n"
                +
                "• El pastor que termina su turno puede elegir libremente quién será el siguiente en jugar.\n\n" +
                "4. FINALIZACIÓN DEL JUEGO\n\n" +
                "• El juego termina cuando queda un solo pastor en la mesa.\n" +
                "• Ese pastor es declarado ganador → rey de burlas y veras, dueño de bolsas y conciencias.";

        areaReglas.setText(reglas);

        // Panel con scroll para las reglas
        JScrollPane scrollPane = new JScrollPane(areaReglas);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setBounds(30, 90, 740, 450);
        add(scrollPane);
    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public void setBtnRegresar(JButton btnRegresar) {
        this.btnRegresar = btnRegresar;
    }
}
