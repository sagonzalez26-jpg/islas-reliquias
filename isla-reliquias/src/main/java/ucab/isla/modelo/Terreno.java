package ucab.isla.modelo;

/**
 * Tipos de casilla del tablero. Cada tipo conoce su símbolo y
 * cuántos puntos de acción cuesta entrar en él.
 */
public enum Terreno {
    CAMPAMENTO('C', 1, "Base Rebelde"),
    SENDERO('S', 1, "Llanura de Tatoine"),
    PANTANO('P', 2, "Pantano de Dagobah"),
    TEMPLO('T', 1, "Templo Jedi"),
    TEMPLO_COFRE('X', 1, "Boveda Sith");

    private final char simbolo;
    private final int costoEntrada;
    private final String nombre;

    Terreno(char simbolo, int costoEntrada, String nombre) {
        this.simbolo = simbolo;
        this.costoEntrada = costoEntrada;
        this.nombre = nombre;
    }

    /** @return la letra que representa al terreno (C, S, P, T o X) */
    public char getSimbolo() {
        return simbolo;
    }

    /** @return puntos de acción que cuesta entrar en este terreno */
    public int getCostoEntrada() {
        return costoEntrada;
    }

    /** @return nombre legible para mostrar en pantalla */
    public String getNombre() {
        return nombre;
    }

    /**
     * Busca el terreno que corresponde a un símbolo.
     *
     * @param simbolo letra recibida (por ejemplo, desde la API)
     * @return el terreno asociado
     * @throws IllegalArgumentException si el símbolo no existe
     */
    public static Terreno desdeSimbolo(char simbolo) {
        for (Terreno t : Terreno.values()) {
            if (t.simbolo == simbolo) {
                return t;
            }
        }
        throw new IllegalArgumentException("Símbolo de terreno inválido: " + simbolo);
    }
}