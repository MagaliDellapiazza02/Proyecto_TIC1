package um.edu.uy.ui.airport.worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;

@Component
public class CheckInPassengerSelectedController {
    @FXML
    private Button btnAddLuggage;

    @FXML
    private Button btnEndCheck;

    @FXML
    void addButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/AddLuggage.fxml", "Agregar Valija");

    }

    @FXML
    void endButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/CheckInPassenger.fxml", "CheckIn Pasajero");

    }
}
