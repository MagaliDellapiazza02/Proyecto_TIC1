package um.edu.uy.ui.passenger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.ui.PublicMethods;

@Component
@Controller
public class SignUpController {
    @FXML
    private Button iniciarSesionButton;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtDocument;

    @FXML
    private TextField txtPassport;

    @FXML
    private TextField txtNationality;

    @FXML
    private TextField txtMail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtPasswordC;

    @FXML
    private Button registrarmeButton;


    @Autowired
    private PassengerMgr passengerMgr;

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
    public void registrarmeButtonClicked(ActionEvent event) {
        // Checkear que se haya llenado todos los espacios
        if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
                txtPassport.getText() == null || txtPassport.getText().equals("") ||
                txtNationality.getText() == null || txtNationality.getText().equals("") ||
                txtName.getText() == null || txtName.getText().equals("") ||
                txtMail.getText() == null || txtMail.getText().equals("") ||
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
                    long document = Long.valueOf(txtDocument.getText());
                    String passport = txtPassport.getText();
                    String nationality = txtNationality.getText();
                    String name = txtName.getText();
                    String mail = txtMail.getText();
                    String password = txtPassword.getText();

                    Passenger newP = new Passenger(document, passport, nationality, name, mail, password);
                    passengerMgr.addPassenger(newP);

                    PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/passenger/PassengerWindow.fxml", "Pasajero");

                } catch(Exception e) {
                    e.printStackTrace();
                    PublicMethods.showAlert("ERROR!", "Ya existe un usuario con este mail.");
                }
        }
    }

    @FXML
    void backButtonClicked(ActionEvent event){
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/LogIn.fxml", "Log In");
    }

}
