package um.edu.uy.ui.airport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;

@Component
public class AirportWorkerController {

    @FXML
    private Button btnFlights;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnLuggage;

    @FXML
    private void logOutButtonClicked(ActionEvent event) {

        PublicMethods.logOut(event);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
