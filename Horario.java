public class Horario {
    private String dia;
    private String turno;

    public Horario(String dia, String turno) {
        this.dia = dia;
        this.turno = turno;
    }

    public String obtenerTipoPelicula() {
        return "Película de ejemplo para " + dia + " en el turno de " + turno;
    }
}