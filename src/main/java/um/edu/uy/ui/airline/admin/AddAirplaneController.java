package um.edu.uy.ui.airline.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Airplane;
import um.edu.uy.business.entities.Session;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirplaneRepository;
import um.edu.uy.services.AirlineMgr;
import um.edu.uy.services.AirplaneMgr;
import um.edu.uy.ui.PublicMethods;

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
    @Autowired
    private AirplaneMgr airplaneMgr;
    @Autowired
    private AirlineMgr airlineMgr;




    @FXML
    void addBtnClicked(javafx.event.ActionEvent event) {
        // Checkear que se haya llenado todos los espacios
        if (txtLuggage.getText() == null || txtLuggage.getText().equals("") ||
                txtSeats.getText() == null || txtSeats.getText().equals("") ||
                txtType.getText() == null || txtType.getText().equals("") ||
                txtLicensePlate.getText() == null || txtLicensePlate.getText().equals("")) {

            PublicMethods.showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {
                String licensePlate = txtLicensePlate.getText();
                String type = txtType.getText();
                int luggage = Integer.parseInt(txtLuggage.getText());
                int seats = Integer.parseInt(txtSeats.getText());

                Airline airline = airlineMgr.findAirlineByIATA(airlineMgr.findAirlineWithUser(Session.mail));

                Airplane newA = new Airplane(licensePlate, type, seats, luggage, airline);
                airline.getAirplanes().add(newA);

                airplaneMgr.addAirplane(newA);
                PublicMethods.showAlert("", "Avión agregado con éxito!");

            } catch (Exception e) {
                e.printStackTrace();
                PublicMethods.showAlert("", "Hubo un error al guardar el avión.");
            }

            PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AdministrarAviones.fxml", "Administrar aviones");

        }
    }

    @FXML
    void backButtonClicked(javafx.event.ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AdministrarAviones.fxml", "Administrar Aviones");
    }

    @FXML
    void logOutButtonClicked(javafx.event.ActionEvent event) {
        PublicMethods.logOut(event);
    }
}
