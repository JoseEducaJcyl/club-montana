package club.controller;

import club.controller.ParticipantesController;
import club.dao.IRutaDAO;
import club.dao.ISocioDAO;
import club.dao.RutaDAO;
import club.dao.SocioDAO;
import club.model.Dificultad;
import club.model.Ruta;
import club.model.Socio;
import club.util.Avisos;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

public class RutaController {

    @FXML private TextField tfNombre;
    @FXML private ComboBox<Dificultad> cbDificultad;
    @FXML private ComboBox<Socio> cbGuia;

    @FXML private TableView<Ruta> tblRutas;
    @FXML private TableColumn<Ruta, Integer> colId;
    @FXML private TableColumn<Ruta, String> colNombre;
    @FXML private TableColumn<Ruta, String> colDificultad;
    @FXML private TableColumn<Ruta, String> colGuia;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreruta()));
        colDificultad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDificultad().name()));

        colGuia.setCellValueFactory(cellData -> {
            Socio guia = cellData.getValue().getGuia();
            return new SimpleStringProperty(guia != null ? guia.getNombre() : "Sin guía");
        });

        // 2. Rellenar los ComboBox
        cbDificultad.setItems(FXCollections.observableArrayList(Dificultad.values()));
        cargarSocios();

        // 3. Cargar la tabla al arrancar
        cargarRutas();
    }

    private void cargarSocios() {
        try (Session session = util.HibernateUtil.getSessionFactory().openSession()) {
            ISocioDAO dao = new SocioDAO(session);
            cbGuia.setItems(FXCollections.observableArrayList(dao.listarSocios()));
        } catch (Exception e) {
            Avisos.error("Error al cargar socios", e);
        }
    }

    private void cargarRutas() {
        try (Session session = util.HibernateUtil.getSessionFactory().openSession()) {
            IRutaDAO dao = new RutaDAO(session);
            tblRutas.setItems(FXCollections.observableArrayList(dao.listarRutas()));
        } catch (Exception e) {
            Avisos.error("Error al cargar rutas", e);
        }
    }

    @FXML
    public void insertarNuevo() {
        if (tfNombre.getText().isEmpty() || cbDificultad.getValue() == null || cbGuia.getValue() == null) {
            Avisos.aviso("Rellena todos los campos.");
            return;
        }

        Ruta nueva = new Ruta();
        nueva.setNombreruta(tfNombre.getText());
        nueva.setDificultad(cbDificultad.getValue());
        nueva.setGuia(cbGuia.getValue());

        try (Session session = util.HibernateUtil.getSessionFactory().openSession()) {
            IRutaDAO dao = new RutaDAO(session);
            dao.insertarRuta(nueva);
            Avisos.info("Ruta insertada correctamente.");
            cargarRutas();
            limpiar();
        } catch (Exception e) {
            Avisos.error("Error al insertar la ruta", e);
        }
    }

    @FXML
    public void modificarSeleccionado() {
        Ruta seleccionada = tblRutas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            Avisos.aviso("Selecciona una ruta de la tabla primero.");
            return;
        }

        seleccionada.setNombreruta(tfNombre.getText());
        seleccionada.setDificultad(cbDificultad.getValue());
        seleccionada.setGuia(cbGuia.getValue());

        try (Session session = util.HibernateUtil.getSessionFactory().openSession()) {
            IRutaDAO dao = new RutaDAO(session);
            dao.modificarRuta(seleccionada);
            Avisos.info("Ruta modificada.");
            cargarRutas();
        } catch (Exception e) {
            Avisos.error("Error al modificar", e);
        }
    }

    @FXML
    public void eliminarSeleccionado() {
        Ruta seleccionada = tblRutas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            Avisos.aviso("Selecciona una ruta para eliminar.");
            return;
        }
        if (Avisos.confirmar("¿Eliminar la ruta " + seleccionada.getNombreruta() + "?")) {
            try (Session session = util.HibernateUtil.getSessionFactory().openSession()) {
                IRutaDAO dao = new RutaDAO(session);
                dao.eliminarRuta(seleccionada);
                Avisos.info("Ruta eliminada.");
                cargarRutas();
                limpiar();
            } catch (Exception e) {
                Avisos.error("Error al eliminar", e);
            }
        }
    }

    @FXML
    public void limpiar() {
        tfNombre.clear();
        cbDificultad.getSelectionModel().clearSelection();
        cbGuia.getSelectionModel().clearSelection();
        tblRutas.getSelectionModel().clearSelection();
    }

    @FXML
    public void irAParticipantes() {
        Ruta seleccionada = tblRutas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            Avisos.aviso("Selecciona una ruta para ver sus participantes.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/participantes.fxml"));
            Parent root = loader.load();

            ParticipantesController controller = loader.getController();
            controller.setRuta(seleccionada);

            Stage stage = (Stage) tblRutas.getScene().getWindow();
            // Mantenemos los estilos
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/estilo.css").toExternalForm());
            stage.setScene(scene);

        } catch (IOException e) {
            Avisos.error("Error al abrir participantes", e);
        }
    }
}