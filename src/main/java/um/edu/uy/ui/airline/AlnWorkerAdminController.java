package um.edu.uy.ui.airline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.ui.PublicMethods;
import um.edu.uy.ui.passenger.SignUpController;

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
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/AdministrarVuelos.fxml", "Administrador de Vuelos");

    }

    @FXML
    void administrateAirplanesButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/AdministrarAviones.fxml", "Administrador de Aviones");

    }

    @FXML
    void addWorkerButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/AddWorkerAln.fxml", "Administrador de Vuelos");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }
}
