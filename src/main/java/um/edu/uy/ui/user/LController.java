package um.edu.uy.ui.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.business.entities.Session;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;
import um.edu.uy.ui.passenger.SignUpController;


@Component

public class LController {

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
    void logInButtonClicked(ActionEvent event) {
        // Obtener los valores de los campos de entrada
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        // Verificar si los campos de usuario y contraseña son nulos o están vacíos
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // Mostrar una alerta de campos vacíos
            PublicMethods.showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return; // No hacer nada más si los campos son nulos o vacíos
        }

        try {

            if (!passengerMgr.checkLogIn(username, password)) {
                String checkedLogIn = userMgr.checkWorkerLogIn(username, password);

                if (checkedLogIn.equals("None")) {
                    PublicMethods.showAlert("Usuario invalido", "Mail no existe o contraseña incorrecta");

                }else if (checkedLogIn.equals("Admin Airport")) {
                    Session.mail=username; //guardo el mail del usuario para acceder en otras instancias
                    PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/UserAdminMenu.fxml", "Menu Administrador");

                } else if (checkedLogIn.equals("Worker Airport")) {
                    Session.mail=username; //guardo el mail del usuario para acceder en otras instancias
                    PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/userAirport/AirportWorker.fxml", "Menu Administrador");

                }else if (checkedLogIn.split(" ")[0].equals("Admin")) {
                    Session.mail = username; //guardo el mail del usuario para acceder en otras instancias
                    PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/AlnWorkerAdmin.fxml", "Aerolinea Administrador");

                } else if (checkedLogIn.split(" ")[0].equals("Worker")) {
                    Session.mail = username; //guardo el mail del usuario para acceder en otras instancias
                    PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/AlnWorkerUser.fxml", "Trabajador Aerolinea");

                }

            } else {
                Session.mail=username; //guardo el mail del usuario para acceder en otras instancias
                PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/passenger/PassengerWindow.fxml", "Pasajero");
            }

        } catch (Exception e) {
            e.printStackTrace();
            PublicMethods.showAlert("ERROR", "No se pudo iniciar sesion");
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
}
