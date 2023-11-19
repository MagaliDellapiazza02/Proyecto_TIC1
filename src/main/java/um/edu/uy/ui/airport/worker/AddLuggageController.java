package um.edu.uy.ui.airport.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.*;
import um.edu.uy.services.FlightMgr;
import um.edu.uy.services.LuggageMgr;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.ui.PublicMethods;

@Component
public class AddLuggageController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private TextField txtLugTrackingCode;

    @FXML
    private TextField txtLugWeight;

    @Autowired
    private PassengerMgr passengerMgr;

    @Autowired
    private FlightMgr flightMgr;

    @Autowired
    private LuggageMgr luggageMgr;

    @FXML
    void addButtonClicked(ActionEvent event) {
        try {
            if (txtLugTrackingCode.getText() == null || txtLugTrackingCode.getText().equals("") ||
            txtLugWeight.getText() == null || txtLugWeight.getText().equals("")) {

                PublicMethods.showAlert(
                        "Datos faltantes!",
                        "No se ingresaron los datos necesarios para completar el ingreso.");

            } else{
                String trackingCode = txtLugTrackingCode.getText();
                int luggageWeight = Integer.parseInt(txtLugWeight.getText());
                Passenger passenger = passengerMgr.getPassengerByPassport(Session.passengerPassport);
                Flight flight = flightMgr.getFlightByNumber(Session.flightNumber);

                boolean result = luggageMgr.addLuggage(passenger,flight, luggageWeight, trackingCode);

                if (result) {
                    PublicMethods.showAlert("Agregada", "Agregado con éxito!");
                    backButtonClicked(event);

                } else {
                    PublicMethods.showAlert("ERROR!", "No se pudo agregar porque ya existe ese codigo de traqueo o no hay más lugar en el avión");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            PublicMethods.showAlert("ERROR!", "Ingrese correctamente todos los campos.");

        }
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/CheckInPassengerSelected.fxml", "CheckIn Pasajero");

    }
}
