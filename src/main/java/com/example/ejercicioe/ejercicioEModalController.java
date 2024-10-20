package com.example.ejercicioe;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Persona;

/**
 * Controlador para la ventana modal que permite agregar una nueva persona. (y futuramente más funciones)
 */
public class ejercicioEModalController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField apellidosField;

    @FXML
    private TextField edadField;

    @FXML
    private Button agregarButton;

    private ObservableList<Persona> personasList;

    /**
     * Establece la lista de personas a la que se agregará la nueva persona.
     *
     * @param personasList Lista de personas.
     */
    public void setPersonasList(ObservableList<Persona> personasList) {
        this.personasList = personasList;
    }

    /**
     * Método para agregar una nueva persona a la lista de la ventana principal.
     * Realiza la validación de los datos, muestra errores si los hay y agrega la persona si todo es correcto.
     */
    @FXML
    private void aniadirPersona() {
        // Obtener los valores ingresados por el usuario
        String nombre = nombreField.getText().trim();
        String apellidos = apellidosField.getText().trim();
        String edadText = edadField.getText().trim();
        StringBuilder errores = new StringBuilder();  // Acumulador de errores

        // Validaciones
        if (nombre.isEmpty()) {
            errores.append("El campo 'Nombre' no puede estar vacío.\n");
        }
        if (apellidos.isEmpty()) {
            errores.append("El campo 'Apellidos' no puede estar vacío.\n");
        }

        int edad = -1;
        try {
            edad = Integer.parseInt(edadText);
            if (edad < 0) {
                errores.append("La edad debe ser un número positivo.\n");
            }
        } catch (NumberFormatException e) {
            errores.append("El campo 'Edad' debe ser un número entero válido.\n");
        }

        // Mostrar errores si hay alguno
        if (errores.length() > 0) {
            mostrarError(errores.toString());
            return;  // No continuar si hay errores
        }

        // Crear una nueva persona
        Persona nuevaPersona = new Persona(nombre, apellidos, edad);

        // Verificar si la persona ya existe en la lista
        for (Persona persona : personasList) {
            if (persona.equals(nuevaPersona)) {
                mostrarError("Persona duplicada: Ya existe una persona con los mismos datos.");
                return;  // No continuar si ya existe una persona con los mismos datos
            }
        }

        // Si todo es correcto, agregar la nueva persona a la lista
        personasList.add(nuevaPersona);

        // Mostrar mensaje de éxito
        mostrarInformacion("Persona agregada con éxito.");

        // No cerrar la ventana aquí, la ventana solo se cierra con el botón "Cancelar"
    }

    /**
     * Muestra un mensaje de error en una alerta emergente.
     *
     * @param mensaje Mensaje de error a mostrar.
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error en los datos");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje informativo en una alerta emergente.
     *
     * @param mensaje Mensaje informativo a mostrar.
     */
    private void mostrarInformacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Cierra la ventana modal.
     */
    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) agregarButton.getScene().getWindow();
        stage.close();
    }
}

