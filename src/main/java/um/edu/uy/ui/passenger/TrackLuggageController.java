package um.edu.uy.ui.passenger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.Luggage;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.Session;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.persistence.LuggageRepository;
import um.edu.uy.persistence.PassengerRepository;
import um.edu.uy.services.LuggageMgr;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.ui.user.LController;

import um.edu.uy.ui.PublicMethods;
import um.edu.uy.ui.user.LController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TrackLuggageController implements Initializable {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelivered;

    @FXML
    private TableColumn<Luggage, String> colFlight;

    @FXML
    private TableColumn<Luggage, String> colState;

    @FXML
    private TableColumn<Luggage, String> colTrackingCode;

    @FXML
    private TableColumn<Luggage, Integer> colWeight;

    @FXML
    private TableView<Luggage> tableLuggages;

    @Autowired
    private LuggageMgr luggageMgr;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerMgr passengerMgr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Passenger passenger = passengerMgr.getPassengerByMail(Session.mail);

        agregarElementosALista(passenger);

    }

    private void agregarElementosALista(Passenger passenger) {
        // Configura las propiedades de las columnas

        colTrackingCode.setCellValueFactory(new PropertyValueFactory<>("trackingCode"));
        colFlight.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        colState.setCellValueFactory(new PropertyValueFactory<>("state"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));

        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<Luggage> elementos = luggageMgr.getLuggagesFromPassenger(passenger);

        //creo la lista que se mostrará al usuario
        ObservableList<Luggage> listaDeValijas = FXCollections.observableArrayList();

        //agrego vuelos que despegan y aterrizan
        for (Luggage luggage : elementos) {
            listaDeValijas.add(luggage);
        }

        tableLuggages.setItems(listaDeValijas);
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/passenger/PassengerWindow.fxml", "Pasajero");
    }

}
