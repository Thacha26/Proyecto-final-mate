package unam.ciencias.matematicas.proyectofinal.conversion;

import unam.ciencias.matematicas.proyectofinal.coordenada.*;

/**
 * Clase que maneja la lógica de conversión entre cualquier tipo de coordenada.
 * Aplica el principio de inversión de dependencias: solo trabaja con la interfaz Coordenada.
 */
public class Convertidor {

    /**
     * Realiza la conversión de cualquier coordenada de origen a un tipo de destino específico.
     * La estrategia es: Origen -> Cartesiana -> Destino.
     * * @param origen La coordenada a convertir (implementa Coordenada).
     * @param tipoDestino La Clase del tipo de coordenada deseado (ej. Polares.class).
     * @return Una nueva instancia del tipo de coordenada de destino.
     * @throws IllegalArgumentException si el tipo de destino no es conocido.
     */
    public Coordenada convertir(Coordenada origen, Class<?> tipoDestino) {
        // Paso 1: Convertir el origen a la base Cartesiana (x, y, z)
        // Esto funciona sin importar el tipo de 'origen' (LSP).
        Cartesiana cartesiana = origen.aCartesiana();

        double x = cartesiana.getX();
        double y = cartesiana.getY();
        double z = cartesiana.getZ();

        // Paso 2: Aplicar la conversión inversa desde Cartesiano al destino deseado.
        
        if (tipoDestino.equals(Cartesiana.class)) {
            // Caso 1: El destino ya es Cartesiano (se devuelve la versión convertida)
            return cartesiana;
        } 
        
        if (tipoDestino.equals(Polares.class)) {
            // Caso 2: De Cartesiano (x, y, z) a Polar (r, theta, z)
            double radio = Math.sqrt(x*x + y*y);
            // atan2 es mejor que atan ya que maneja los 4 cuadrantes y divisiones por cero.
            double angulo = Math.atan2(y, x);
            double altura = z;
            return new Polares(radio, angulo, altura);
        } 
        
        if (tipoDestino.equals(Cilindricas.class)) {
            // Caso 3: De Cartesiano (x, y, z) a Cilíndrica (r, theta, z)
            // (La misma fórmula que Polar en 3D)
            double radio = Math.sqrt(x*x + y*y);
            double angulo = Math.atan2(y, x);
            double altura = z;
            return new Cilindricas(radio, angulo, altura);
        }
        
        if (tipoDestino.equals(Esfericas.class)) {
            // Caso 4: De Cartesiano (x, y, z) a Esférica (rho, theta, phi)
            double rho = Math.sqrt(x*x + y*y + z*z);
            double theta = Math.atan2(y, x);

            // Evitar división por cero si rho es 0 (punto en el origen)
            double phi = (rho == 0) ? 0 : Math.acos(z / rho);
            
            return new Esfericas(rho, theta, phi);
        }
        
        // Si la clase de destino no es reconocida, lanza una excepción
        throw new IllegalArgumentException("Tipo de coordenada de destino no soportado: " + tipoDestino.getSimpleName());
    }
}