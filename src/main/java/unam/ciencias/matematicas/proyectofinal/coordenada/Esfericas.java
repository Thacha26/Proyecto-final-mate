package unam.ciencias.matematicas.proyectofinal.coordenada;

/**
 * Modela un punto en el sistema de coordenadas Esféricas (rho, theta, phi).
 * rho = distancia al origen
 * theta = ángulo azimutal (respecto al eje X)
 * phi = ángulo polar (respecto al eje Z positivo)
 */
public class Esfericas implements Coordenada {
    private final double rho;  // Distancia al origen (rho)
    private final double theta; // Ángulo azimutal (theta)
    private final double phi;   // Ángulo polar (phi)

    /**
     * Constructor para coordenadas Esféricas.
     */
    public Esfericas(double rho, double theta, double phi) {
        this.rho = rho;
        this.theta = theta;
        this.phi = phi;
    }

    // --- Conversión a Cartesiano ---
    @Override
    public Cartesiana aCartesiana() {
        // Fórmulas de Esférica (rho, theta, phi) a Cartesiano (x, y, z)
        // Math.sin(phi) es el radio proyectado en el plano XY
        double radioXY = rho * Math.sin(phi);
        
        double x = radioXY * Math.cos(theta);
        double y = radioXY * Math.sin(theta);
        double z = rho * Math.cos(phi); 
        
        return new Cartesiana(x, y, z);
    }

    // --- Getters específicos ---
    public double getRho() { return rho; }
    public double getTheta() { return theta; }
    public double getPhi() { return phi; }

    @Override
    public String toString() {
        return String.format("Esférica(\u03C1=%.4f, \u03B8=%.4f, \u03C6=%.4f)", rho, theta, phi);
    }
}