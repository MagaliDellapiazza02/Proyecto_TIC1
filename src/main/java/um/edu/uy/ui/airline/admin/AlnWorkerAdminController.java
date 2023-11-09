package um.edu.uy.ui.airline.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;

@Component
public class AlnWorkerAdminController {
    @FXML
    private Button btnAddAirplane;

    @FXML
    private Button btnAddFlight;

    @FXML
    private Button btnAddWorker;

    @FXML
    private Button btnLogOut;

    @FXML
    void administrateFlightsButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AdministrarVuelos.fxml", "Administrador de Vuelos");

    }

    @FXML
    void administrateAirplanesButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AdministrarAviones.fxml", "Administrador de Aviones");

    }

    @FXML
    void addWorkerButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AddWorkerAln.fxml", "Administrador de Vuelos");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }
}
