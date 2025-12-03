package unam.ciencias.matematicas.proyectofinal.conversion;

import unam.ciencias.matematicas.proyectofinal.coordenada.*;

/**
 * Clase que maneja la lógica de conversión.
 */
public class Convertidor {

    public Coordenada convertir(Coordenada origen, Class<?> tipoDestino) {
        Cartesiana cartesiana = origen.aCartesiana();

        double x = cartesiana.getX();
        double y = cartesiana.getY();
        double z = cartesiana.getZ();

        if (tipoDestino.equals(Cartesiana.class)) {
            return cartesiana;
        } 
        
        // r = sqrt(x^2 + y^2) -> Distancia en el plano XY
        double r = Math.sqrt(x * x + y * y);
        
        // Theta = atan2(y, x)
        // Si x>0, y>0 -> arctan(y/x)
        // Si x<0 -> pi + arctan(y/x), etc.
        double theta = Math.atan2(y, x);


        if (tipoDestino.equals(Polares.class)) {
            // (r cos θ, r sen θ) -> Ya tenemos r y theta.
            return new Polares(r, theta, z);
        } 
        
        if (tipoDestino.equals(Cilindricas.class)) {
            return new Cilindricas(r, theta, z);
        }
        
        if (tipoDestino.equals(Esfericas.class)) {
            // rho = sqrt(x^2 + y^2 + z^2)
            double rho = Math.sqrt(x * x + y * y + z * z);

            // phi = arctan(r / z)
            // Usamos atan2(r, z) en lugar de atan(r/z) para evitar división por cero si z=0,
            double phi = Math.atan2(r, z);
            
            return new Esfericas(rho, theta, phi);
        }
        
        throw new IllegalArgumentException("Tipo de destino no soportado: " + tipoDestino.getSimpleName());
    }
}