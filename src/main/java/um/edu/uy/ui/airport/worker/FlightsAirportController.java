package um.edu.uy.ui.airport.worker;
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
import um.edu.uy.services.FlightMgr;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class FlightsAirportController implements Initializable {
    @FXML
    private Button btnBack;

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

    @FXML
    private TableColumn<Flight, String> colAirline1;

    @FXML
    private TableColumn<Flight, Date> colArrivalTime1;

    @FXML
    private TableColumn<Flight, Date> colDepartureTime1;

    @FXML
    private TableColumn<Flight, String> colDestinyAirport1;

    @FXML
    private TableColumn<Flight, String> colNumFlight1;

    @FXML
    private TableColumn<Flight, String> colOriginAirport1;

    @FXML
    private TableView<Flight> tableFlights1;

    @Autowired
    private AirportMgr airportMgr;

    @Autowired
    private FlightRepository flightRepository;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Airport a = airportMgr.findAirportWithUser(Session.mail);
        agregarElementosALista(a);
        agregarElementosALista1(a);
    }

    private void agregarElementosALista(Airport airport) {
        // Configura las propiedades de las columnas

        colNumFlight.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        colAirline.setCellValueFactory(new PropertyValueFactory<>("flightIATA"));
        colOriginAirport.setCellValueFactory(new PropertyValueFactory<>("originAirportIATA"));
        colDestinyAirport.setCellValueFactory(new PropertyValueFactory<>("destinyAirportIATA"));
        colArrivalTime.setCellValueFactory(new PropertyValueFactory<>("scheduledArrival"));
        colDepartureTime.setCellValueFactory(new PropertyValueFactory<>("scheduledDeparture"));


        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<Flight> elementos = flightRepository.findByFlightStateAndOriginAirport("Approved", airport);

        //creo la lista que se mostrará al usuario
        ObservableList<Flight> listaDeVuelos = FXCollections.observableArrayList();

        //agrego vuelos que despegan y aterrizan
        for (Flight flight : elementos) {
            listaDeVuelos.add(flight);
        }

        tableFlights.setItems(listaDeVuelos);
    }

    private void agregarElementosALista1(Airport airport) {
        // Configura las propiedades de las columnas

        colNumFlight1.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        colAirline1.setCellValueFactory(new PropertyValueFactory<>("flightIATA"));
        colOriginAirport1.setCellValueFactory(new PropertyValueFactory<>("originAirportIATA"));
        colDestinyAirport1.setCellValueFactory(new PropertyValueFactory<>("destinyAirportIATA"));
        colArrivalTime1.setCellValueFactory(new PropertyValueFactory<>("scheduledArrival"));
        colDepartureTime1.setCellValueFactory(new PropertyValueFactory<>("scheduledDeparture"));


        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<Flight> elementos = flightRepository.findByFlightStateAndDestinyAirport("Approved", airport);

        //creo la lista que se mostrará al usuario
        ObservableList<Flight> listaDeVuelos = FXCollections.observableArrayList();

        //agrego vuelos que despegan y aterrizan
        for (Flight flight : elementos) {
            listaDeVuelos.add(flight);
        }

        tableFlights1.setItems(listaDeVuelos);
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/AirportWorker.fxml", "Trabajado Aeropuerto");
    }
}
