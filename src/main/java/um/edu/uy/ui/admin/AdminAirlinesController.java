package um.edu.uy.ui.admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.ui.passenger.SignUpController;

import java.io.IOException;

@Component
public class AdminAirlinesController {

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
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void backButtonClicked(ActionEvent event) {

        close(event);

        // Open the User window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/UserAdminMenu.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Menu");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
        void addAirlineButtonClicked(ActionEvent event) {
        close(event);

        // Open Add Airline window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/AddAirline.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Crear Aerolinea");
            stage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
