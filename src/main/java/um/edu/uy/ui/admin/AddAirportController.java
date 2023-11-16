package um.edu.uy.ui.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.User;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.services.AirportMgr;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;

import java.awt.event.ActionEvent;

@Component
public class AddAirportController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnLogOut;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtDocument;

    @FXML
    private TextField txtIATA;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtNameAirport;

    @FXML
    private TextField txtNationality;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPasswordC;

    @FXML
    private TextField txtTelephone;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtUserName;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private UserMgr userMgr;


    private void addAirport(Airport a) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el aeropuerto. Checkear que el role este correcto

        if (airportRepository.findByName(a.getName()) != null) {
            throw new EntityAlreadyExists();
        }

        airportRepository.save(a);
    }

    @FXML
    void addBtnClicked(javafx.event.ActionEvent event) {
        try {
            if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
                    txtUserName.getText() == null || txtUserName.getText().equals("") ||
                    txtMail.getText() == null || txtMail.getText().equals("") ||
                    txtAddress.getText() == null || txtAddress.getText().equals("") ||
                    txtNationality.getText() == null || txtNationality.getText().equals("") ||
                    txtTelephone.getText() == null || txtTelephone.getText().equals("") ||
                    txtPassword.getText() == null || txtPassword.getText().equals("") ||
                    txtPasswordC.getText() == null || txtPasswordC.getText().equals("") ||
                    txtCountry.getText() == null || txtCountry.getText().equals("") ||
                    txtNameAirport.getText() == null || txtNameAirport.getText().equals("") ||
                    txtIATA.getText() == null || txtIATA.getText().equals("") ||
                    txtType.getText() == null || txtType.getText().equals("")) {

                PublicMethods.showAlert(
                        "Datos faltantes!",
                        "No se ingresaron los datos necesarios para completar el ingreso.");

            } else if (!txtPassword.getText().equals(txtPasswordC.getText())) {
                PublicMethods.showAlert(
                        "ERROR Contraseña!",
                        "Se ingresaron dos contraseñas distintas.");

            } else {
                try {
                    String airportName = txtNameAirport.getText();
                    String country = txtCountry.getText();
                    String type = txtType.getText();
                    String IATA = txtIATA.getText();

                    Airport a = new Airport(airportName, country, type, IATA);
                    addAirport(a);


                    Long document = Long.valueOf(txtDocument.getText());
                    String name = txtUserName.getText();
                    String mail = txtMail.getText();
                    String password = txtPassword.getText();
                    String address = txtAddress.getText();
                    String role = "administrador";
                    String company = "Aeropuerto%" + airportName;
                    String nationality = txtNationality.getText();
                    String telephone = txtTelephone.getText();

                    User newU = new User(document, name, mail, password, address, company, role, nationality, telephone);
                    userMgr.addUser(newU);

                    PublicMethods.showAlert("Agregado", "Agregado con exito");

                    PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAirports.fxml", "Administrar aeropuertos");

                } catch (Exception e) {
                    e.printStackTrace();
                    PublicMethods.showAlert("ERROR!", "Ya existe un usuario con ese mail o un aeropuerto con ese nombre");
                }
            }
        } catch (Exception e) {
            PublicMethods.showAlert("ERROR!", "Ingrese correctamente todos los campos.");
        }

    }

    @FXML
    void backButtonClicked(javafx.event.ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAirports.fxml", "Admin Worker");

    }

    @FXML
    void logOutButtonClicked(javafx.event.ActionEvent event){
        PublicMethods.logOut(event);
    }

}
