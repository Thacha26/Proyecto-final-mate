package unam.ciencias.matematicas.proyectofinal.coordenada;

/**
 * Modela un punto en el sistema de coordenadas Cilíndricas (r, theta, z).
 */
public class Cilindricas implements Coordenada {
    private final double radio;  // Distancia radial en el plano XY (r)
    private final double angulo; // Ángulo (theta)
    private final double altura; // Altura (z)

    /**
     * Constructor para coordenadas Cilíndricas.
     */
    public Cilindricas(double radio, double angulo, double altura) {
        this.radio = radio;
        this.angulo = angulo;
        this.altura = altura;
    }

    // Fórmulas de Cilíndrica (r, theta, z) a Cartesiano (x, y, z)
    @Override
    public Cartesiana aCartesiana() {
        double x = radio * Math.cos(angulo);
        double y = radio * Math.sin(angulo);
        double z = altura; 
        
        return new Cartesiana(x, y, z);
    }

    public double getRadio() { return radio; }
    public double getAngulo() { return angulo; }
    public double getAltura() { return altura; }

    @Override
    public String toString() {
        return String.format("Cilíndrica(r=%.4f, \u03B8=%.4f, z=%.4f)", radio, angulo, altura);
    }
}