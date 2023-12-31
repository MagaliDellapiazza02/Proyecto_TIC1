package um.edu.uy.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;
import um.edu.uy.ui.PublicMethods;

@Component
public class AdminAirportsController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Label labTitle;

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/UserAdminMenu.fxml", "Admin Menu");
    }

    @FXML
    void addAirportButtonClicked(ActionEvent event){
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AddAirport.fxml", "Crear aeropuerto");

    }

    @FXML
    void searchAirportButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/SearchAirport.fxml", "Buscar aeropuerto");
    }

    @FXML
    void setBtnSearch(ActionEvent event) {
    	//llamar al método de buscar


        //si encuentra algo, lo muestre
        //si no, que muestre un mensaje de error
    }

}