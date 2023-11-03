package um.edu.uy.ui.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Airplane;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.AirplaneRepository;
import um.edu.uy.ui.PublicMethods;

@Component
public class AddAirplaneController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;

    @FXML
    private TextField txtLicensePlate;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtSeats;

    @FXML
    private TextField txtLuggage;

    @Autowired
    private AirplaneRepository airplaneRepository;

    private void addAirplane(Airplane a) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el avion. Checkear que el role este correcto

        if (airplaneRepository.findByLicensePlate(a.getLicensePlate()) != null) {
            throw new EntityAlreadyExists();
        }

        airplaneRepository.save(a);
    }

    @FXML
    void addBtnClicked(javafx.event.ActionEvent event) {
        // Checkear que se haya llenado todos los espacios
        if (txtLuggage.getText() == null || txtLuggage.getText().equals("") ||
                txtSeats.getText() == null || txtSeats.getText().equals("") ||
                txtType.getText() == null || txtType.getText().equals("") ||
                txtLicensePlate.getText() == null || txtLicensePlate.getText().equals("")) {

            PublicMethods.showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {
                String licensePlate = txtLicensePlate.getText();
                String type = txtType.getText();
                int luggage = Integer.parseInt(txtLuggage.getText());
                int seats = Integer.parseInt(txtSeats.getText());

                Airplane newA = new Airplane(licensePlate, type, seats, luggage);
                addAirplane(newA);
                PublicMethods.showAlert("", "Avión agregado con éxito");

            } catch (Exception e) {
                e.printStackTrace();
                PublicMethods.showAlert("", "Hubo un error al guardar el avión");
            }

            PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AlnWorkerAdmin.fxml", "Administrar aeropuertos");

        }
    }

    @FXML
    void backButtonClicked(javafx.event.ActionEvent event) {

        //volver a la ventana previa
    }
}
