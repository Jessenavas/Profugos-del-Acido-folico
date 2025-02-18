import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Autocinema {
    private Horario horario;
    private SalaVisual salaVisual;

    public Autocinema() {
        this.salaVisual = new SalaVisual();
    }

    public void establecerHorario(String dia, String turno) {
        horario = new Horario(dia, turno);
    }

    public void mostrarInterfaz() {
        JFrame marco = new JFrame("Autocinema");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(1000, 600);
        marco.setMinimumSize(new Dimension(800, 500));
        marco.setLocationRelativeTo(null);
        marco.setLayout(new BorderLayout(10, 10));
        marco.getContentPane().setBackground(new Color(30, 30, 30));

        // Panel de cartelera
        JPanel carteleraPanel = new JPanel();
        carteleraPanel.setLayout(new BorderLayout(5, 5));
        carteleraPanel.setBackground(new Color(50, 50, 50));
        carteleraPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        carteleraPanel.setPreferredSize(new Dimension(280, 500));

        JLabel etiquetaCartelera = new JLabel("Cartelera", SwingConstants.CENTER);
        etiquetaCartelera.setFont(new Font("Arial", Font.BOLD, 20));
        etiquetaCartelera.setForeground(Color.WHITE);
        carteleraPanel.add(etiquetaCartelera, BorderLayout.NORTH);

        JTextArea areaCartelera = new JTextArea(obtenerCarteleraActual());
        areaCartelera.setEditable(false);
        areaCartelera.setBackground(new Color(70, 70, 70));
        areaCartelera.setForeground(Color.WHITE);
        areaCartelera.setFont(new Font("Arial", Font.PLAIN, 14));
        carteleraPanel.add(new JScrollPane(areaCartelera), BorderLayout.CENTER);
        marco.add(carteleraPanel, BorderLayout.WEST);

        // Panel central (sala visual)
        JPanel contenedorSala = new JPanel(new GridBagLayout());
        contenedorSala.setBackground(new Color(40, 40, 40));
        contenedorSala.add(salaVisual);
        marco.add(contenedorSala, BorderLayout.CENTER);

        // Panel derecho (compra de snacks)
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBackground(new Color(50, 50, 50));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton botonSnacks = new JButton("Comprar Snacks");
        botonSnacks.setFont(new Font("Arial", Font.BOLD, 16));
        botonSnacks.setBackground(new Color(255, 204, 102));
        botonSnacks.setForeground(Color.BLACK);
        botonSnacks.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSnacks.addActionListener(e -> {
            CompraSnacks compraSnacks = new CompraSnacks();
            compraSnacks.setTitle("Comprar Snacks");
            compraSnacks.setBounds(100, 100, 1050, 550);
            compraSnacks.setVisible(true);
        });

        panelDerecho.add(Box.createVerticalGlue());
        panelDerecho.add(botonSnacks);
        panelDerecho.add(Box.createVerticalGlue());
        marco.add(panelDerecho, BorderLayout.EAST);

        marco.setVisible(true);
    }

    private String obtenerCarteleraActual() {
        LocalDateTime ahora = LocalDateTime.now();
        String dia = ahora.format(DateTimeFormatter.ofPattern("EEEE"));
        String hora = ahora.format(DateTimeFormatter.ofPattern("HH:mm"));
        return "Día: " + dia + "\nHora: " + hora
                + "\n\nPelículas:\n- Acción: Rápidos y Furiosos\n- Animada: Toy Story\n- Terror: El Conjuro";
    }

    public static void main(String[] args) {
        Autocinema autocinema = new Autocinema();
        autocinema.establecerHorario("domingo", "tarde");
        autocinema.mostrarInterfaz();
    }
}