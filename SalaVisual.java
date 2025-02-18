import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SalaVisual extends JPanel {
    private Asiento[][] asientos;
    private int filas = 3;
    private int columnas = 4;

    public SalaVisual() {
        setPreferredSize(new Dimension(350, 400)); // Ajuste de tamaño para mayor espacio
        setLayout(new BorderLayout());
        setBackground(new Color(30, 30, 30)); // Fondo oscuro para estética moderna

        // Panel para la pantalla
        JLabel pantalla = new JLabel("PANTALLA", JLabel.CENTER);
        pantalla.setOpaque(true);
        pantalla.setBackground(Color.BLACK);
        pantalla.setForeground(Color.WHITE);
        pantalla.setFont(new Font("Arial", Font.BOLD, 16));
        pantalla.setPreferredSize(new Dimension(350, 40));
        add(pantalla, BorderLayout.NORTH);

        // Panel espaciador adicional
        JPanel espaciador = new JPanel();
        espaciador.setPreferredSize(new Dimension(350, 20));
        espaciador.setOpaque(false);
        add(espaciador, BorderLayout.CENTER);

        // Panel para los asientos
        JPanel panelAsientos = new JPanel(new GridLayout(filas, columnas, 15, 15)); // Mayor espaciado entre botones
        panelAsientos.setBackground(new Color(50, 50, 50)); // Fondo más oscuro para resaltar los asientos
        asientos = new Asiento[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                asientos[i][j] = new Asiento();
                JButton botonAsiento = new JButton();
                botonAsiento.setBackground(asientos[i][j].getColor());
                botonAsiento.setPreferredSize(new Dimension(50, 40)); // Tamaño de cada botón
                botonAsiento.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                int fila = i;
                int columna = j;
                botonAsiento.addActionListener(e -> manejarSeleccionAsiento(fila, columna, botonAsiento));

                panelAsientos.add(botonAsiento);
            }
        }

        add(panelAsientos, BorderLayout.SOUTH);
    }

    private void manejarSeleccionAsiento(int fila, int columna, JButton boton) {
        Asiento asiento = asientos[fila][columna];
        if (asiento.isOcupado()) {
            asiento.liberar();
        } else {
            asiento.ocupar();
        }
        boton.setBackground(asiento.getColor());
    }
}
