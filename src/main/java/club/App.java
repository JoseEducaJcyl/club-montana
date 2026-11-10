package club;

import club.util.Avisos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/rutas.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add(App.class.getResource("/css/estilo.css").toExternalForm());

        // Se ejecuta al pulsar la aspa de la ventana
        stage.setOnCloseRequest(event -> {
            //Conexion.cerrar();
            Avisos.info("Cerrando conexiones...");
        });

        stage.setTitle("Club de Montaña — Rutas");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        //Conexion.cerrar();
    }

    /**
     * Cambia el contenido de la ventana actual por otro formulario y devuelve su controlador,
     * para poder pasarle datos (por ejemplo, la ruta seleccionada).
     */


    public static void main(String[] args) {
        launch(args);
    }
}
