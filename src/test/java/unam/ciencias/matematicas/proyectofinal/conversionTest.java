package unam.ciencias.matematicas.proyectofinal;

import org.junit.jupiter.api.Test;
import unam.ciencias.matematicas.proyectofinal.coordenada.*;
import unam.ciencias.matematicas.proyectofinal.conversion.Convertidor;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clase de pruebas unitarias para verificar la exactitud de las conversiones.
 * Se usan 5 casos de prueba para cumplir con la rúbrica.
 */
public class conversionTest { // CLASE RENOMBRADA a ConversionTest

    private final Convertidor convertidor = new Convertidor();
    // Tolerancia para la comparación de doubles (por errores de punto flotante)
    private static final double TOLERANCIA = 1e-4;

    /**
     * Problema 1: Conversión de Cartesiana a Polar.
     * Punto: (3, 4, 5) -> Polar (r=5, theta=0.9273, z=5)
     */
    @Test
    void test01_cartesianaAPolar() {
        Cartesiana origen = new Cartesiana(3.0, 4.0, 5.0);
        Polares resultado = (Polares) convertidor.convertir(origen, Polares.class);

        assertEquals(5.0, resultado.getRadio(), TOLERANCIA, "El radio Polar 'r' es incorrecto.");
        assertEquals(0.927295, resultado.getAngulo(), TOLERANCIA, "El ángulo Polar 'theta' es incorrecto.");
        assertEquals(5.0, resultado.getAltura(), TOLERANCIA, "La altura Polar 'z' es incorrecta.");
    }

    /**
     * Problema 2: Conversión de Cartesiana a Esférica.
     * Punto: (1, 0, 0) -> Esférica (rho=1, theta=0, phi=π/2)
     */
    @Test
    void test02_cartesianaAEsferica() {
        Cartesiana origen = new Cartesiana(1.0, 0.0, 0.0);
        Esfericas resultado = (Esfericas) convertidor.convertir(origen, Esfericas.class);

        assertEquals(1.0, resultado.getRho(), TOLERANCIA, "El rho es incorrecto.");
        assertEquals(0.0, resultado.getTheta(), TOLERANCIA, "El theta es incorrecto.");
        assertEquals(Math.PI / 2.0, resultado.getPhi(), TOLERANCIA, "El phi es incorrecto.");
    }

    /**
     * Problema 3: Conversión de Esférica a Cartesiana.
     * Punto: (rho=1, theta=π/2, phi=π/2) -> Cartesiana (x=0, y=1, z=0)
     */
    @Test
    void test03_esfericaACartesiana() {
        // En radianes: rho=1, theta=1.570796, phi=1.570796
        Esfericas origen = new Esfericas(1.0, Math.PI / 2.0, Math.PI / 2.0);
        Cartesiana resultado = origen.aCartesiana();

        assertEquals(0.0, resultado.getX(), TOLERANCIA, "La x es incorrecta.");
        assertEquals(1.0, resultado.getY(), TOLERANCIA, "La y es incorrecta.");
        assertEquals(0.0, resultado.getZ(), TOLERANCIA, "La z es incorrecta.");
    }

    /**
     * Problema 4: Conversión de Polar a Esférica (Prueba de ruta completa).
     * Punto Polar: (r=2, theta=π/4, z=2) -> Cartesiana (x=1.4142, y=1.4142, z=2)
     * -> Esférica (rho=2.8284, theta=π/4, phi=0.7854)
     */
    @Test
    void test04_polarAEsferica() {
        // Polar (r=2, theta=0.78539, z=2)
        Polares origen = new Polares(2.0, Math.PI / 4.0, 2.0);
        Esfericas resultado = (Esfericas) convertidor.convertir(origen, Esfericas.class);

        // rho = sqrt(2^2 + 2^2) = sqrt(8) ~ 2.8284
        // phi = acos(z/rho) = acos(2 / sqrt(8)) = acos(1/sqrt(2)) = pi/4 ~ 0.7854
        assertEquals(2.828427, resultado.getRho(), TOLERANCIA, "El rho es incorrecto.");
        assertEquals(Math.PI / 4.0, resultado.getTheta(), TOLERANCIA, "El theta es incorrecto.");
        assertEquals(Math.PI / 4.0, resultado.getPhi(), TOLERANCIA, "El phi es incorrecto.");
    }

    /**
     * Problema 5: Conversión de Esférica al origen.
     * Punto Esférico: (rho=0, theta=cualquier cosa, phi=cualquier cosa) -> Cartesiana (0, 0, 0)
     */
    @Test
    void test05_esfericaOrigenACartesiana() {
        Esfericas origen = new Esfericas(0.0, 5.0, 1.0); 
        Cartesiana resultado = origen.aCartesiana();

        assertEquals(0.0, resultado.getX(), TOLERANCIA, "La x del origen es incorrecta.");
        assertEquals(0.0, resultado.getY(), TOLERANCIA, "La y del origen es incorrecta.");
        assertEquals(0.0, resultado.getZ(), TOLERANCIA, "La z del origen es incorrecta.");
    }
}