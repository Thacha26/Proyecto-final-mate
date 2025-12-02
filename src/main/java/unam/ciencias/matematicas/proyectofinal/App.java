package unam.ciencias.coordenadas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga el layout de la interfaz
        Parent root = FXMLLoader.load(getClass().getResource("ui/main.fxml"));

        primaryStage.setTitle("Conversor de Coordenadas");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}