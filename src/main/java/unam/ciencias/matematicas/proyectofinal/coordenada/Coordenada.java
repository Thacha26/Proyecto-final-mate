package unam.ciencias.matematicas.proyectofinal.coordenada;

/**
 * Interfaz fundamental para todas las clases de coordenadas.
 * Obliga a cada sistema a saber cómo convertirse a la base Cartesiana (x, y, z),
 * lo que centraliza la lógica de conversión en el Convertidor.
 */
public interface Coordenada {

    /**
     * Devuelve una nueva instancia de Cartesiana con los valores de la coordenada actual.
     * @return Objeto Cartesiana equivalente.
     */
    Cartesiana aCartesiana();

    /**
     * Devuelve la representación en cadena de la coordenada (ejemplo: "(r=5, theta=0.5, z=2)").
     * @return Cadena con los valores.
     */
    String toString();
}