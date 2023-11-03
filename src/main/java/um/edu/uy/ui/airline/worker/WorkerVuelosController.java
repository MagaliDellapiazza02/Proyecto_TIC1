package um.edu.uy.ui.airline.worker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;

@Component
public class WorkerVuelosController {
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
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/worker/AlnWorkerUser.fxml", "Trabajador Aerolinea");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }

    @FXML
    void addButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/addFlight.fxml", "Agregar vuelo");
    }
}
