package um.edu.uy.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import um.edu.uy.ui.PublicMethods;
@Component
@Controller
public class AdminWorkersController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Label labTitle;

    @FXML
    void addWorkerButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AddWorker.fxml", "Agregar Trabajador");
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/DeleteWorker.fxml", "Borrar Trabajador");
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/UserAdminMenu.fxml", "Admin Menu");
    }

}

