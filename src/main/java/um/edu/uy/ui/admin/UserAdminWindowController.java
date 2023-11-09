package um.edu.uy.ui.admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import um.edu.uy.Main;
import um.edu.uy.ui.PublicMethods;
import um.edu.uy.ui.passenger.SignUpController;

import java.io.IOException;

@ComponentScan
@Controller
public class UserAdminWindowController {

    @FXML
    private Button btnAln;

    @FXML
    private Button btnAirports;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnWorkers;

    /*
    @Bean
    public UserAdminWindowController userAdminWindowController() {
        return new UserAdminWindowController();
    }
*/

    @FXML
    void adminAln(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAln.fxml", "Admin Aerolineas");
    }

    @FXML
    void adminAirports(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAirports.fxml", "Admin Aeropuertos");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }

}

