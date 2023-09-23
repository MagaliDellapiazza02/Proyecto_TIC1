package um.edu.uy.ui.user;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.uy.business.UserMgr;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.UserAlreadyExists;
import um.edu.uy.business.exceptions.InvalidUserInformation;
import org.springframework.stereotype.Component;

@Component
public class UserController {

    @Autowired
    private UserMgr userMgr;

    @FXML
    private Button btnClose;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDocument;

    @FXML
    private Label loginWindow;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addUser(ActionEvent event) {
        if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
        txtAddress.getText() == null || txtAddress.getText().equals("") ||
        txtAddress.getText() == null || txtAddress.getText().equals("")) {

            showAlert(
            "Datos faltantes!",
            "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {

                Long document = Long.valueOf(txtDocument.getText());
                String name = txtName.getText();
                String address = txtAddress.getText();

                try {

                    User user = new User(document, name, address);

                    userMgr.addUser(user);

                    showAlert("Usuario agregado", "Se agrego con exito el usuario!");

                    close(event);

                } catch (InvalidUserInformation invalidUserInformation) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                } catch (UserAlreadyExists userAlreadyExists) {
                    showAlert(
                            "Documento ya registrado !",
                            "El documento indicado ya ha sido registrado en el sistema).");
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "El documento no tiene el formato esperado (numerico).");

            }
        }

    }

    private void clean() {
        txtDocument.setText(null);
        txtAddress.setText(null);
        txtName.setText(null);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
