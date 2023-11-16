package um.edu.uy.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import um.edu.uy.Main;
import um.edu.uy.ui.PublicMethods;
import um.edu.uy.ui.airline.admin.AddAirplaneController;

import java.io.IOException;

@Component
@Controller
public class AlnWorkerControllerCreoQueNoVa {

    @FXML
    private Button btnAddAirplane;

    @FXML
    private Button btnAddFlight;

    @FXML
    private Button btnAddWorker;

    @FXML
    private Button btnBack;

    @FXML
    void addAirplaneButtonClicked(ActionEvent event) {
        // Open Add Airplane window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(AddAirplaneController.class.getResourceAsStream("/um/edu/uy/ui/user/airline/admin/AddAirplane.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Agregar avión");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/UserAdminMenu.fxml", "Inicio de sesión");
    }
}