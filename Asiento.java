import java.awt.*;

public class Asiento {
    private boolean ocupado;

    public Asiento() {
        this.ocupado = false; // Inicialmente libre
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void ocupar() {
        this.ocupado = true;
    }

    public void liberar() {
        this.ocupado = false;
    }

    public Color getColor() {
        return ocupado ? Color.RED : Color.GREEN; // Rojo si ocupado, verde si libre
    }
}
