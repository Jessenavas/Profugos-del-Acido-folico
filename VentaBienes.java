
/******
 * @Programa: VentaBienesRaices.java
 * @Autor: Espejel Alvarado Christopher Leny
 * @Fecha: 05/12/2024
 * @Profesor: Adriana Vega Palos
 * @Descripcion: Aplicación para la venta de bienes raíces con cotización detallada y opciones adicionales.
 ******/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class VentaBienes extends JFrame {

	// Componentes de la interfaz gráfica
	private JLabel imagenCasa, precioCasa, imagenDepto, precioDepto, imagenTerreno, precioTerreno;
	private JLabel imagenCocina, imagenCajonEstacionamiento;
	private JLabel subtotalLabel, escrituracionLabel, totalLabel, tipoPagoLabel, tipoCompraLabel;
	private JTextArea detallesPropiedades;
	private JButton elegirCasa, elegirDepto, elegirTerreno, botonComprar, botonSalir, botonCocina,
			botonCajonEstacionamiento;
	private JComboBox<String> tipoPagoCombo, tipoCompraCombo;

	// Variables de precios
	private final double[] precios = { 2150000, 5550000, 6500000 };
	private final double cocinaEquipada = 30000, cajonEstacionamiento = 80000, escrituracion = 0.06;
	private double precioBaseInmueble = 0.0, extras = 0.0, subtotal = 0.0, total = 0.0;

	public static void main(String[] args) {
		VentaBienes marco = new VentaBienes();
		marco.setTitle("Venta de Bienes Raíces");
		marco.setBounds(100, 40, 950, 650);
		marco.setVisible(true);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public VentaBienes() {
		setLayout(null);

		// Posición inicial de los inmuebles
		int x = 30, y = 30, width = 200, height = 180;

		JLabel testLabel = new JLabel(new ImageIcon("casa.jpg"));
		add(testLabel);

		// Imágenes y botones de inmuebles

		File file = new File("casa.jpg");
		if (!file.exists()) {
			System.out.println("El archivo no existe: " + file.getAbsolutePath());
		}
		imagenCasa = new JLabel(new ImageIcon("casa.jpg"));
		imagenCasa.setBounds(x, y, width, height);
		imagenCasa.setBorder((BorderFactory.createLineBorder(Color.BLACK, 2)));
		add(imagenCasa);

		precioCasa = crearLabel(String.format("Precio: $%.0f", precios[0]), x + 50, y + height + 5, width, 30);
		elegirCasa = crearBoton("Seleccionar Casa", x, y + height + 40, width, 40);
		elegirCasa.addActionListener(event -> {
			seleccionarInmueble(0);
			verDetallesCasa();
		});

		x += 220;
		imagenDepto = new JLabel();
		imagenDepto.setIcon(new ImageIcon("depto.jpg"));
		imagenDepto.setBounds(x, y, width, height);
		imagenDepto.setBorder((BorderFactory.createLineBorder(Color.BLACK, 2)));
		add(imagenDepto);
		precioDepto = crearLabel(String.format("Precio: $%.0f", precios[1]), x + 50, y + height + 5, width, 30);
		elegirDepto = crearBoton("Seleccionar Depto", x, y + height + 40, width, 40);
		elegirDepto.addActionListener(event -> {
			seleccionarInmueble(1);
			verDetallesDepto();
		});

		x += 220;

		imagenTerreno = new JLabel();
		imagenTerreno.setIcon(new ImageIcon("terreno.jpg"));
		imagenTerreno.setBounds(x, y, width, height);
		imagenTerreno.setBorder((BorderFactory.createLineBorder(Color.BLACK, 2)));
		add(imagenTerreno);
		precioTerreno = crearLabel(String.format("Precio: $%.0f", precios[2]), x + 50, y + height + 5, width, 30);
		elegirTerreno = crearBoton("Seleccionar Terreno", x, y + height + 40, width, 40);
		elegirTerreno.addActionListener(event -> {
			seleccionarInmueble(2);
			verDetallesTerreno();
		});

		// Detalles de la propiedad
		detallesPropiedades = new JTextArea("Selecciona un inmueble para ver los detalles...");
		detallesPropiedades.setBounds(95, 320, 500, 130);
		detallesPropiedades.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		detallesPropiedades.setEditable(false);
		add(detallesPropiedades);

		x += 220;

		// Opciones adicionales con imágenes
		imagenCocina = new JLabel();
		imagenCocina.setIcon(new ImageIcon("cocina.jpg"));
		imagenCocina.setBounds(x + 25, 30, 150, 100);
		imagenCocina.setBorder((BorderFactory.createLineBorder(Color.BLACK, 2)));
		add(imagenCocina);
		botonCocina = crearBoton("Agregar Cocina", x + 30, 140, 140, 30);
		botonCocina.addActionListener(event -> agregarCocina());

		imagenCajonEstacionamiento = new JLabel();
		imagenCajonEstacionamiento.setIcon(new ImageIcon("cajon.jpg"));
		imagenCajonEstacionamiento.setBounds(x + 25, 180, 150, 100);
		imagenCajonEstacionamiento.setBorder((BorderFactory.createLineBorder(Color.BLACK, 2)));
		add(imagenCajonEstacionamiento);
		botonCajonEstacionamiento = crearBoton("Agregar Cajon", x + 30, 290, 140, 30);
		botonCajonEstacionamiento.addActionListener(event -> agregarCajonEstacionamiento());

		// Tipos de pago y compra
		tipoPagoLabel = crearLabel("Método de Pago:", x + 25, 330, 150, 30);
		tipoPagoCombo = crearComboBox(new String[] { "Depósito", "Transferencia" }, x + 30, 360, 140, 30);

		tipoCompraLabel = crearLabel("Tipo de Compra:", x + 25, 390, 150, 30);
		tipoCompraCombo = crearComboBox(new String[] { "Contado", "Crédito" }, x + 30, 420, 140, 30);

		// Subtotal, escrituración y total
		subtotalLabel = crearLabel("Subtotal: $0", x + 30, 460, 240, 25);
		escrituracionLabel = crearLabel("Escrituración: $0", x + 30, 485, 240, 25);
		totalLabel = crearLabel("Total: $0", x + 30, 510, 240, 25);

		// Botones principales
		botonComprar = crearBoton("Comprar", x + 25, 540, 150, 40);
		botonComprar.addActionListener(e -> datosUsuarioCompra());
		botonSalir = crearBoton("Salir", 300, 500, 100, 40);
		botonSalir.addActionListener(e -> System.exit(0));
	}

	// Métodos auxiliares

	/**
	 * Crea un JLabel con la configuración especificada.
	 */
	private JLabel crearLabel(String texto, int x, int y, int width, int height) {
		JLabel label = new JLabel(texto);
		label.setBounds(x, y, width, height);
		add(label);
		return label;
	}

	/**
	 * Crea un JButton con la configuración especificada.
	 */
	private JButton crearBoton(String texto, int x, int y, int width, int height) {
		JButton button = new JButton(texto);
		button.setBounds(x, y, width, height);
		add(button);
		return button;
	}

	/**
	 * Crea un JComboBox con las opciones especificadas.
	 */
	private JComboBox<String> crearComboBox(String[] opciones, int x, int y, int width, int height) {
		JComboBox<String> comboBox = new JComboBox<>(opciones);
		comboBox.setBounds(x, y, width, height);
		add(comboBox);
		return comboBox;
	}

	// Actualiza los totales de compra cada que se selecciona un inmueble.
	private void seleccionarInmueble(int index) {
		precioBaseInmueble = precios[index];
		extras = 0.0; // Reinicia los extras
		actualizarTotales();
	}

	// Eventos que añaden la descripcion de los inmuebles al JTextArea
	private void verDetallesCasa() {
		detallesPropiedades.setText(String.format(" Seleccionaste: Casa \n Precio Base: $%.0f" +
				"\n\n Amplia casa de 3 habitaciones y 2 baños ubicada en unz zona tranquila de facil acceso." +
				"\n Cuenta con jardin trasero y cochera par 2 autos. Cerca de escuelas, parques y centros "
				+ "\n comerciales.", precios[0]));
	}

	private void verDetallesDepto() {
		detallesPropiedades.setText(String.format(" Seleccionaste: Departamento \n Precio Base: $%.0f" +
				"\n\n Moderno departamento de 2 recamaras y 1 baño, perfecto para parejas. Cuenta con un" +
				"\n balcon con vista a la ciudad y roof garden." +
				"\n Ubicado en un edificio con amenidades como gimnasio, alberca y seguridad 24/7", precios[1]));
	}

	private void verDetallesTerreno() {
		detallesPropiedades.setText(String.format(" Seleccionaste: Terreno \n Precio Base: $%.0f" +
				"\n\n Terreno de 500 metros cuadrados en excelente ubicacion, ideal para proyectos" +
				"\n residenciales o comerciales. Se encuentra en una zona de alta plusvalia. Tiene" +
				"\n acceso a servicios basicos como agua, luz y drenaje", precios[2]));
	}

	// Agrega el costo de la cocina si se trata de la casa o del departamento.
	private void agregarCocina() {
		if (precioBaseInmueble == precios[0] || precioBaseInmueble == precios[1]) {
			extras += cocinaEquipada;
			actualizarTotales();
		} else {
			// No permite que sea elegida si el inmueble es el terreno
			JOptionPane.showMessageDialog(this, "La cocina equipada solo está disponible para casas y departamentos.");
		}
	}

	// Agrega el costo del cajón de estacionamiento si es un departamento.
	private void agregarCajonEstacionamiento() {
		if (precioBaseInmueble == precios[1]) {
			extras += cajonEstacionamiento;
			actualizarTotales();
		} else {
			// No permite su seleccion si el inmueble es la casa o el terreno.
			JOptionPane.showMessageDialog(this, "El cajón de estacionamiento solo está disponible para departamentos.");
		}
	}

	/*
	 * Actualiza los totales (subtotal, escrituracion y total), de acuerdo a las
	 * interacciones del usuario con los botones.
	 */
	private void actualizarTotales() {
		subtotal = precioBaseInmueble + extras;
		double gastosEscrituracion = precioBaseInmueble * escrituracion;
		total = precioBaseInmueble + gastosEscrituracion + extras;

		subtotalLabel.setText(String.format("Subtotal: $%.0f", subtotal));
		escrituracionLabel.setText(String.format("Escrituración: $%.0f", gastosEscrituracion));
		totalLabel.setText(String.format("Total: $%.0f", total));
	}

	// Solicita los datos del usuario y confirma la compra.
	private void datosUsuarioCompra() {
		// Si se presiona el boton "Comprar" y aún no se ha seleccionado un inmueble.
		if (total == 0.0) {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un inmueble antes de comprar.");
			return;
		}

		// Validaciones para el nombre.
		String nombre;
		while (true) {
			nombre = JOptionPane.showInputDialog(this, "Ingresa tu nombre:");
			if (nombre == null) {
				JOptionPane.showMessageDialog(this, "La compra ha sido cancelada.");
				return;
			}
			nombre = nombre.toUpperCase().replaceAll("[ÁÀÂÄ]", "A")
					.replaceAll("[ÉÈÊË]", "E")
					.replaceAll("[ÍÌÎÏ]", "I")
					.replaceAll("[ÓÒÔÖ]", "O")
					.replaceAll("[ÚÙÛÜ]", "U")
					.replaceAll("[^A-Z ]", "");

			if (!nombre.trim().isEmpty()) {
				break;
			} else {
				JOptionPane.showMessageDialog(this,
						"Por favor, ingresa un nombre válido sin acentos, números o símbolos.");
			}
		}

		// Validaciones para el correo.
		String correo;
		while (true) {
			correo = JOptionPane.showInputDialog(this, "Ingresa tu correo:");
			if (correo == null) {
				JOptionPane.showMessageDialog(this, "La compra ha sido cancelada.");
				return;
			}
			if (correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
				break;
			} else {
				JOptionPane.showMessageDialog(this, "Por favor, ingresa un correo válido.");
			}
		}

		// Validaciones para el telefono.
		String telefono;
		while (true) {
			telefono = JOptionPane.showInputDialog(this, "Ingresa tu teléfono:");
			if (telefono == null) {
				JOptionPane.showMessageDialog(this, "La compra ha sido cancelada.");
				return;
			}
			if (telefono.matches("^\\d{10}$") && !telefono.startsWith("-")) {
				break;
			} else {
				JOptionPane.showMessageDialog(this,
						"Por favor, ingresa un teléfono válido de 10 dígitos sin letras ni números negativos.");
			}
		}

		// Pide confirmacion al usuario para la compra.
		int confirmacion = JOptionPane.showConfirmDialog(this,
				"¿Confirmas tu compra?\nNombre: " + nombre + "\nCorreo: " + correo + "\nTelefono: " + telefono,
				"Confirmar Compra", JOptionPane.YES_NO_OPTION);

		// Si el usuario confirma, se muestra mensaje. En caso contrario se regresa a la
		// ventana inicial.
		if (confirmacion == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this,
					"Gracias  " + nombre + ",  un ejecutivo se pondra en contacto contigo"
							+ "\n al correo:  " + correo + "   o al telefono  " + telefono + ".");
			System.exit(0);
		}
	}
}
