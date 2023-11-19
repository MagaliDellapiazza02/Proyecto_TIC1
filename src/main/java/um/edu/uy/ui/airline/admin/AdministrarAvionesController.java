package um.edu.uy.ui.airline.admin;

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
public class AdministrarAvionesController {

    @FXML
    private Button btnAddPlane;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDeletePlane;

    @FXML
    private Button btnLogOut;

    @FXML
    void addAirplaneButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AddAirplane.fxml", "Agregar Aviones");
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AlnWorkerAdmin.fxml", "Administrador Aerolinea");
    }
}
