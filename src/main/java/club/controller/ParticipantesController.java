package club.controller;

import club.dao.IParticipanteDAO;
import club.dao.ParticipanteDAO;
import club.model.Participante;
import club.model.Ruta;
import club.util.Avisos;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

public class ParticipantesController {

    @FXML private TextField tfRuta; // Ojo, en tu FXML es un TextField, no un Label
    @FXML private ListView<Participante> lvParticipantes;

    private Ruta rutaActual;

    // Este método lo llama RutaController justo antes de mostrar la ventana
    public void setRuta(Ruta ruta) {
        this.rutaActual = ruta;
        tfRuta.setText(ruta.getNombreruta()); // Mostramos el nombre en el TextField
        cargarParticipantes();
    }

    private void cargarParticipantes() {
        if (rutaActual == null) return;

        try (Session session = util.HibernateUtil.getSessionFactory().openSession()) {
            IParticipanteDAO dao = new ParticipanteDAO(session);
            lvParticipantes.setItems(FXCollections.observableArrayList(dao.listarParticipantes(rutaActual.getId())));
        } catch (Exception e) {
            Avisos.error("Error al cargar participantes", e);
        }
    }

    @FXML
    public void eliminarSocio() {
        Participante seleccionado = lvParticipantes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            Avisos.aviso("Selecciona un participante de la lista.");
            return;
        }

        if (Avisos.confirmar("¿Eliminar a " + seleccionado.getNombre() + " de esta ruta?")) {
            try (Session session = util.HibernateUtil.getSessionFactory().openSession()) {
                IParticipanteDAO dao = new ParticipanteDAO(session);
                dao.eliminarParticipante(seleccionado);
                Avisos.info("Participante eliminado.");
                cargarParticipantes(); // Refrescar la lista
            } catch (Exception e) {
                Avisos.error("Error al eliminar participante", e);
            }
        }
    }

    @FXML
    public void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rutas.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tfRuta.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilo.css").toExternalForm());
            stage.setScene(scene);

        } catch (IOException e) {
            Avisos.error("Error al volver a rutas", e);
        }
    }
}