package um.edu.uy.ui.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.business.entities.Airplane;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirplaneRepository;

import java.io.IOException;

@Component
public class AddAirplaneController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;

    @FXML
    private TextField txtLicensePlate;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtSeats;

    @FXML
    private TextField txtLuggage;

    @Autowired
    private AirplaneRepository airplaneRepository;


    @FXML
    private void close(javafx.event.ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void addAirplane(Airplane a) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el avion. Checkear que el role este correcto

        if (airplaneRepository.findByLicensePlate(a.getLicensePlate()) != null) {
            throw new EntityAlreadyExists();
        }

        airplaneRepository.save(a);
    }

    @FXML
    void addBtnClicked(javafx.event.ActionEvent event) {
        // Checkear que se haya llenado todos los espacios
        if (txtLuggage.getText() == null || txtLuggage.getText().equals("") ||
                txtSeats.getText() == null || txtSeats.getText().equals("") ||
                txtType.getText() == null || txtType.getText().equals("") ||
                txtLicensePlate.getText() == null || txtLicensePlate.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {
                String licensePlate = txtLicensePlate.getText();
                String type = txtType.getText();
                int luggage = Integer.parseInt(txtLuggage.getText());
                int seats = Integer.parseInt(txtSeats.getText());

                Airplane newA = new Airplane(licensePlate, type, seats, luggage);
                addAirplane(newA);
                showAlert("", "Avión agregado con éxito");

            } catch (Exception e) {
                e.printStackTrace();
                showAlert("", "Hubo un error al guardar el avión");
            }
            close(event);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                Parent root = fxmlLoader.load(AlnWorkerController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/AlnWorkerAdmin.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Administrar aeropuertos");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void backButtonClicked(javafx.event.ActionEvent event) {

        close(event);

        //volver a la ventana previa
    }
}
