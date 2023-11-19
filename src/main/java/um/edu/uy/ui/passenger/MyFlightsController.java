package um.edu.uy.ui.passenger;

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
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.Session;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.services.AirportMgr;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


@Component
public class MyFlightsController implements Initializable {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogOut;

    @FXML
    private TableColumn<Flight, String> colAirline;

    @FXML
    private TableColumn<Flight, Date> colArrivalTime;

    @FXML
    private TableColumn<Flight, Date> colDepartureTime;

    @FXML
    private TableColumn<Flight, String> colDestinyAirport;

    @FXML
    private TableColumn<Flight, String> colNumFlight;

    @FXML
    private TableColumn<Flight, String> colOriginAirport;

    @FXML
    private TableView<Flight> tableFlights;

    @Autowired
    private PassengerMgr passengerMgr;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Passenger passenger = passengerMgr.getPassengerByMail(Session.mail);
        agregarElementosALista(passenger);
    }

    private void agregarElementosALista(Passenger passenger) {
        // Configura las propiedades de las columnas

        colNumFlight.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        colAirline.setCellValueFactory(new PropertyValueFactory<>("airlineOwner.alnName"));
        colOriginAirport.setCellValueFactory(new PropertyValueFactory<>("originAirportIATA"));
        colDestinyAirport.setCellValueFactory(new PropertyValueFactory<>("destinyAirportIATA"));
        colArrivalTime.setCellValueFactory(new PropertyValueFactory<>("scheduledArrival"));
        colDepartureTime.setCellValueFactory(new PropertyValueFactory<>("scheduledDeparture"));


        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<Flight> elementos = passengerMgr.getFlightsFromPassenger(passenger);

        //creo la lista que se mostrará al usuario
        ObservableList<Flight> listaDeVuelos = FXCollections.observableArrayList();

        //agrego vuelos que despegan y aterrizan
        for (Flight flight : elementos) {
            listaDeVuelos.add(flight);
        }

        tableFlights.setItems(listaDeVuelos);
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/passenger/PassengerWindow.fxml", "Pasajero");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }
}
