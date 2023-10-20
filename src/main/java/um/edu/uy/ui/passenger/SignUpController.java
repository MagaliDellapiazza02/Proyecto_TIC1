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
import um.edu.uy.Main;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.PassengerRepository;
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
    private PassengerRepository passengerRepository;

    @FXML
    private void addPassenger(Passenger p) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el usuario. Checkear que el role este correcto

        if(passengerRepository.findByDocument(p.document) != null) {
            throw new EntityAlreadyExists();
        }

        passengerRepository.save(p);
    }

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
                    addPassenger(newP);

                    //Mostrar ventana
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                    Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/passenger/PassengerWindow.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();

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

        // Open the User window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/LogIn.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
