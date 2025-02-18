
/******
 * @Programa: MaquinaExpendedora.java
 * @Autor: Espejel Alvarado Christopher Leny
 * @Fecha: 07/12/2024
 * @Profesor: Adriana Vega Palos
 * @Descripcion: Maquina expendedora con 15 productos, el usuario podra ingresar monedas de 20, 10, 5 , 2 o 1
 *               peso y la maquina devolvera el cambio. Si el usuario trata de agregar mas de 30 pesos, se
 *               muestra un aviso. Una vez devuelto el cambio, el programa se reinicia y se puede hacer una
 *               nueva compra.
 ******/

import java.awt.*;
import javax.swing.*;

public class CompraSnacks extends JFrame {

    private JButton[] botonesProductos = new JButton[15], botonesMonedas = new JButton[5];
    private JButton botonSalir;

    private JLabel[] precioProductosLabels = new JLabel[15];
    private JLabel pagarLabel, dineroIngresadoLabel, cambioLabel, monedasLabel, cambioMonedasLabel;

    private int dineroIngresado = 0;
    private int precioSeleccionado = 0;
    private int precios[] = { 17, 16, 15, 12, 12, 13, 14, 15, 10, 22, 15, 16, 10, 20, 20 };
    private int valoresMonedas[] = { 20, 10, 5, 2, 1 };

    public static void main(String[] args) {
        CompraSnacks ventana = new CompraSnacks();
        ventana.setTitle("Maquina expendedora");
        ventana.setBounds(50, 50, 1050, 550);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public CompraSnacks() {
        setLayout(null);
        // Cambia color de la ventana.
        getContentPane().setBackground(new Color(169, 211, 191));

        // Fuentes que se usaran en las etiquetas.
        Font fuenteSerief = new Font("Serief", Font.BOLD, 14);

        // Valores predeterminados para ajustar los botones.
        int x1 = 30, y1 = 30, width1 = 120, height1 = 100;
        // Nombre de los archivos de imagen de los productos.
        String[] imgProductos = { "refresco1.jpg", "refresco2.jpg", "refresco3.jpg", "jugo1.jpg",
                "jugo2.jpg", "jugo3.jpg", "agua1.jpg", "agua2.png", "golosina1.jpg",
                "golosina2.jpg", "golosina3.jpg", "golosina4.jpg", "golosina5.jpg",
                "golosina6.jpg", "golosina7.jpg" };

        // for para crear los botones.
        for (int b = 0; b < botonesProductos.length; b++) {

            botonesProductos[b] = new JButton();
            // Coloca a cada boton una imagen.
            ImageIcon iconoOriginal = new ImageIcon("./imgMaquina/" + imgProductos[b]);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

            botonesProductos[b].setIcon(iconoEscalado);
            botonesProductos[b].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            botonesProductos[b].setBounds(x1, y1, width1, height1);
            // Le da el precio a cada boton de acuerdo al array precios[].
            final int precio = precios[b];
            botonesProductos[b].addActionListener(e -> seleccionarProducto(precio));
            add(botonesProductos[b]);

            x1 += width1 + 20; // Incrementar posici�n horizontal con el ancho mas un espacio entre productos.
            if ((b + 1) % 5 == 0) { // Cada 5 filas hay un salto de fila.
                x1 = 30; // Cada 5 botones se reinicia la posicion horizontal.
                y1 += height1 + 50; // Incrementa la posici�n vertical.
            }
        }

        /*
         * Se le dan nuevos valores para las posiciones vertical y horizontal de las
         * etiquetas de los
         * productos.
         */
        x1 = 50;
        y1 = 100;

        // for para crear las etiquetas que indican el precio de los productos.
        for (int p = 0; p < precioProductosLabels.length; p++) {
            precioProductosLabels[p] = new JLabel("Precio: $" + precios[p]);
            precioProductosLabels[p].setFont(fuenteSerief);
            precioProductosLabels[p].setBounds(x1, y1, width1, height1);
            add(precioProductosLabels[p]);

            x1 += width1 + 20; // Incrementa la posicion horizontal mas un ancho entre etiquetas.
            if ((p + 1) % 5 == 0) { // Cada 5 etiquetas hay un salto de fila.
                x1 = 50; // Se reinicia la posicion horizontal.
                y1 += height1 + 50; // se incrementa la posicion vertical.
            }
        }

        // Nuevos valores de posicionamiento para los botones de las monedas
        int x2 = 750, y2 = 60, width2 = 70, height2 = 70;
        // Nombre de los archivos de imagen de las monedas.
        String[] imgMonedas = { "m20.jpg", "m10.jpg", "m5.jpg", "m2.jpg", "m1.jpg" };

        // for para crear los botones de las monedas con sus respectivas imagenes.
        for (int m = 0; m < botonesMonedas.length; m++) {
            botonesMonedas[m] = new JButton();
            // Coloca a cada boton una imagen.
            ImageIcon iconoOriginal = new ImageIcon("./imgMaquina/" + imgMonedas[m]);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(width2, height2, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

            botonesMonedas[m].setIcon(iconoEscalado);
            botonesMonedas[m].setBounds(x2, y2, width2, height2);
            final int valor = valoresMonedas[m]; // Otorga valor a cada moneda.
            botonesMonedas[m].addActionListener(e -> ingresarMoneda(valor));
            add(botonesMonedas[m]);

            x2 += width2 + 20; // Incrementa la posicion horizontal mas un ancho.

            if ((m + 1) % 3 == 0) { // Cada 3 botones hay un salto de fila.
                x2 = 750; // Reinicia la posicion horizontal.
                y2 += height2 + 20; // Incrementa la posicion vertical.
            }
        }

        /*
         * Etiquetas que muestran el precio de los productos, el dinero ingresado y el
         * cambio
         * devuelto, en caso de que lo haya.
         */
        pagarLabel = crearLabel("Ingresar: $0", 760, 240, 150, 20);
        dineroIngresadoLabel = crearLabel("Ingresado: $0", 760, 270, 150, 20);
        cambioLabel = crearLabel("Cambio: $0", 760, 300, 300, 20);
        monedasLabel = crearLabel("Monedas devueltas: ", 760, 330, 300, 20);
        cambioMonedasLabel = crearLabel("", 760, 360, 300, 50);

        // Boton para cerrar la interfaz.
        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(event -> dispose());
        botonSalir.setBounds(820, 440, 120, 40);
        add(botonSalir);

    }

    // Metodo para crear un JLabel con las caracteisticas deseadas.
    private JLabel crearLabel(String texto, int x, int y, int width, int height) {
        Font fuenteArial = new Font("Arial", Font.BOLD, 16);
        JLabel label = new JLabel(texto);
        label.setFont(fuenteArial);
        label.setBounds(x, y, width, height);
        add(label);
        return label;
    }

    /*
     * Al dar click en un producto, se reinician los valores de las etiquetas y se
     * coloca el precio del producto.
     */
    private void seleccionarProducto(int precio) {
        precioSeleccionado = precio;
        pagarLabel.setText("Ingresar: $" + precioSeleccionado);
        dineroIngresado = 0; // Resetea el dinero ingresado para una nueva compra.
        dineroIngresadoLabel.setText("Ingresado: $0");
        cambioLabel.setText("Cambio: $0");
    }

    private void ingresarMoneda(int valor) {
        if (precioSeleccionado == 0) { // No permite ingresar monedas si no se ha seleecionado un producto.
            JOptionPane.showMessageDialog(this, "Selecciona un producto primero");
            return;
        }

        if (dineroIngresado + valor > 30) { // No permite ingresar mas de $30
            JOptionPane.showMessageDialog(this,
                    "No se puede ingresar m�s de $30. Por favor, ingresa la cantidad correcta.");
            return;
        }

        dineroIngresado += valor; // Aumento de acuerdo a las monedas ingresadas.
        dineroIngresadoLabel.setText("Ingresado: $" + dineroIngresado);

        if (dineroIngresado >= precioSeleccionado) {
            int cambio = dineroIngresado - precioSeleccionado;
            calcularCambio(cambio);
            precioSeleccionado = 0; // Reinicia para la proxima compra.
        }
    }

    private void calcularCambio(int cambio) {
        if (cambio == 0) {
            cambioLabel.setText("Cambio: Exacto");
            return;
        } else if (cambio > 0) {
            cambioLabel.setText("Cambio: $" + cambio);
        }

        // En caso de que haya cambio.
        StringBuilder cambioMonedas = new StringBuilder("");
        for (int denominacion : valoresMonedas) { // Recorre el array valoresMonedas[].
            int cantidad = cambio / denominacion;
            if (cantidad > 0) {
                // Muestra la cantidad de cada moneda devuelta.
                cambioMonedas.append("<html>").append("$").append(denominacion).append("...").append(cantidad)
                        .append("<br>");
                // Va restando hasta que quede 0 y el cambio quede cubierto
                cambio -= denominacion * cantidad;
            }
        }
        cambioMonedas.append("</html>");

        // En la etiqueta de cambio actualiza con los valores de monedas.
        cambioMonedasLabel.setText(cambioMonedas.toString());
    }
}