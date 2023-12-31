package um.edu.uy.ui.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.*;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.persistence.GateRepository;
import um.edu.uy.persistence.RunwayRepository;
import um.edu.uy.services.AirportMgr;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;
import javafx.event.ActionEvent;


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

    @FXML
    private TextField txtGatesAmount;

    @FXML
    private TextField txtRunwaysAmount;


    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private RunwayRepository runwayRepository;


    @Autowired
    private UserMgr userMgr;

    @Autowired
    private AirportMgr airportMgr;


    @FXML
    void addBtnClicked(ActionEvent event) {
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
                    txtType.getText() == null || txtType.getText().equals("") ||
                    txtGatesAmount.getText() == null || txtGatesAmount.getText().equals("") ||
                    txtRunwaysAmount.getText() == null || txtRunwaysAmount.getText().equals("")){

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

                    Airport airport = new Airport(airportName, country, type, IATA);
                    airportMgr.addAirport(airport);

                    int gatesAmount = Integer.parseInt(txtGatesAmount.getText());
                    int runwaysAmount = Integer.parseInt(txtRunwaysAmount.getText());


                    for (int i = 0; i < gatesAmount; i++) { //agregar las gates al aeropuerto y appendear
                        Gate gate = new Gate(i, airport);
                        airport.getGates().add(gate);
                        gateRepository.save(gate);
                    }

                    for (int i = 0; i < runwaysAmount; i++) { //agregar las gates al aeropuerto y appendear
                        Runway runway = new Runway(i, airport);
                        airport.getRunways().add(runway);
                        runwayRepository.save(runway);
                    }


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

                    PublicMethods.showAlert("Agregado", "Agregado con éxito!");

                    PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAirports.fxml", "Administrar aeropuertos");

                } catch (Exception e) {
                    e.printStackTrace();
                    PublicMethods.showAlert("ERROR!", "Ya existe un usuario con ese mail o un aeropuerto con ese nombre.");
                }
            }
        } catch (Exception e) {
            PublicMethods.showAlert("ERROR!", "Ingrese correctamente todos los campos.");
        }

    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAirports.fxml", "Admin Worker");

    }

    @FXML
    void logOutButtonClicked(ActionEvent event){
        PublicMethods.logOut(event);
    }

}
