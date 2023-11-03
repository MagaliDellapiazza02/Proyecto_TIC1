package um.edu.uy.ui.passenger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import org.springframework.stereotype.Controller;
import um.edu.uy.ui.PublicMethods;

@Component
@Controller
public class PassengerWindowController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button rastrearEquipajeButton;

    @FXML
    private Button verVuelosButton;

    @FXML
    private Button logOutButton;


    @FXML
    private void rastrearEquipajeButtonClicked(ActionEvent event) {
        try {
            //Mostrar ventana
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(TrackLuggageController.class.getResourceAsStream("/um/edu/uy/ui/user/passenger/TrackLuggage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Rastreo equipajes");
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void verVuelosButtonClicked(ActionEvent event) {
        // Manejo del evento para el botón "Ver vuelos"
        // Puedes agregar aquí la lógica para ver los vuelos
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }






}