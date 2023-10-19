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
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirportRepository;

import java.io.IOException;

@Component
public class AddAirportController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;

    @FXML
    private TextField txtIATA;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtType;

    @Autowired
    private AirportRepository airportRepository;


    @FXML
    private void close(javafx.event.ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void addAirport(Airport a) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el aeropuerto. Checkear que el role este correcto

        if (airportRepository.findByName(a.getName()) != null) {
            throw new EntityAlreadyExists();
        }

        airportRepository.save(a);
    }

    @FXML
    void addBtnClicked(javafx.event.ActionEvent event) {
        // Checkear que se haya llenado todos los espacios
        if (txtIATA.getText() == null || txtIATA.getText().equals("") || txtName.getText() == null || txtName.getText().equals("") || txtType.getText() == null || txtType.getText().equals("")) {
            System.out.println("faltaron datos");

            showAlert("Datos faltantes!", "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {
                System.out.println("entro a agregar");
                String name = txtName.getText();
                String type = txtType.getText();
                String IATA = txtIATA.getText();

                Airport newA = new Airport(name, type, IATA);
                addAirport(newA);
                showAlert("Éxito", "Aeropuerto creado con éxito");

            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Hubo un error al guardar el aeropuerto");
            }
            close(event);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                Parent root = fxmlLoader.load(AdminAirportsController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/AdminAirports.fxml"));
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

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(AdminAirportsController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/AdminAirports.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Worker");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
