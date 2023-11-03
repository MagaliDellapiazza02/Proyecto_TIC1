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
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/AlnWorkerAdmin.fxml", "Administrador Aerolinea");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }

}
