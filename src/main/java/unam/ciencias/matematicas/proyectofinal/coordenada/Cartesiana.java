package unam.ciencias.matematicas.proyectofinal.coordenada;

/**
 * Modela un punto en el sistema de coordenadas Cartesiano (x, y, z).
 */
public class Cartesiana implements Coordenada {
    private final double x;
    private final double y;
    private final double z;

    /**
     * Constructor para coordenadas Cartesianas.
     * @param x Componente x.
     * @param y Componente y.
     * @param z Componente z.
     */
    public Cartesiana(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Cartesiana aCartesiana() {
        return this;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return String.format("Cartesiana(x=%.4f, y=%.4f, z=%.4f)", x, y, z);
    }
}