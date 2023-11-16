package um.edu.uy.ui.airport.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;

@Component
public class CheckInFlightController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnNext;

    @FXML
    private MenuButton menuBtnFlights;

    @FXML
    void nextButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/CheckInPassenger.fxml", "CheckIn Pasajero");
    }

    @FXML
    void backButtonClicked(ActionEvent event) {

    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }
}
