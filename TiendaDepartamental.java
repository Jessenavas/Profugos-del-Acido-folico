/******
 * @Programa: TiendaDepartamental.java
 * @Autor: Espejel Alvarado Christopher Leny
 * @Fecha: 14/11/2024
 * @Profesor: Adriana Vega Palos
 * @Descripcion: Tienda departamental donde el usuario selecciona uno o varios productos de una lista.
 * 				 El total de la compra, se muestra en la interfaz, con el IVA incluido. Se elige una
 * 				 forma de pago (efectivo o tarjeta).
 * 				 Valida las entradas de pago y muestra un mensaje de confirmacion al finalizar la
 * 				 transaccion. Se habilitan los botones y campos de entrada según la selección del usuario
 * 				 y la forma de pago elegida.
 *******/

import java.awt.*;
import javax.swing.*;

public class TiendaDepartamental extends JFrame {

	private JRadioButton selectProducto1, selectProducto2, selectProducto3;
	private JRadioButton selectProducto4, selectProducto5, selectProducto6;
	private JLabel producto1, producto2, producto3, producto4, producto5, producto6;
	private JLabel subtotalLabel, ivaLabel, totalLabel, formaPagoLabel, efectivoLabel, cambioLabel, numTarjetaLabel;

	private ButtonGroup formaPago;
	private JTextField cantidadEfectivo, iNumeroTarjeta;
	private JRadioButton pagoEfectivo, pagoTarjeta;
	private JButton calcularTotal, procederCompra, botonSalir;

	private double subtotal = 0, total = 0, iva = 0;
	private double[] precios = { 4500, 2100, 600, 5000, 400, 700 };

	public static void main(String[] ar) {
		TiendaDepartamental interfazCompra = new TiendaDepartamental();
		interfazCompra.setTitle("Tienda Departamental"); // Titulo del marco.
		interfazCompra.setBounds(100, 100, 910, 550);
		interfazCompra.setVisible(true);
		interfazCompra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public TiendaDepartamental() {
		setLayout(null); // Permite colocar los componentes con setBounds().

		// Producto 1
		producto1 = new JLabel();
		producto1.setIcon(new ImageIcon("imagenes/audifonos.jpeg"));
		producto1.setBounds(30, 50, 180, 120);
		producto1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(producto1);

		selectProducto1 = new JRadioButton("Audifonos BT $4500");
		selectProducto1.setBounds(50, 170, 180, 40);
		/*
		 * Valida que un producto sea seleccionado antes de habilitar el boton
		 * "calcularTotal".
		 * Esta en todos los productos.
		 */
		selectProducto1.addActionListener(event -> habBotonCalcularTot());
		add(selectProducto1);

		// Producto 2
		producto2 = new JLabel();
		producto2.setIcon(new ImageIcon("imagenes/microondas.jpeg"));
		producto2.setBounds(240, 50, 180, 120);
		producto2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(producto2);

		selectProducto2 = new JRadioButton("Horno microondas $2100");
		selectProducto2.setBounds(245, 170, 180, 40);
		selectProducto2.addActionListener(event -> habBotonCalcularTot());
		add(selectProducto2);

		// Producto 3
		producto3 = new JLabel();
		producto3.setIcon(new ImageIcon("imagenes/lego.jpeg"));
		producto3.setBounds(450, 50, 180, 120);
		producto3.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(producto3);

		selectProducto3 = new JRadioButton("Lego Technic $600");
		selectProducto3.setBounds(470, 170, 180, 40);
		selectProducto3.addActionListener(event -> habBotonCalcularTot());
		add(selectProducto3);

		// Producto 4
		producto4 = new JLabel();
		producto4.setIcon(new ImageIcon("imagenes/bicicleta.jpeg"));
		producto4.setBounds(30, 220, 180, 120);
		producto4.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(producto4);

		selectProducto4 = new JRadioButton("Bicicleta 24'' $5000");
		selectProducto4.setBounds(40, 340, 180, 40);
		selectProducto4.addActionListener(event -> habBotonCalcularTot());
		add(selectProducto4);

		// Producto 5
		producto5 = new JLabel();
		producto5.setIcon(new ImageIcon("imagenes/ventilador.jpeg"));
		producto5.setBounds(240, 220, 180, 120);
		producto5.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(producto5);

		selectProducto5 = new JRadioButton("Ventilador negro $400");
		selectProducto5.setBounds(260, 340, 180, 40);
		selectProducto5.addActionListener(event -> habBotonCalcularTot());
		add(selectProducto5);

		// Producto 6
		producto6 = new JLabel();
		producto6.setIcon(new ImageIcon("imagenes/brazalete.jpeg"));
		producto6.setBounds(450, 220, 180, 120);
		producto6.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		add(producto6);

		selectProducto6 = new JRadioButton("Brazalete dama $700");
		selectProducto6.setBounds(460, 340, 180, 40);
		selectProducto6.addActionListener(event -> habBotonCalcularTot());
		add(selectProducto6);

		/*
		 * Boton calcular total.
		 * Muestra con etiquetas subtotal, IVA y total.
		 */
		calcularTotal = new JButton("Total de compra");
		calcularTotal.setBounds(680, 75, 150, 30);
		calcularTotal.setEnabled(false); // Inicialmente deshabilitado, hasta la eleccion de un producto.
		calcularTotal.addActionListener(event -> {
			calcularTotales();
			// Habilita los JRadioButton para pago en efectivo y pago con tarjeta.
			pagoEfectivo.setEnabled(true);
			pagoTarjeta.setEnabled(true);
		});
		add(calcularTotal);

		// Etiquetas del subtotal, IVA y total.
		subtotalLabel = new JLabel("Subtotal: $0.00");
		subtotalLabel.setBounds(720, 130, 200, 20);
		add(subtotalLabel);

		ivaLabel = new JLabel("IVA: $0.00");
		ivaLabel.setBounds(720, 150, 200, 20);
		add(ivaLabel);

		totalLabel = new JLabel("Total: $0.00");
		totalLabel.setBounds(720, 170, 200, 20);
		add(totalLabel);

		// Etiqueta para separar el total de la forma de pago.
		formaPagoLabel = new JLabel("> > > > > Forma de pago < < < < <");
		formaPagoLabel.setBounds(670, 200, 200, 30);
		add(formaPagoLabel);

		/*
		 * ButtonGroup de formas de pago. Garantiza que solo una sea elegida por el
		 * usuario.
		 */
		formaPago = new ButtonGroup();

		// Pago con efectivo
		pagoEfectivo = new JRadioButton("Efectivo");
		pagoEfectivo.setBounds(660, 230, 100, 20);
		// Inicialmente inhabilitado. Se habilita al clickear el boton calcularTotal.
		pagoEfectivo.setEnabled(false);
		pagoEfectivo.addActionListener(event -> {
			elegirFormaPago();
			iNumeroTarjeta.setText(""); /*
										 * Limpia el JTextField "iNumeroTarjeta".
										 * En caso de que se haya seleccionado primero pagoTarjeta,
										 * y se hayan ingresado valores.
										 */
		});
		add(pagoEfectivo);

		// Entrada de efectivo
		efectivoLabel = new JLabel("Efectivo:");
		efectivoLabel.setBounds(660, 255, 80, 20);
		add(efectivoLabel);

		// Campo para ingresar la cantidad de efectivo.
		cantidadEfectivo = new JTextField();
		cantidadEfectivo.setBounds(710, 255, 150, 20);
		cantidadEfectivo.setEnabled(false); // Inhabilitado por defecto si no seleccionan efectivo.
		add(cantidadEfectivo);

		// Muestra el cambio después de finalizar el proceso.
		cambioLabel = new JLabel("Cambio: $0.00");
		cambioLabel.setBounds(660, 280, 200, 20);
		add(cambioLabel);

		// Pago con tarjeta
		pagoTarjeta = new JRadioButton("Tarjeta");
		pagoTarjeta.setBounds(660, 320, 100, 20);
		// Inicialmente inhabilitado. Se habilita al clickear el boton calcularTotal.
		pagoTarjeta.setEnabled(false);
		pagoTarjeta.addActionListener(event -> {
			elegirFormaPago();
			cantidadEfectivo.setText(""); /*
											 * Limpia el JTextField "cantidadEfectivo".
											 * En caso de que se haya seleccionado primero pagoEfectivo,
											 * y se hayan ingresado valores.
											 */
		});
		add(pagoTarjeta);

		numTarjetaLabel = new JLabel("Ingrese el numero de tarjeta:");
		numTarjetaLabel.setBounds(660, 340, 180, 20);
		add(numTarjetaLabel);

		// Campo para ingresar el numero de tarjeta.
		iNumeroTarjeta = new JTextField();
		iNumeroTarjeta.setBounds(660, 360, 180, 20);
		// Inicialmente inhabilitado. Se habilita al clickear el boton calcularTotal.
		iNumeroTarjeta.setEnabled(false);
		add(iNumeroTarjeta);

		formaPago.add(pagoEfectivo);
		formaPago.add(pagoTarjeta);

		/*
		 * Modificar la visibilidad de la caja de texto para efectivo segun la opcion
		 * seleccionada.
		 */
		pagoEfectivo.addActionListener(event -> {
			cantidadEfectivo.setEnabled(true);
			iNumeroTarjeta.setEnabled(false);
		});

		/*
		 * Modificar la visibilidad de la caja de texto para efectivo segun la opcion
		 * seleccionada.
		 */
		pagoTarjeta.addActionListener(event -> {
			cantidadEfectivo.setEnabled(false);
			iNumeroTarjeta.setEnabled(true);
		});

		// Boton para finalizar la compra.
		procederCompra = new JButton("Pagar");
		procederCompra.setBounds(680, 390, 150, 30);
		procederCompra.setEnabled(false);
		procederCompra.addActionListener(event -> finalizarCompra());
		add(procederCompra);

		// Boton salir
		botonSalir = new JButton("Salir");
		botonSalir.setBounds(405, 420, 120, 40);
		botonSalir.addActionListener(event -> System.exit(0));
		add(botonSalir);

	}

	/*
	 * Evento para habilitar el boton calcularTotal. Si en el proceso es
	 * deseleccionado un producto, se deshabilitan todos los botones
	 * (excepto el de salir) y se limpian los campos.
	 */
	private void habBotonCalcularTot() {
		if (selectProducto1.isSelected() || selectProducto2.isSelected() ||
				selectProducto3.isSelected() || selectProducto4.isSelected() ||
				selectProducto5.isSelected() || selectProducto6.isSelected()) {
			calcularTotal.setEnabled(true);
		} else {
			calcularTotal.setEnabled(false);
			subtotalLabel.setText("Subtotal: $0.00");
			ivaLabel.setText("IVA: $0.00");
			totalLabel.setText("Total: $0.00");

			pagoEfectivo.setEnabled(false);
			cantidadEfectivo.setEnabled(false);
			cantidadEfectivo.setText("");

			pagoTarjeta.setEnabled(false);
			iNumeroTarjeta.setEnabled(false);
			iNumeroTarjeta.setText("");

			procederCompra.setEnabled(false);
		}
	}

	/*
	 * Suma el precio de cada producto (declarados en el array "precios").
	 * Despues se calculan subtotal, IVA y total, mostrados en la pantalla.
	 */
	private void calcularTotales() {
		subtotal = 0;
		if (selectProducto1.isSelected())
			subtotal += precios[0];
		if (selectProducto2.isSelected())
			subtotal += precios[1];
		if (selectProducto3.isSelected())
			subtotal += precios[2];
		if (selectProducto4.isSelected())
			subtotal += precios[3];
		if (selectProducto5.isSelected())
			subtotal += precios[4];
		if (selectProducto6.isSelected())
			subtotal += precios[5];

		iva = subtotal * 0.16;
		total = subtotal + iva;

		subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
		ivaLabel.setText(String.format("IVA: $%.2f", iva));
		totalLabel.setText(String.format("Total: $%.2f", total));
	}

	// Si no es elegida una forma de pago, el boton procederCompra no se habilita.
	private void elegirFormaPago() {
		if (pagoEfectivo.isSelected() || pagoTarjeta.isSelected()) {
			procederCompra.setEnabled(true);
		} else {
			procederCompra.setEnabled(false);
		}
	}

	// Validaciones para finalizar la compra.
	private void finalizarCompra() {
		if (pagoEfectivo.isSelected()) {
			try {
				// Convierte en variable double al efectivo para continuar.
				double efectivo = Double.parseDouble(cantidadEfectivo.getText());
				// No procede si el efectivo es 0 o es un valor negativo.
				if (efectivo <= 0) {
					JOptionPane.showMessageDialog(this, "Introduce un monto válido mayor a 0.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (efectivo < total) {
					// Muestra un mensaje en caso de que el efectivo ingresado sea menor al total a
					// pagar.
					JOptionPane.showMessageDialog(this, "Efectivo insuficiente.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Calcula el cambio a dar, en caso de que lo haya.
				double cambio = efectivo - total;
				cambioLabel.setText(String.format("Cambio: $%.2f", cambio));
				JOptionPane.showMessageDialog(this, "¡Compra realizada con éxito!");
				System.exit(0);
			} catch (NumberFormatException texto) {
				JOptionPane.showMessageDialog(this, "Introduce una cantidad válida.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (pagoTarjeta.isSelected()) {
			try {
				// Convierte a string el numero de tarjeta.
				String numTarjeta = iNumeroTarjeta.getText();

				// Valida si el campo está vacío
				if (numTarjeta == null || numTarjeta.equals("")) {
					JOptionPane.showMessageDialog(this, "Ingrese el número de tarjeta para proceder.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Valida que solo contenga numeros, debido a que es una string.
				if (!numTarjeta.matches("\\d+")) { // \\d+ solo permite dígitos
					JOptionPane.showMessageDialog(this, "El número de tarjeta solo debe contener números.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Valida que tenga la longitud típica de una tarjeta (generalmente 16 dígitos)
				if (numTarjeta.length() != 16) {
					JOptionPane.showMessageDialog(this, "El número de tarjeta debe tener 16 dígitos.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Si todas las validaciones son correctas
				JOptionPane.showMessageDialog(this, "Pago con tarjeta aprobado.");
				System.exit(0);
			} catch (Exception numeros) {
				JOptionPane.showMessageDialog(this, "Ocurrió un error al validar el número de tarjeta.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}