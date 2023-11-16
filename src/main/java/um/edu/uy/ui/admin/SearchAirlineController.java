package um.edu.uy.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.Label;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.persistence.AirlineRepository;
import um.edu.uy.ui.PublicMethods;

@Component
public class SearchAirlineController {

    @FXML
    private Label labAirline;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtIATA;

    @Autowired
    private AirlineRepository alnRepository;

    @FXML
    void searchButtonClicked(ActionEvent event) {
        try {
            if (txtIATA.getText() == null || txtIATA.getText().equals("")) {

                PublicMethods.showAlert(
                        "Datos faltantes!",
                        "No se ingresaron los datos necesarios para completar el ingreso.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            PublicMethods.showAlert("ERROR!", "Ingrese correctamente todos los espacios");
        }

        String IATA = txtIATA.getText();

        Airline aln = alnRepository.findOneByAlnIATA(IATA);





    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAln.fxml", "Administrar Aerolineas");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }
}
