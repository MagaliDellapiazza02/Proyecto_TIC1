package um.edu.uy.ui.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.User;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;

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

    @FXML
    private TextField txtNationality;

    @FXML
    private TextField txtTelephone;

    @Autowired
    private UserMgr userMgr;

    @FXML
    void addBtnClicked(javafx.event.ActionEvent event) {
        // Checkear que se haya llenado todos los espacios
        if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
                txtName.getText() == null || txtName.getText().equals("") ||
                txtMail.getText() == null || txtMail.getText().equals("") ||
                txtAddress.getText() == null || txtAddress.getText().equals("") ||
                txtRole.getText() == null || txtRole.getText().equals("") ||
                txtNationality.getText() == null || txtNationality.getText().equals("") ||
                txtTelephone.getText() == null || txtTelephone.getText().equals("") ||
                txtPassword.getText() == null || txtPassword.getText().equals("") ||
                txtPasswordC.getText() == null || txtPasswordC.getText().equals("")) {
            PublicMethods.showAlert("Datos faltantes!", "No se ingresaron los datos necesarios para completar el ingreso.");

        } else if (!txtPassword.getText().equals(txtPasswordC.getText())){
            PublicMethods.showAlert(
                    "ERROR Contraseña!",
                    "Se ingresaron dos contraseñas distintas.");

        } else{
            try {
                long document = 78;
                try{
                    document = Long.valueOf(txtDocument.getText());
                } catch (Exception e) {
                    PublicMethods.showAlert("A", "A");
                }
                String name = txtName.getText();
                String mail = txtMail.getText();
                String password = txtPassword.getText();
                String address = txtAddress.getText();
                String company = "aeropuerto";
                String role = txtRole.getText();
                String nationality = txtNationality.getText();
                String telephone = txtTelephone.getText();

                User newU = new User(document, name, mail, password, address, company, role, nationality, telephone);
                userMgr.addUser(newU);
                PublicMethods.showAlert("Agregado", "Se agrego correctamente");

            } catch(Exception e) {
                e.printStackTrace();
                PublicMethods.showAlert("Usuario ya existe", "Ya existe un usuario con ese mail");
            }
        }
    }

    @FXML
    void logOutButtonClicked(javafx.event.ActionEvent event){
        PublicMethods.logOut(event);
    }

    @FXML
    void backButtonClicked(javafx.event.ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminWorkers.fxml", "Admin Trabajadores");
    }
}
