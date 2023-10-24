package um.edu.uy.ui.admin;

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
import org.springframework.stereotype.Controller;
import um.edu.uy.Main;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirlineRepository;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.passenger.SignUpController;

import java.io.IOException;

@Component
@Controller
public class AirlineCreatorController {

    @FXML
    private TextField txtalnCodeIATA;

    @FXML
    private TextField txtalnCodeICAO;

    @FXML
    private TextField txtalnCountry;

    @FXML
    private TextField txtalnName;


    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConfirm;

    @Autowired
    private AirlineRepository airlineRepository;


    @FXML
    private void addAirline(Airline airline) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el usuario. Checkear que el role este correcto

        if(airlineRepository.findOneByAlnIATA(airline.alnIATA) != null) {
            throw new EntityAlreadyExists();
        }

        airlineRepository.save(airline);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
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

    @FXML
    void addAirlineButtonClicked(ActionEvent event) throws EntityAlreadyExists {
        // Checkear que se haya llenado todos los espacios
        if (txtalnCodeIATA.getText() == null || txtalnCodeIATA.getText().equals("") ||
                txtalnCodeICAO.getText() == null || txtalnCodeICAO.getText().equals("") ||
                txtalnCountry.getText() == null || txtalnCountry.getText().equals("") ||
                txtalnName.getText() == null || txtalnName.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else{
            try {
                String alnCodeIATA = String.valueOf(txtalnCodeIATA.getText());
                String alnCodeICAO = txtalnCodeICAO.getText();
                String alnCountry = txtalnCountry.getText();
                String alnName = txtalnName.getText();

                Airline newP = new Airline(alnCodeIATA, alnCodeICAO, alnCountry, alnName);
                addAirline(newP);

                close(event); //cierro la ventana
                //Mostrar ventana
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("um/edu/uy/ui/user/admin/UserAdminMenu.fxml"));
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

}


