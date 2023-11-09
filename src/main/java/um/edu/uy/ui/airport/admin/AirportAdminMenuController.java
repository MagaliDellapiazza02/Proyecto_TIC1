package um.edu.uy.ui.airport.admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;


@Component
public class AirportAdminMenuController {
    @FXML
    private Button btnAdminFlights;

    @FXML
    private Button btnAdminLuggages;

    @FXML
    private Button btnAdminWorkers;

    @FXML
    private Button btnLogOut;

    @FXML
    void adminWorkersButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airport/admin/AdminWorkers.fxml", "Administrar Trabajadores");
    }

    @FXML
    void adminFlightsButtonClicked(ActionEvent event) {
    }

    @FXML
    void adminLuggagesButtonClicked(ActionEvent event) {
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }
}
