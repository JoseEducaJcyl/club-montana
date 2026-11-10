package club.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public final class Avisos {

    private Avisos() {
    }

    public static void aviso(String mensaje) {
        mostrar(Alert.AlertType.WARNING, "Aviso", mensaje);
    }

    public static void error(String mensaje, Exception e) {
        mostrar(Alert.AlertType.ERROR, "Error", mensaje + "\n\n" + e.getMessage());
    }

    public static boolean confirmar(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Confirmar");
        alert.setHeaderText(null);
        return alert.showAndWait().filter(b -> b == ButtonType.OK).isPresent();
    }

    private static void mostrar(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo, mensaje);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void info(String mensaje) {
        mostrar(Alert.AlertType.INFORMATION, "Información", mensaje);
    }
}
