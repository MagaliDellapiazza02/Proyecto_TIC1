package um.edu.uy.ui.airline.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;

@Component
public class AlnWorkerUserController {

    @FXML
    private Button btnAdminFlights;

    @FXML
    private Button btnLogOut;

    @FXML
    void adminFlightsButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/worker/WorkerVuelos.fxml", "Administrar Vuelos");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }
}
