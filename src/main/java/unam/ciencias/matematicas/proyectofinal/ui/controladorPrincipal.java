package unam.ciencias.matematicas.proyectofinal.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import unam.ciencias.matematicas.proyectofinal.coordenada.*; 
import unam.ciencias.matematicas.proyectofinal.conversion.Convertidor; 

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador de la interfaz gráfica (GUI) de JavaFX.
 * Se encarga de la interacción del usuario, la validación y la presentación de resultados.
 */
public class controladorPrincipal {

    @FXML private ComboBox<String> comboOrigen;
    @FXML private ComboBox<String> comboDestino;
    
    @FXML private TextField campoComp1;
    @FXML private TextField campoComp2;
    @FXML private TextField campoComp3;
    
    @FXML private Label etiquetaComp1;
    @FXML private Label etiquetaComp2;
    @FXML private Label etiquetaComp3;
    
    @FXML private Label resultadoFinal;
    @FXML private Label mensajeError;
    @FXML private Button botonConvertir;

    private Convertidor convertidor = new Convertidor();
    
    private final Map<String, Class<? extends Coordenada>> mapaTipos = new HashMap<>();

    /**
     * Inicializa el controlador después de que se cargan los componentes FXML.
     */
    @FXML
    public void initialize() {
        mapaTipos.put("Cartesiana", Cartesiana.class);
        mapaTipos.put("Polar", Polares.class);
        mapaTipos.put("Cilíndrica", Cilindricas.class);
        mapaTipos.put("Esférica", Esfericas.class);

        ObservableList<String> sistemas = FXCollections.observableArrayList(
                "Cartesiana", "Polar", "Cilíndrica", "Esférica"
        );

        comboOrigen.setItems(sistemas);
        comboDestino.setItems(sistemas);
        
        comboOrigen.getSelectionModel().select("Cartesiana");
        comboDestino.getSelectionModel().select("Esférica");
        
        comboOrigen.getSelectionModel().selectedItemProperty().addListener(
            (observable, valorAnterior, valorNuevo) -> actualizarEtiquetas(valorNuevo)
        );
        
        actualizarEtiquetas("Cartesiana");
        
        mensajeError.setVisible(false);
    }
    
    /**
     * Actualiza las etiquetas de los campos de entrada según el sistema de coordenadas seleccionado.
     * @param sistema El nombre del sistema seleccionado.
     */
    private void actualizarEtiquetas(String sistema) {
        if (sistema == null) return;

        switch (sistema) {
            case "Cartesiana":
                etiquetaComp1.setText("X (m):");
                etiquetaComp2.setText("Y (m):");
                etiquetaComp3.setText("Z (m):");
                break;
            case "Polar":
                etiquetaComp1.setText("r (m):");
                etiquetaComp2.setText("θ (rad):");
                etiquetaComp3.setText("Z (m):");
                break;
            case "Cilíndrica":
                etiquetaComp1.setText("r (m):");
                etiquetaComp2.setText("θ (rad):");
                etiquetaComp3.setText("Z (m):");
                break;
            case "Esférica":
                etiquetaComp1.setText("ρ (m):");
                etiquetaComp2.setText("θ (rad):");
                etiquetaComp3.setText("φ (rad):");
                break;
            default:
                etiquetaComp1.setText("Comp 1:");
                etiquetaComp2.setText("Comp 2:");
                etiquetaComp3.setText("Comp 3:");
                break;
        }
        
        campoComp1.clear();
        campoComp2.clear();
        campoComp3.clear();
        resultadoFinal.setText("Resultado:");
        mensajeError.setVisible(false);
    }

    /**
     * Maneja el evento de click en el botón "Convertir".
     * Aplica la lógica de conversión y muestra el resultado o un error.
     * @param event Evento de acción.
     */
    @FXML
    private void manejarConversion(ActionEvent event) {
        mensajeError.setVisible(false);
        resultadoFinal.setText("Resultado:");
        
        try {
            double valor1 = obtenerValor(campoComp1.getText());
            double valor2 = obtenerValor(campoComp2.getText());
            double valor3 = obtenerValor(campoComp3.getText());
            
            String nombreOrigen = comboOrigen.getSelectionModel().getSelectedItem();
            String nombreDestino = comboDestino.getSelectionModel().getSelectedItem();
            
            Class<? extends Coordenada> claseOrigen = mapaTipos.get(nombreOrigen);
            Class<? extends Coordenada> claseDestino = mapaTipos.get(nombreDestino);

            Coordenada coordOrigen = crearCoordenada(claseOrigen, valor1, valor2, valor3);
            Coordenada coordResultado = convertidor.convertir(coordOrigen, claseDestino);
            resultadoFinal.setText("Resultado: " + coordResultado.toString());

        } catch (NumberFormatException e) {
            mostrarError("Error!!!: Asegúrate de ingresar solo números válidos.");
        } catch (IllegalArgumentException e) {
            mostrarError("Error lógico: " + e.getMessage());
        } catch (Exception e) {
            mostrarError("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Convierte un texto a double, lanzando NumberFormatException si falla.
     * @param texto El texto a convertir.
     * @return El valor numérico.
     */
    private double obtenerValor(String texto) throws NumberFormatException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new NumberFormatException("Campo vacío detectado.");
        }
        return Double.parseDouble(texto.trim());
    }
    
    /**
     * Crea la instancia de la coordenada de origen usando Reflection.
     * @param clase El tipo de clase de coordenada a instanciar.
     * @param v1 Valor 1.
     * @param v2 Valor 2.
     * @param v3 Valor 3.
     * @return La instancia de Coordenada creada.
     */
    private Coordenada crearCoordenada(Class<? extends Coordenada> clase, double v1, double v2, double v3) {
        try {
            return clase.getDeclaredConstructor(double.class, double.class, double.class)
                         .newInstance(v1, v2, v3);
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo crear la coordenada: " + clase.getSimpleName());
        }
    }
    
    /**
     * Muestra el mensaje de error en la interfaz.
     * @param mensaje El mensaje de error a mostrar.
     */
    private void mostrarError(String mensaje) {
        mensajeError.setText("¡ERROR! " + mensaje);
        mensajeError.setVisible(true);
        resultadoFinal.setText("Resultado: Fallido.");
    }
}