package um.edu.uy.ui.passenger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.business.entities.Luggage;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.Session;
import um.edu.uy.persistence.LuggageRepository;
import um.edu.uy.persistence.PassengerRepository;
import um.edu.uy.ui.user.LController;

import um.edu.uy.ui.PublicMethods;
import um.edu.uy.ui.user.LController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TrackLuggageController implements Initializable {

    @FXML
    private TableView<Luggage> luggageList;
    @FXML
    private TableColumn<Luggage, String> trackingCodeColumn;
    @FXML
    private TableColumn<Luggage, Integer> weightColumn;
    @FXML
    private TableColumn<Luggage, String> stateColumn;

    @FXML
    private Button backBtn;

    @Autowired
    private LuggageRepository luggageRepository;
    @Autowired
    private LController loginController;
    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        agregarElementosALista();
    }

    private void agregarElementosALista() {
        trackingCodeColumn.setCellValueFactory(new PropertyValueFactory<>("trackingCode"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        Passenger passenger = passengerRepository.findByMailWithLuggageList(Session.mail).orElseThrow();

        ObservableList<Luggage> listaDeEquipaje = FXCollections.observableArrayList();

        // Ahora la colección luggageList está cargada y se puede acceder sin problemas
        listaDeEquipaje.addAll(passenger.getLuggageList());

        luggageList.setItems(listaDeEquipaje);
    }

    @FXML
    void backButtonClicked(ActionEvent event) throws Exception {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/passenger/PassengerWindow.fxml", "Pasajero");
    }
}
