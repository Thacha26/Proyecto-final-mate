package unam.ciencias.matematicas.proyectofinal.coordenada;

public class Coordenadas {

/**
 * Interfaz común para todas las coordenadas.
 * Permite tratarlas de manera uniforme y aplicar polimorfismo.
 */
public interface Coordenada {

    /**
     * Regresa la dimensión de la coordenada (2D o 3D).
     * @return número de dimensiones.
     */
    public int dimension();

    /**
     * Convierte la coordenada a su representación en cartesianas.
     * @return coordenada equivalente en cartesianas.
     */
    public Cartesiana aCartesianas();

    /**
     * Regresa una representación en cadena de la coordenada.
     * @return representación legible.
     */
    @Override public String toString();
}


}
