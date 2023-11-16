package um.edu.uy.ui.airport.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;

@Component
public class CheckInPassengerController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnNext;

    @FXML
    private Label labPassengers;

    @FXML
    private Spinner<?> spinnerCant;

    @FXML
    private TextField txtPassenger;

    @FXML
    void nextButtonClicked(ActionEvent event) {

    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/CheckInFlight.fxml", "CheckIn Vuelo");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }
}
