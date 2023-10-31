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
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

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

        close(event);
        // Abrir la ventana de inicio de sesión (puede ser "LogIn.fxml")
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/LogIn.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Log In");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}