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
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.ui.PublicMethods;

import java.io.IOException;

@Component
public class AddAirportController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;

    @FXML
    private TextField txtIATA;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtType;

    @Autowired
    private AirportRepository airportRepository;

    private void addAirport(Airport a) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el aeropuerto. Checkear que el role este correcto

        if (airportRepository.findByName(a.getName()) != null) {
            throw new EntityAlreadyExists();
        }

        airportRepository.save(a);
    }

    @FXML
    void addBtnClicked(javafx.event.ActionEvent event) {
        // Checkear que se haya llenado todos los espacios
        if (txtIATA.getText() == null || txtIATA.getText().equals("") || txtName.getText() == null || txtName.getText().equals("") || txtType.getText() == null || txtType.getText().equals("")) {
            System.out.println("faltaron datos");

            PublicMethods.showAlert("Datos faltantes!", "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {
                System.out.println("entro a agregar");
                String name = txtName.getText();
                String type = txtType.getText();
                String IATA = txtIATA.getText();

                Airport newA = new Airport(name, type, IATA);
                addAirport(newA);
                PublicMethods.showAlert("Éxito", "Aeropuerto creado con éxito");

            } catch (Exception e) {
                e.printStackTrace();
                PublicMethods.showAlert("Error", "Hubo un error al guardar el aeropuerto");
            }

            PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAirports.fxml", "Administrar aeropuertos");
        }
    }

    @FXML
    void backButtonClicked(javafx.event.ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAirports.fxml", "Admin Worker");

    }

}
