package um.edu.uy.ui.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class SignUpController {
    @FXML
    private Button iniciarSesionButton;

    @FXML
    private TextField usuarioTextField;

    @FXML
    private PasswordField contrasenaPasswordField;

    @FXML
    private Button registrarmeButton;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void iniciarSesionButtonClicked() {
        // Manejar el evento del botón "Iniciar Sesión" aquí
        // Puedes abrir una nueva ventana o realizar otra acción necesaria

    }

    @FXML
    private void registrarmeButtonClicked() {
        // Manejar el evento del botón "Registrarme" aquí
        // Puedes abrir una nueva ventana o realizar otra acción necesaria
    }

    @FXML
    private void cancelButtonClicked(ActionEvent actionEvent) {
        try {
            // Cerrar la ventana actual
            close(actionEvent);

            // Volver a la ventana de inicio de sesión (puede ser la ventana anterior)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
            Parent root = loader.load();

            Stage loginWindow = new Stage();
            loginWindow.setTitle("Log In");

            // Configurar el controlador de la ventana de inicio de sesión (si es necesario)
            LogInController loginController = loader.getController();
            // Puedes pasar datos al controlador de inicio de sesión si es necesario

            Scene scene = new Scene(root);
            loginWindow.setScene(scene);

            // Mostrar la ventana de inicio de sesión
            loginWindow.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
