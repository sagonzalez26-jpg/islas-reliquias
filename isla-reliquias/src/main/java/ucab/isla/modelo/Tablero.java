package ucab.isla.modelo;

import java.util.ArrayList;

/** Tablero rectangular de casillas. El tamaño se deduce de los datos recibidos. */
public class Tablero {
    private final Casilla[][] casillas;
    private final int filas;
    private final int columnas;

    /**
     * Crea el tablero a partir de una fila de texto por cada fila del mapa.
     * Ejemplo: {"STSPS", "PSSXS", ...} donde cada letra es el símbolo de un Terreno.
     *
     * @param mapa filas del tablero, todas de igual longitud
     * @throws IllegalArgumentException si el mapa está vacío o es irregular
     */
    public Tablero(String[] mapa) {
        if (mapa == null || mapa.length == 0) {
            throw new IllegalArgumentException("El mapa no puede estar vacío");
        }
        this.filas = mapa.length;
        this.columnas = mapa[0].length();
        this.casillas = new Casilla[filas][columnas];

        for (int f = 0; f < filas; f++) {
            if (mapa[f].length() != columnas) {
                throw new IllegalArgumentException("Todas las filas deben tener el mismo largo");
            }
            for (int c = 0; c < columnas; c++) {
                Terreno t = Terreno.desdeSimbolo(mapa[f].charAt(c));
                casillas[f][c] = new Casilla(f, c, t);
            }
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    /** @return true si la posición existe dentro del tablero */
    public boolean estaDentro(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    /**
     * @return la casilla en esa posición, o null si está fuera del tablero
     */
    public Casilla getCasilla(int fila, int columna) {
        if (!estaDentro(fila, columna)) {
            return null;
        }
        return casillas[fila][columna];
    }

    /**
     * Busca una casilla por su coordenada, por ejemplo "C3" o "c3".
     *
     * @return la casilla, o null si el formato es inválido o queda fuera del mapa
     */
    public Casilla getCasilla(String coordenada) {
        if (coordenada == null) {
            return null;
        }
        String texto = coordenada.trim().toUpperCase();
        if (!texto.matches("[A-Z][0-9]+")) {
            return null;
        }
        int columna = texto.charAt(0) - 'A';
        int fila = Integer.parseInt(texto.substring(1)) - 1;
        return getCasilla(fila, columna);
    }

    /** @return las casillas vecinas arriba, abajo, izquierda y derecha (sin diagonales) */
    public ArrayList<Casilla> getVecinas(Casilla casilla) {
        int[] difFila = {-1, 1, 0, 0};
        int[] difCol = {0, 0, -1, 1};
        ArrayList<Casilla> vecinas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Casilla v = getCasilla(casilla.getFila() + difFila[i],
                                   casilla.getColumna() + difCol[i]);
            if (v != null) {
                vecinas.add(v);
            }
        }
        return vecinas;
    }

    /** @return true si b es vecina ortogonal de a */
    public boolean sonVecinas(Casilla a, Casilla b) {
        int distancia = Math.abs(a.getFila() - b.getFila())
                      + Math.abs(a.getColumna() - b.getColumna());
        return distancia == 1;
    }

    /** @return la primera casilla con ese terreno, o null si no existe (útil para C y X) */
    public Casilla buscarTerreno(Terreno terreno) {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (casillas[f][c].getTerreno() == terreno) {
                    return casillas[f][c];
                }
            }
        }
        return null;
    }
}