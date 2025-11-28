package unam.ciencias.matematicas.proyectofinal.coordenadas;


/**
 * Clase que representa coordenadas cartesianas (x, y, z).
 */
public class Cartesianas implements Coordenadas {

    /** Valores de la coordenada. */
    private double x, y, z;

    /**
     * Constructor para coordenadas 2D.
     * @param x valor en eje X.
     * @param y valor en eje Y.
     */
    public Cartesianas(double x, double y) {
        // Aquí va su código.
    }

    /**
     * Constructor para coordenadas 3D.
     * @param x valor en eje X.
     * @param y valor en eje Y.
     * @param z valor en eje Z.
     */
    public Cartesianas(double x, double y, double z) {
        // Aquí va su código.
    }

    /** Métodos getX(), getY(), getZ(). */
    public double getX() { /* Aquí va su código. */ }
    public double getY() { /* Aquí va su código. */ }
    public double getZ() { /* Aquí va su código. */ }

    @Override 
    public int dimension() {
        // Aquí va su código: devolver 2 o 3 según corresponda.
    }

    @Override 
    public Cartesianas aCartesianas() {
        // Aquí va su código: devolver this.
    }

    @Override 
    public String toString() {
        // Aquí va su código: devolver "(x, y, z)".
    }
}
