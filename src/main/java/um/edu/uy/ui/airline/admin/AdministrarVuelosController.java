package um.edu.uy.ui.airline.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;

@Component
public class AdministrarVuelosController {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnModify;

    @FXML
    private Button btnSeeFlights;

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AlnWorkerAdmin.fxml", "Administrador Aerolinea");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }

    @FXML
    void addButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AddFlight.fxml", "Agregar vuelo");
    }
}
