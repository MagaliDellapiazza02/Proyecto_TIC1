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
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import um.edu.uy.business.UserMgr;
import um.edu.uy.business.exceptions.InvalidInformation;

@Controller
public class LogInController {

    @Autowired
    private UserMgr userMgr;

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
    private void loginButtonClicked() {
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
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserWindow.fxml"));
            Parent root = loader.load();

            // Crear una nueva instancia de Stage para la nueva ventana
            Stage userDataWindow = new Stage();
            userDataWindow.setTitle("User Data Window");

            // Configurar el controlador de la nueva ventana (si es necesario)
            UserWindowController userDWController = loader.getController();
            // Puedes pasar datos al controlador de la nueva ventana si es necesario

            // Configurar la escena
            Scene scene = new Scene(root);

            // Establecer la escena en la nueva ventana
            userDataWindow.setScene(scene);

            // Mostrar la nueva ventana
            userDataWindow.show();

            // Cerrar la ventana de inicio de sesión
            closeLoginWindow();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeLoginWindow() {
        // Obtener la ventana de inicio de sesión actual y cerrarla
        Stage loginStage = (Stage) loginButton.getScene().getWindow();
        loginStage.close();
    }

    @FXML
    private void registrarmeButtonClicked() {
        try {
            // Verificar si la ventana de registro ya está abierta
            if (isSignUpWindowOpen()) {
                return; // No hacer nada si ya está abierta
            }

            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            Parent root = loader.load();

            // Crear una nueva instancia de Stage para la nueva ventana
            Stage registerWindow = new Stage();

            // Configurar la ventana como un diálogo modal
            registerWindow.initModality(Modality.APPLICATION_MODAL);

            // Configurar el controlador de la nueva ventana (si es necesario)
            SignUpController nuevaVentanaController = loader.getController();
            // Puedes pasar datos al controlador de la nueva ventana si es necesario

            // Configurar la escena
            Scene scene = new Scene(root);

            // Establecer la escena en la nueva ventana
            registerWindow.setScene(scene);

            // Quitar el título de la ventana
            registerWindow.initStyle(StageStyle.UTILITY);

            // Mostrar la nueva ventana
            registerWindow.showAndWait(); // El programa se bloqueará hasta que la ventana se cierre
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

}
