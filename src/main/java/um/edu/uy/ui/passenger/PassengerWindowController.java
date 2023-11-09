package um.edu.uy.ui.passenger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import um.edu.uy.Main;
import um.edu.uy.business.entities.Session;
import um.edu.uy.persistence.PassengerRepository;
import um.edu.uy.persistence.UserRepository;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Controller
public class PassengerWindowController implements Initializable {

    @FXML
    private Label GreetingLabel;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button rastrearEquipajeButton;

    @FXML
    private Button verVuelosButton;

    @FXML
    private Button logOutButton;

    @Autowired
    private PassengerMgr passengerMgr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Session.mail);
        GreetingLabel.setText("Bienvenido, " + passengerMgr.getNameByMail(Session.mail));
    }

    @FXML
    private void rastrearEquipajeButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/passenger/TrackLuggage.fxml", "Rastrear equipaje");
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