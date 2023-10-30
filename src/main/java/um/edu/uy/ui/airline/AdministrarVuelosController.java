package um.edu.uy.ui.airline;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

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
}
