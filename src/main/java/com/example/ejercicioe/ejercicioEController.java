package com.example.ejercicioe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Persona;

import java.io.IOException;

/**
 * Controlador que maneja la ventana principal con la lista de personas.
 */
public class ejercicioEController {

    @FXML
    private TableView<Persona> personTable;

    @FXML
    private TableColumn<Persona, String> nombreColumn;

    @FXML
    private TableColumn<Persona, String> apellidosColumn;

    @FXML
    private TableColumn<Persona, Integer> edadColumn;

    @FXML
    private Button agregarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button eliminarButton;

    private ObservableList<Persona> personasList = FXCollections.observableArrayList();

    /**
     * Inicializa la tabla y vincula las columnas a los datos de las personas.
     */
    @FXML
    public void initialize() {
        nombreColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        apellidosColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getApellido()));
        edadColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());

        personTable.setItems(personasList);
    }

    /**
     * Método que abre una ventana modal para agregar o modificar una persona dependiendo del botón que se haya pulsado.
     *
     * @param event Evento disparado por los botones "Agregar Persona" o "Modificar Persona".
     */
    @FXML
    private void abrirVentanaAgregar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ejercicioe/ejercicioEmodal.fxml"));
            Parent modalRoot = loader.load();
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(agregarButton.getScene().getWindow());

            ejercicioEModalController modalController = loader.getController();

            if (event.getSource() == agregarButton) {
                modalStage.setTitle("Agregar Persona");
                modalController.setPersonasList(personasList);
            } else if (event.getSource() == modificarButton) {
                Persona personaSeleccionada = personTable.getSelectionModel().getSelectedItem();
                if (personaSeleccionada == null) {
                    mostrarAlerta("No hay ninguna persona seleccionada", "Por favor, seleccione una persona para editar.");
                    return; // No continuar si no hay selección
                }
                modalStage.setTitle("Editar Persona");
                modalController.setPersonasList(personasList);
                modalController.setPersonaAEditar(personaSeleccionada);
            }

            modalStage.setScene(new Scene(modalRoot));
            modalStage.showAndWait();

            // Refrescar la tabla después de modificar
            personTable.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que elimina una persona seleccionada de la tabla.
     * Si no hay una persona seleccionada, muestra un mensaje de alerta.
     */
    @FXML
    private void eliminarPersona(ActionEvent event) {
        // Obtener la persona seleccionada
        Persona personaSeleccionada = personTable.getSelectionModel().getSelectedItem();

        if (personaSeleccionada == null) {
            // Si no hay ninguna persona seleccionada, mostrar alerta
            mostrarAlerta("No hay ninguna persona seleccionada", "Por favor, seleccione una persona para eliminar.");
        } else {
            // Eliminar la persona seleccionada de la lista
            personasList.remove(personaSeleccionada);
            // Mostrar un mensaje de confirmación
            mostrarAlerta("Persona eliminada", "La persona ha sido eliminada con éxito.");
        }
    }

    /**
     * Muestra una alerta con el título y el mensaje especificado.
     *
     * @param titulo El título de la alerta.
     * @param mensaje El mensaje de la alerta.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
