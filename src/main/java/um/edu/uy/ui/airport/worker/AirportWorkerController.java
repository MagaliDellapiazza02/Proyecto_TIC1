package um.edu.uy.ui.airport.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;

@Component
public class AirportWorkerController {

    @FXML
    private Button btnFlights;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnLuggage;

    @FXML
    private Button btnValidateFlights;

    @FXML
    private Button gateReservationsBtn;

    @FXML
    private Button runwayReservationsBtn;

    @FXML
    void ValidateFlightsBtnClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airport/worker/ValidateFlights.fxml", "Validar Vuelos");
    }

    @FXML
    void gateReservationsBtnClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airport/worker/GateReservations.fxml", "Reservas de puertas con vuelos sin confirmar");
    }
    @FXML
    void runwayReservationsBtnClicked(ActionEvent actionEvent) {
        PublicMethods.changeWindow(actionEvent, "/um/edu/uy/ui/user/airport/worker/RunwayReservations.fxml", "Reservas de pistas con vuelos sin confirmar");
    }
    @FXML
    void logOutButtonClicked(ActionEvent actionEvent) {
        PublicMethods.logOut(actionEvent);
    }
}
