package um.edu.uy.ui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class CreateWorkerController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;

    @FXML
    private ComboBox<String> cboxWorkerType;

    @FXML
    private TextField txtDocument;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtVerifyPassword;

    @FXML
    public void initialize() {
        // Crear una lista de elementos para el ComboBox
        ObservableList<String> workerTypes = FXCollections.observableArrayList(
                "Aerolínea",
                "Recepcionista",
                "Maletero"
        );

        // Asignar la lista de elementos al ComboBox
        this.cboxWorkerType.setItems(workerTypes);
    }
}