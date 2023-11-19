package um.edu.uy.ui.airport.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
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

    @FXML
    void addButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/CheckInPassengerSelected.fxml", "CheckIn Pasajero");

    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/CheckInPassengerSelected.fxml", "CheckIn Pasajero");

    }
}
