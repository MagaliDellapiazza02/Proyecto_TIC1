package um.edu.uy.ui.admin;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.business.exceptions.EntityAlreadyExists;

public class AirlineCreatorController {

    @FXML
    private TextField alnCodeIATA;

    @FXML
    private TextField alnCodeICAO;

    @FXML
    private TextField alnCountry;

    @FXML
    private TextField alnName;


    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConfirm;


    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addAirline(ActionEvent event) throws InvalidInformation, EntityAlreadyExists {
        // Obtener los valores de los campos de entrada
        if (alnName.getText().isEmpty() || alnCodeIATA.getText().isEmpty() || alnCodeICAO.getText().isEmpty() || alnCountry.getText().isEmpty()) {
            // Mostrar una alerta de campos vacíos
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return; // No hacer nada más si los campos son nulos o vacíos
        } else {

            String alnNameText = alnName.getText();
            String alnCodeIATAText = alnCodeIATA.getText();
            String alnCodeICAOText = alnCodeICAO.getText();
            String alnCountryText = alnCountry.getText();


            // Verificar si los campos de usuario y contraseña son nulos o están vacíos


            Airline airline = new Airline(alnNameText, alnCodeIATAText, alnCodeICAOText, alnCountryText);

            airline.addAirline(airline);

            showAlert("Aerolinea creada", "Se agregó con éxito la aerolinea!");

            close(event);

        }
    }


    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}


