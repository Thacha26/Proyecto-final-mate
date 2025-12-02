package unam.ciencias.matematicas.proyectofinal.coordenada;

/**
 * Modela un punto en el sistema de coordenadas Polares (r, theta, z).
 * Nota: En la mayoría de contextos, esto es idéntico a cilíndricas. Aquí se mantienen separadas por modularidad.
 */
public class Polares implements Coordenada {
    private final double radio;  // Distancia radial en el plano XY (r)
    private final double angulo; // Ángulo (theta)
    private final double altura; // Altura (z)

    /**
     * Constructor para coordenadas Polares.
     */
    public Polares(double radio, double angulo, double altura) {
        this.radio = radio;
        this.angulo = angulo;
        this.altura = altura;
    }

    // --- Conversión a Cartesiano ---
    @Override
    public Cartesiana aCartesiana() {
        // Fórmulas de Polar (r, theta, z) a Cartesiano (x, y, z)
        double x = radio * Math.cos(angulo);
        double y = radio * Math.sin(angulo);
        double z = altura; 
        
        return new Cartesiana(x, y, z);
    }

    // --- Getters específicos ---
    public double getRadio() { return radio; }
    public double getAngulo() { return angulo; }
    public double getAltura() { return altura; }

    @Override
    public String toString() {
        return String.format("Polar(r=%.4f, \u03B8=%.4f, z=%.4f)", radio, angulo, altura);
    }
}