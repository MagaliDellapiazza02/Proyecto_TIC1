package um.edu.uy.ui.airline.worker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.Session;
import um.edu.uy.persistence.AirlineRepository;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.services.AirlineMgr;
import um.edu.uy.services.PassengerMgr;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class AddPassengerController implements Initializable {


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogOut;

    @FXML
    private MenuButton menuBtnFlights;

    @FXML
    private TextField txtPassport;

    @FXML
    private TableColumn<Flight, String> colDestinyAirport;

    @FXML
    private TableColumn<Flight, String> colFreeSpaces;

    @FXML
    private TableColumn<Flight, String> colNumFlight;

    @FXML
    private TableColumn<Flight, String> colOriginAirport;

    @FXML
    private TableView<Flight> tableFlights;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineMgr airlineMgr;

    @FXML
    void menuBtnFlightsClicked(ActionEvent event) {
        String alnIATA = airlineMgr.findAirlineWithUser(Session.mail);
        List<Flight> flights = flightRepository.findAllByFlightIATA(alnIATA);
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            if (f.getFlightState().equals("Approved")) {
                System.out.println(f.getFlightNumber());
                MenuItem mItem = new MenuItem(f.getFlightNumber());
                menuBtnFlights.getItems().add(mItem);
            }
        }

    }

    @FXML
    void addButtonClicked(ActionEvent event) {

    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airline/worker/AlnWorkerUser.fxml", "CheckIn Pasajero");

    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String alnIATA = airlineMgr.findAirlineWithUser(Session.mail);

        agregarElementosALista(alnIATA);

        tableFlights.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Flight>() {
            @Override
            public void changed(ObservableValue<? extends Flight> observable, Flight oldValue, Flight newValue) {
                if (newValue != null) {
                    // Habilita el botón cuando se selecciona un vuelo
                    btnAdd.setDisable(false);
                } else {
                    // Deshabilita el botón cuando no hay vuelo seleccionado
                    btnAdd.setDisable(true);
                }
            }
        });
    }

    private void agregarElementosALista(String alnIATA) {
        // Configura las propiedades de las columnas
        colNumFlight.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        colFreeSpaces.setCellValueFactory(new PropertyValueFactory<>("passengersLeft"));
        colOriginAirport.setCellValueFactory(new PropertyValueFactory<>("originAirportIATA"));
        colDestinyAirport.setCellValueFactory(new PropertyValueFactory<>("destinyAirportIATA"));


        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<Flight> elementos = flightRepository.findByFlightStateAndFlightIATA("Approved", alnIATA);

        //creo la lista que se mostrará al usuario
        ObservableList<Flight> listaDeVuelos = FXCollections.observableArrayList();

        //agrego vuelos que despegan y aterrizan
        for (Flight flight : elementos) {
            listaDeVuelos.add(flight);
        }

        tableFlights.setItems(listaDeVuelos);
    }
}
