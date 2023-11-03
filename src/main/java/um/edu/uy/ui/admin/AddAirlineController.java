package um.edu.uy.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.User;
import um.edu.uy.services.AirlineMgr;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;

@Component
public class AddAirlineController {
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
    private TextField txtMail;

    @FXML
    private TextField txtNationality;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPasswordC;

    @FXML
    private TextField txtTelephone;

    @FXML
    private TextField txtalnCodeIATA;

    @FXML
    private TextField txtalnCodeICAO;

    @FXML
    private TextField txtalnCountry;

    @FXML
    private TextField txtalnName;

    @Autowired
    private UserMgr userMgr;

    @Autowired
    private AirlineMgr airlineMgr;

    @FXML
    void addAirlineButtonClicked(ActionEvent event) {
        //asignar rol de administrador  y company
        if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
                txtName.getText() == null || txtName.getText().equals("") ||
                txtMail.getText() == null || txtMail.getText().equals("") ||
                txtAddress.getText() == null || txtAddress.getText().equals("") ||
                txtNationality.getText() == null || txtNationality.getText().equals("") ||
                txtTelephone.getText() == null || txtTelephone.getText().equals("") ||
                txtPassword.getText() == null || txtPassword.getText().equals("") ||
                txtPasswordC.getText() == null || txtPasswordC.getText().equals("") ||
                txtalnCodeIATA.getText() == null || txtalnCodeIATA.getText().equals("") ||
                txtalnCodeICAO.getText() == null || txtalnCodeICAO.getText().equals("") ||
                txtalnCountry.getText() == null || txtalnCountry.getText().equals("") ||
                txtalnName.getText() == null || txtalnName.getText().equals("")) {

            PublicMethods.showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else if (!txtPassword.getText().equals(txtPasswordC.getText())){
            PublicMethods.showAlert(
                    "ERROR Contraseña!",
                    "Se ingresaron dos contraseñas distintas.");

        } else{
            try {
                String alnIATA = txtalnCodeIATA.getText();
                String alnICAO = txtalnCodeICAO.getText();
                String alnName = txtalnName.getText();
                String alnCountry = txtalnCountry.getText();

                Airline a = new Airline(alnName, alnIATA, alnICAO, alnCountry);
                airlineMgr.addAirline(a);


                Long document = Long.valueOf(txtDocument.getText());
                String name = txtName.getText();
                String mail = txtMail.getText();
                String password = txtPassword.getText();
                String address = txtAddress.getText();
                String role = "administrador";
                String company = alnName;
                String nationality = txtNationality.getText();
                String telephone = txtTelephone.getText();

                User newU = new User(document, name, mail, password, address, company, role, nationality, telephone);
                userMgr.addUser(newU);

                PublicMethods.showAlert("Agregado", "Agregado con exito");


            } catch(Exception e) {
                e.printStackTrace();
                PublicMethods.showAlert("ERROR!", "Ya existe un usuario con ese mail o una aerolinea con ese codigo IATA");
            }
        }

    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAln.fxml","Administrar Aerolineas");
    }

}
