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
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.Passenger;
import um.edu.uy.business.entities.Session;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.services.AirportMgr;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class GiveLuggageFlightController implements Initializable {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnNext;

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
    private AirportMgr airportMgr;

    @Autowired
    private FlightRepository flightRepository;


    @FXML
    public void nextButtonClicked(ActionEvent event) {
        try {
            Flight flight = tableFlights.getSelectionModel().getSelectedItem();
            Session.flightNumber = flight.getFlightNumber();
            PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/GiveLuggage.fxml", "Entrega Valija");

        } catch (Exception e) {
            e.printStackTrace();
            PublicMethods.showAlert("ERROR!", "Seleccione un vuelo correctamente");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Airport airport = airportMgr.findAirportWithUser(Session.mail);

        agregarElementosALista(airport);

        tableFlights.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Flight>() {
            @Override
            public void changed(ObservableValue<? extends Flight> observable, Flight oldValue, Flight newValue) {
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

    private void agregarElementosALista(Airport airport) {
        // Configura las propiedades de las columnas

        colNumFlight.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        colAirline.setCellValueFactory(new PropertyValueFactory<>("flightIATA"));
        colOriginAirport.setCellValueFactory(new PropertyValueFactory<>("originAirportIATA"));
        colDestinyAirport.setCellValueFactory(new PropertyValueFactory<>("destinyAirportIATA"));
        colArrivalTime.setCellValueFactory(new PropertyValueFactory<>("scheduledArrival"));
        colDepartureTime.setCellValueFactory(new PropertyValueFactory<>("scheduledDeparture"));


        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<Flight> elementos = flightRepository.findByFlightStateAndDestinyAirport("Approved", airport);

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
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/AirportWorker.fxml", "Trabajador Aeropuerto");
    }
}
