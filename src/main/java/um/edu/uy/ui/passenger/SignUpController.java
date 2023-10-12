package um.edu.uy.ui.passenger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.ui.user.LogInController;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SignUpController {
    @FXML
    private Button iniciarSesionButton;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtDocument;

    @FXML
    private TextField txtMail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtPasswordC;

    @FXML
    private Button registrarmeButton;

    @Autowired
    private PassengerMgr passengerMgr = new PassengerMgr();

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
        // Obtener los valores de los campos de entrada
        String username = txtName.getText();
        String password = txtPassword.getText();
    }

    @FXML
    public void registrarmeButtonClicked() {
        // Checkear que se haya llenado todos los espacios
        if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
                txtName.getText() == null || txtName.getText().equals("") ||
                txtMail.getText() == null || txtMail.getText().equals("") ||
                txtPassword.getText() == null || txtPassword.getText().equals("") ||
                txtPasswordC.getText() == null || txtPasswordC.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else if (!txtPassword.getText().equals(txtPasswordC.getText())){
            showAlert(
                    "ERROR Contraseña!",
                    "Se ingresaron dos contraseñas distintas.");

        } else{
                try {
                    Long document = Long.valueOf(txtDocument.getText());
                    String name = txtName.getText();
                    String mail = txtMail.getText();
                    String password = txtPassword.getText();

                    Passenger newP = new Passenger(document, name, mail, password);
                    passengerMgr.addPassenger(newP);

                } catch(Exception e) {
                    e.printStackTrace();
                }
        }
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void backButtonClicked(ActionEvent event) throws Exception {

        close(event);
/*
        // Open the User window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/um/edu/uy/ui/user/LogIn.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Log In");

            // Configurar el controlador de la ventana de inicio de sesión (si es necesario)
            LogInController loginController = loader.getController();
            // Puedes pasar datos al controlador de inicio de sesión si es necesario

            Scene scene = new Scene(root);
            loginStage.setScene(scene);

            // Mostrar la ventana de inicio de sesión
            loginStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

 */
    }

}
