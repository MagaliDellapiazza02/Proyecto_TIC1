package um.edu.uy.ui.admin;

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
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.User;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.passenger.SignUpController;

import java.awt.event.ActionEvent;
import java.io.IOException;

@Component
public class AddWorkerController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

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

    @Autowired
    private UserMgr userMgr;

    @FXML
    private void close(javafx.event.ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


    @FXML
    void addBtnClicked(javafx.event.ActionEvent event) {
        // Checkear que se haya llenado todos los espacios
        if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
                txtName.getText() == null || txtName.getText().equals("") ||
                txtMail.getText() == null || txtMail.getText().equals("") ||
                txtAddress.getText() == null || txtAddress.getText().equals("") ||
                txtRole.getText() == null || txtRole.getText().equals("") ||
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
                String address = txtAddress.getText();
                String company = "aeropuerto";
                String role = txtRole.getText();

                User newU = new User(document, name, mail, password, address, company, role);
                userMgr.addUser(newU);

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void backButtonClicked(javafx.event.ActionEvent event) {

        close(event);

        // Open the User window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/AdminWorkers.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Worker");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
