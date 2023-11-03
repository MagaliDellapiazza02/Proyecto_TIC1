package um.edu.uy.ui.airline;

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
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.business.entities.Session;
import um.edu.uy.business.entities.User;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;
import um.edu.uy.ui.passenger.SignUpController;

@Component
public class AddWorkerAlnController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogOut;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDocument;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPasswordC;

    @FXML
    private TextField txtNationality;

    @FXML
    private TextField txtTelephone;

    @Autowired
    private UserMgr userMgr;

    @FXML
    void addBtnClicked(ActionEvent event) {
        //asignar COMPANY
        if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
                txtName.getText() == null || txtName.getText().equals("") ||
                txtMail.getText() == null || txtMail.getText().equals("") ||
                txtAddress.getText() == null || txtAddress.getText().equals("") ||
                txtRole.getText() == null || txtRole.getText().equals("") ||
                txtNationality.getText() == null || txtNationality.getText().equals("") ||
                txtTelephone.getText() == null || txtTelephone.getText().equals("") ||
                txtPassword.getText() == null || txtPassword.getText().equals("") ||
                txtPasswordC.getText() == null || txtPasswordC.getText().equals("")) {

            PublicMethods.showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else if (!txtPassword.getText().equals(txtPasswordC.getText())){
            PublicMethods.showAlert(
                    "ERROR Contraseña!",
                    "Se ingresaron dos contraseñas distintas.");

        } else{
            try {
                Long document = Long.valueOf(txtDocument.getText());
                String name = txtName.getText();
                String mail = txtMail.getText();
                String password = txtPassword.getText();
                String address = txtAddress.getText();
                String role = txtRole.getText();
                String nationality = txtNationality.getText();
                String telephone = txtTelephone.getText();

                //Setear company
                String company = userMgr.getAirlineByMail(Session.mail);
                if (company.equals("None")) {
                    PublicMethods.showAlert("ERROR", "No está autorizado a agregar trabajadores");
                } else {
                    User newU = new User(document, name, mail, password, address, company, role, nationality, telephone);
                    userMgr.addUser(newU);
                }

            } catch(Exception e) {
                e.printStackTrace();
                PublicMethods.showAlert("ERROR!", "Ya existe un usuario con ese mail");
            }
        }
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/AlnWorkerAdmin.fxml", "Administrador Aerolinea");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }

}
