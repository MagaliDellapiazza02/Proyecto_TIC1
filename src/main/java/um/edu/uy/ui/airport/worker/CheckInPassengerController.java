package um.edu.uy.ui.airport.worker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.Session;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.services.FlightMgr;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CheckInPassengerController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnNext;

    @FXML
    private TableColumn<Passenger, Long> colDocument;

    @FXML
    private TableColumn<Passenger, String> colName;

    @FXML
    private TableColumn<Passenger, String> colNationality;

    @FXML
    private TableColumn<Passenger, String> colPassport;

    @FXML
    private TableView<Passenger> tablePassengers;

    @Autowired
    private PassengerMgr passengerMgr;

    @Autowired
    private FlightRepository flightRepository;


    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/CheckInFlight.fxml", "CheckIn Vuelo");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }

    @FXML
    public void addButtonClicked(ActionEvent event) {
        try {
            Passenger passenger = tablePassengers.getSelectionModel().getSelectedItem();
            Flight flight = flightRepository.findOneByFlightNumber(Session.flightNumber);
            passengerMgr.passengerCheckedIn(flight, passenger);
            Session.passengerPassport = passenger.getPassport();
            PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/CheckInPassengerSelected.fxml", "CheckIn Pasajero");

        } catch (Exception e) {
            e.printStackTrace();
            PublicMethods.showAlert("ERROR!", "Seleccione un pasajero correctamente");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String flightNumber = Session.flightNumber;

        agregarElementosALista(flightNumber);

        tablePassengers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Passenger>() {
            @Override
            public void changed(ObservableValue<? extends Passenger> observable, Passenger oldValue, Passenger newValue) {
                if (newValue != null) {
                    // Habilita el botón cuando se selecciona un vuelo
                    btnNext.setDisable(false);
                } else {
                    // Deshabilita el botón cuando no hay vuelo seleccionado
                    btnNext.setDisable(true);
                }
            }
        });
    }

    private void agregarElementosALista(String flightNumber) {
        // Configura las propiedades de las columnas
        colDocument.setCellValueFactory(new PropertyValueFactory<>("document"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        colPassport.setCellValueFactory(new PropertyValueFactory<>("passport"));


        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<Passenger> elementos = passengerMgr.getPassengersFromFlight(flightNumber);

        //creo la lista que se mostrará al usuario
        ObservableList<Passenger> listaPasajeros = FXCollections.observableArrayList();

        //agrego vuelos que despegan y aterrizan
        for (Passenger passenger : elementos) {
            listaPasajeros.add(passenger);
        }

        tablePassengers.setItems(listaPasajeros);
    }
}
