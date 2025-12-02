package unam.ciencias.matematicas.proyectofinal.conversion;

import unam.ciencias.matematicas.proyectofinal.coordenada.*;

/**
 * Clase que maneja la lógica de conversión.
 * Se han ajustado las fórmulas para coincidir con la hoja de apuntes proporcionada:
 * 1. Theta se calcula con atan2 (que equivale a la definición por partes de la hoja).
 * 2. Phi (Esféricas) se calcula usando la relación de arcotangente (r/z).
 */
public class Convertidor {

    public Coordenada convertir(Coordenada origen, Class<?> tipoDestino) {
        // Paso 1: Convertir cualquier origen a Cartesiana (x, y, z)
        Cartesiana cartesiana = origen.aCartesiana();

        double x = cartesiana.getX();
        double y = cartesiana.getY();
        double z = cartesiana.getZ();

        // Si el destino es Cartesiana, ya terminamos
        if (tipoDestino.equals(Cartesiana.class)) {
            return cartesiana;
        } 
        
        // --- CÁLCULOS AUXILIARES (según tu hoja) ---
        // r = sqrt(x^2 + y^2) -> Distancia en el plano XY
        double r = Math.sqrt(x * x + y * y);
        
        // Theta = atan2(y, x) -> Esto cubre TODOS los casos de la llave en tu hoja:
        // Si x>0, y>0 -> arctan(y/x)
        // Si x<0 -> pi + arctan(y/x), etc.
        double theta = Math.atan2(y, x);

        // --- CONVERSIONES ---

        if (tipoDestino.equals(Polares.class)) {
            // Hoja: (r cos θ, r sen θ) -> Ya tenemos r y theta.
            // Asumimos z se mantiene igual para coordenadas cilíndricas/polares 3D
            return new Polares(r, theta, z);
        } 
        
        if (tipoDestino.equals(Cilindricas.class)) {
            // Hoja: Mismas fórmulas que Polares + z
            return new Cilindricas(r, theta, z);
        }
        
        if (tipoDestino.equals(Esfericas.class)) {
            // Hoja: rho = sqrt(x^2 + y^2 + z^2)
            double rho = Math.sqrt(x * x + y * y + z * z);

            // Hoja: phi = arctan(r / z)
            // Usamos atan2(r, z) en lugar de atan(r/z) para evitar división por cero si z=0,
            // pero matemáticamente es la misma fórmula de tu hoja.
            double phi = Math.atan2(r, z);
            
            return new Esfericas(rho, theta, phi);
        }
        
        throw new IllegalArgumentException("Tipo de destino no soportado: " + tipoDestino.getSimpleName());
    }
}