package um.edu.uy.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import um.edu.uy.Main;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.passenger.SignUpController;

import java.io.IOException;

@Controller
public class adminGeneralController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogIn;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtUser;

    @Autowired
    private UserMgr userMgr;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginButtonClicked(ActionEvent event) {
        // Obtener los valores de los campos de entrada
        String username = txtUser.getText();
        String password = txtPass.getText();

        // Verificar si los campos de usuario y contraseña son nulos o están vacíos
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // Mostrar una alerta de campos vacíos
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return; // No hacer nada más si los campos son nulos o vacíos
        }

        try {
            if (!userMgr.checkAdminLogIn(username, password)) {
                showAlert("Administrador invalido", "Contraseña incorrecta o no es administrador");
            } else {
                close(event);

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/UserAdminMenu.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Admin Menu");
                stage.setScene(scene);
                stage.show();
            }

        } catch (IOException e) {
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

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/LogIn1.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}
