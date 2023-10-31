package um.edu.uy.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.ui.passenger.SignUpController;

import java.io.IOException;

@Component
public class AddAirlineController {
    @FXML
    private Button btnLogOut;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDocument;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPasswordC;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtalnCodeIATA;

    @FXML
    private TextField txtalnCodeICAO;

    @FXML
    private TextField txtalnCountry;

    @FXML
    private TextField txtalnName;

    @FXML
    void addAirlineButtonClicked(ActionEvent event) {
        //asignar rol de administrador  y company
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        close(event);

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/LogIn1.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Log In");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        close(event);

        // Open the User window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/AdminAln.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Administrar Aerolineas");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
