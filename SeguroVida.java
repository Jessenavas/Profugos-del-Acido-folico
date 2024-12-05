
/***
*Programa: SeguroVida.java
*Autor:    Espejel Alvarado Christopher Leny
*Fecha:    24/10/2024
*Descripcion: Cotizacion de un seguro de vida, en donde el usuario elegir�
*			  si la desea de manera anual o mensual. Adem�s de que la tasa de
*			  inter�s var�a de acuerdo al g�nero del usuario (1.1% anual para
*			  hombres y 1.5% anual para mujeres).
*			  Si el usuario tiene menos de 18 a�os no se realiza la cotizacion.
***/

import java.awt.*;
import javax.swing.*;

public class SeguroVida extends JFrame{

    // Componentes a utilizar
	private JPanel logoEmpresa, espacioPanel, panelCotizacion;
    private JLabel anioNacimiento, nombre, tipoCotizacion;
    private JButton botonCotizarHombre, botonCotizarMujer;
    private JButton botonLimpiar, botonFinalizar;
    private JTextField iAnioNacimiento, iNombre, iTipoCotizacion;

    public static void main(String[] args) {
        SeguroVida marco = new SeguroVida();
        marco.setSize(550, 300);
        marco.crearGUI();
        marco.setTitle("Cotizacion de Seguro de Vida");
        marco.setDefaultCloseOperation(EXIT_ON_CLOSE);
        marco.setVisible(true);
    }

    private void crearGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container ventana = getContentPane();
        ventana.setLayout(new FlowLayout());

        // Panel para el logo de la empresa
        logoEmpresa = new JPanel();
        logoEmpresa.setBackground(Color.WHITE);
        logoEmpresa.setPreferredSize(new Dimension(350, 40));
        ventana.add(logoEmpresa);
        
        // Panel de separacion
        espacioPanel = new JPanel();
        espacioPanel.setPreferredSize(new Dimension(350,20));
        ventana.add(espacioPanel);
        
        panelCotizacion = new JPanel();
        panelCotizacion.setBackground(Color.WHITE);
        panelCotizacion.setPreferredSize(new Dimension(350, 150));
        ventana.add(panelCotizacion);
        
        // Etiqueta y campo para ingresar el nombre
        nombre = new JLabel("Introduzca su nombre completo: ");
        panelCotizacion.add(nombre);
        iNombre = new JTextField(10);
        panelCotizacion.add(iNombre);

        // Etiqueta y campo para ingresar el a�o de nacimiento
        anioNacimiento = new JLabel("Introduzca su a�o de nacimiento: ");
        panelCotizacion.add(anioNacimiento);
        iAnioNacimiento = new JTextField(5);
        panelCotizacion.add(iAnioNacimiento);

        // Etiqueta y campo para especificar tipo de cotizacion
        tipoCotizacion = new JLabel("Tipo de cotizacion (anual o mensual) : ");
        panelCotizacion.add(tipoCotizacion);
        iTipoCotizacion = new JTextField(10);
        panelCotizacion.add(iTipoCotizacion);

        // Boton para cotizar el seguro de un usuario hombre
        botonCotizarHombre = new JButton("Hombre");
        panelCotizacion.add(botonCotizarHombre);
        botonCotizarHombre.addActionListener(event -> realizarCotizacion("hombre"));

        // Botón para cotizar el seguro de un usuario mujer
        botonCotizarMujer = new JButton("Mujer");
        panelCotizacion.add(botonCotizarMujer);
        botonCotizarMujer.addActionListener(event -> realizarCotizacion("mujer"));

        // Limpia los campos de nombre y a�o de nacimiento del usuario
        botonLimpiar = new JButton("Limpiar");
        panelCotizacion.add(botonLimpiar);
        botonLimpiar.addActionListener(event -> {
            iNombre.setText("");
            iAnioNacimiento.setText("");
            iTipoCotizacion.setText("");
        });

        // Boton para cerrar la ventana
        botonFinalizar = new JButton("Salir");
        botonFinalizar.setAlignmentX(CENTER_ALIGNMENT);
        panelCotizacion.add(botonFinalizar);
        botonFinalizar.addActionListener(event -> System.exit(0));
    }

    private void realizarCotizacion(String genero) {
        String nombre = iNombre.getText();
        String anioDado = iAnioNacimiento.getText(); // Obtiene el a�o de nacimiento ingresado
        int anio = Integer.parseInt(anioDado); // Convierte el a�o a entero
        int edad = 2024 - anio; // Calcula la edad en base al a�o de nacimiento

        // Verificacion de la edad para proceder a la cotizacion
        if (edad < 18) {
            JOptionPane.showMessageDialog(this,
                    "Lo siento, " + nombre + ". \n No se puede realizar la cotizacion para menores de 18 años.");
            System.exit(0); // Termina el proceso si el usuario tiene menos de 18 a�os
        }

        // Operaciones para cotizar
        double monto = 1000000; // Monto fijo del seguro
        double tasa = genero.equals("hombre") ? 0.011 : 0.015; // Interes dependiendo al genero (1.1% hombres, 1.5%
                                                               // mujeres)
        double costoAnual = (monto * tasa);
        double costoMensual = costoAnual / 12;

        // Convierte la cadena a minusculas
        String cotizacion = iTipoCotizacion.getText();
        String resultado = cotizacion.toLowerCase();

        // Cotizacion anual
        if (resultado.equals("anual")) {
            String salida1 = "Estimado/a " + nombre + ",\n"
                    + "Su cotizacion es:\n\n"
                    + "Monto del seguro: $" + monto + "\n"
                    + "Costo anual: $" + String.format("%.2f", costoAnual) + "\n";
            JOptionPane.showMessageDialog(this, salida1); // Salida del programa para cotizacion anual
        }

        // Cotizacion mensual
        else if (resultado.equals("mensual")) {
            String salida2 = "Estimado/a " + nombre + ",\n"
                    + "Su cotizacion es:\n\n"
                    + "Monto del seguro: $" + monto + "\n"
                    + "Costo mensual: $" + String.format("%.2f", costoMensual) + "\n\n";
            JOptionPane.showMessageDialog(this, salida2); // Salida del programa para cotizacion mensual
        }
    }
}
