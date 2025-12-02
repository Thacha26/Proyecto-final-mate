package unam.ciencias.matematicas.proyectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal que arranca la aplicación JavaFX.
 * Se encarga de cargar el layout FXML y configurar la ventana principal.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga el layout de la interfaz usando la ruta ABSOLUTA del classpath,
        // asegurando que Maven y JavaFX lo encuentren correctamente.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/unam/ciencias/matematicas/proyectofinal/ui/main.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Proyecto Final - Conversor de Coordenadas");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}