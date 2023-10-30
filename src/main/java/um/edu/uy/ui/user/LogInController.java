package um.edu.uy.ui.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import um.edu.uy.Main;
import um.edu.uy.business.entities.Session;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.services.UserMgr;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.ui.passenger.SignUpController;

import java.io.IOException;

@Controller
@Component
@Getter
public class LogInController {

    @Autowired
    private UserMgr userMgr;

    @Autowired
    private PassengerMgr passengerMgr;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginButtonClicked(ActionEvent event) {
        // Obtener los valores de los campos de entrada
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        // Verificar si los campos de usuario y contraseña son nulos o están vacíos
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // Mostrar una alerta de campos vacíos
            showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return; // No hacer nada más si los campos son nulos o vacíos
        }

        try {
            if (!passengerMgr.checkLogIn(username, password)) {
                showAlert("Usuario invalido", "Mail no existe o contraseña incorrecta");

            } else {

                Session.mail=username; //guardo el mail del usuario para acceder en otras instancias

                close(event);

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/passenger/PassengerWindow.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Pasajero");
                stage.setScene(scene);
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void registrarmeButtonClicked(ActionEvent event) {
        try {
            // Verificar si la ventana de registro ya está abierta
            if (isSignUpWindowOpen()) {
                return; // No hacer nada si ya está abierta
            }

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/passenger/SignUp.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setTitle("Registrarme");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Stage registerWindow; // Declarar la variable de clase para la ventana de registro

    private static boolean isSignUpWindowOpen() {
        // Verificar si la ventana de registro ya está abierta
        if (registerWindow != null && registerWindow.isShowing()) {
            return true; // La ventana de registro está abierta
        }
        return false; // La ventana de registro no está abierta
    }

    @FXML
    private void userLogIn(ActionEvent event) throws InvalidInformation {
        if (txtUsername.getText() == null || txtPassword.getText().equals("") || txtPassword.getText() == null) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {
                String username = txtUsername.getText();
                String password = txtPassword.getText();

                //userMgr.logIn(username, password);

                //Buscar el usuario en la base de datos y mostrarle su ventana correspondiente

                showAlert("Bienvenido! " + username, "Se agrego con exito el usuario!");

                close(event);

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "El documento no tiene el formato esperado (numérico).");

            }
        }
    }

    private void clean() {
        txtUsername.setText(null);
        txtPassword.setText(null);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
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
}
