package ucab.isla.modelo;

/** Una casilla del tablero: su posición y su tipo de terreno. */
public class Casilla {
    private final int fila;      // índice desde 0
    private final int columna;   // índice desde 0
    private final Terreno terreno;

    public Casilla(int fila, int columna, Terreno terreno) {
        this.fila = fila;
        this.columna = columna;
        this.terreno = terreno;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Terreno getTerreno() {
        return terreno;
    }

    /** @return la coordenada legible, por ejemplo "C3" (columna letra, fila número desde 1) */
    public String getCoordenada() {
        char letra = (char) ('A' + columna);
        return "" + letra + (fila + 1);
    }

    @Override
    public String toString() {
        return getCoordenada() + " " + terreno.getSimbolo();
    }
}