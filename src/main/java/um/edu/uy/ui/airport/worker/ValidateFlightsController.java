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
import um.edu.uy.business.entities.*;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.business.exceptions.InvalidInformation;
import um.edu.uy.persistence.*;
import um.edu.uy.services.*;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class ValidateFlightsController implements Initializable {

    @FXML
    private TableView<Flight> flightList;

    @FXML
    private TableColumn<Flight, String> arrivalAirportColumn;

    @FXML
    private TableColumn<Flight, Date> arrivalDateColumn;

    @FXML
    private TableColumn<Flight, String> departureAirportColumn;

    @FXML
    private TableColumn<Flight, Date> departureDateColumn;

    @FXML
    private TableColumn<Flight, String> flightNumberColumn;

    @FXML
    private Button ValidateBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button cancelBtn;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightMgr flightMgr;

    @Autowired
    private UserMgr userMgr;

    @Autowired
    private AirportMgr airportMgr;

    @Autowired
    private GateReservationMgr gateReservationMgr;

    @Autowired
    private RunwayReservationMgr runwayReservationMgr;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private GateReservationRepository gateReservationRepository;

    @Autowired
    private RunwayReservationRepository runwayReservationRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] company = userMgr.findByMail(Session.mail).get().getCompany().split("%");
        String userAirportName = company[1];
        //String userAirportName = "Carrasco"; //para probar
        agregarElementosALista(userAirportName);
        flightList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Flight>() {
            @Override
            public void changed(ObservableValue<? extends Flight> observable, Flight oldValue, Flight newValue) {
                if (newValue != null) {
                    // Habilita el botón cuando se selecciona un vuelo
                    ValidateBtn.setDisable(false);
                    cancelBtn.setDisable(false);
                } else {
                    // Deshabilita el botón cuando no hay vuelo seleccionado
                    ValidateBtn.setDisable(true);
                    cancelBtn.setDisable(true);
                }
            }
        });
    }

    private void agregarElementosALista(String userAirportName) {
        // Configura las propiedades de las columnas
        arrivalAirportColumn.setCellValueFactory(new PropertyValueFactory<>("destinyAirportIATA"));
        arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<>("scheduledArrival"));
        departureAirportColumn.setCellValueFactory(new PropertyValueFactory<>("originAirportIATA"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("scheduledDeparture"));
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));


        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        //Iterable<Flight> elementos = flightRepository.findByOriginApprovedAndOriginAirportIATAAndFlightState(false, airportRepository.findByName(userAirportName).getIATA(), "Pending");
        Iterable<Flight> elementos = flightMgr.getFlightsFromDepartureAirport(false, airportMgr.findByName(userAirportName).getIATA(), "Pending");
        //agrego vuelos pendientes de validar que despeguen en el aeropuerto del usuario
        Iterable<Flight> elementos2 = flightMgr.getFlightsFromArrivalAirport(false, airportMgr.findByName(userAirportName).getIATA(), "Pending");

        //creo la lista que se mostrará al usuario
        ObservableList<Flight> listaDeVuelos = FXCollections.observableArrayList();

        //agrego vuelos que despegan y aterrizan
        for (Flight flight : elementos) {
            listaDeVuelos.add(flight);
        }
        for (Flight flight : elementos2) {
            listaDeVuelos.add(flight);
        }

        flightList.setItems(listaDeVuelos);
    }

    @FXML
    void ValidateButtonClicked(ActionEvent event) throws EntityAlreadyExists, InvalidInformation {
        String[] company = userMgr.findByMail(Session.mail).get().getCompany().split("%");
        String userAirportName = company[1];
        //String userAirportName = "Carrasco"; //para probar
        Flight flight = flightList.getSelectionModel().getSelectedItem();

        //el vuelo sale del aeropuerto del usuario
        if (airportMgr.findByName(userAirportName).getIATA().equals(flight.getOriginAirport().getIATA())) {
            if (flightMgr.validateFlightOnOrigin(flight.getOriginAirport(), flight.getScheduledDeparture(), flight)) {
                flight.setOriginApproved(true);
                PublicMethods.showAlert("Validado", "El vuelo ha sido validado");
            } else {
                PublicMethods.showAlert("Error", "El vuelo no se puede validar\nNo hay disponibilidad");
                backButtonClicked(event);
            }
        //el vuelo llega al aeropuerto del usuario
        } else if (airportMgr.findByName(userAirportName).getIATA().equals(flight.getDestinyAirport().getIATA())) {
            if (flightMgr.validateFlightOnDestiny(flight.getDestinyAirport(), flight.getScheduledArrival(), flight)) {
                flight.setDestinyApproved(true);
                PublicMethods.showAlert("Validado", "El vuelo ha sido validado");
            } else {
                PublicMethods.showAlert("Error", "El vuelo no se puede validar\nNo hay disponibilidad");
                backButtonClicked(event);
            }
        } else {
            PublicMethods.showAlert("Error", "El aeropuerto no coincide con el vuelo");
        }

        //si el vuelo pasa a tener validación de origen y destino, pasa a estar aprobado
        if (flight.isOriginApproved() && flight.isDestinyApproved()) {
            flight.setFlightState("Approved");
            String IATADestino = flight.getDestinyAirportIATA();
            String IATAOrigen = flight.getOriginAirportIATA();
            GateReservation reservaPuertaOrigen = gateReservationMgr.findGateReservation(flight, IATAOrigen);
            RunwayReservation reservaPistaOrigen = runwayReservationMgr.findRunwayReservation(flight, IATAOrigen);
            GateReservation reservaPuertaDestino = gateReservationMgr.findGateReservation(flight, IATADestino);
            RunwayReservation reservaPistaDestino = runwayReservationMgr.findRunwayReservation(flight, IATADestino);

            reservaPuertaOrigen.setFlightConfirmed(true);
            reservaPistaOrigen.setFlightConfirmed(true);
            reservaPuertaDestino.setFlightConfirmed(true);
            reservaPistaDestino.setFlightConfirmed(true);

            gateReservationMgr.addGateReservation(reservaPuertaOrigen);
            runwayReservationMgr.addRunwayReservation(reservaPistaOrigen);
            gateReservationMgr.addGateReservation(reservaPuertaDestino);
            runwayReservationMgr.addRunwayReservation(reservaPistaDestino);
        }
        flightMgr.saveFlight(flight);
        backButtonClicked(event);
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airport/worker/AirportWorker.fxml", "Trabajador Aeropuerto");
    }

    public void CancelButtonClicked(ActionEvent actionEvent) throws EntityAlreadyExists, InvalidInformation {
        Flight flight = flightList.getSelectionModel().getSelectedItem();
        flight.setFlightState("Canceled");
        flightMgr.addFlight(flight);

        PublicMethods.showAlert("Cancelado", "El vuelo ha sido cancelado");
        backButtonClicked(actionEvent);
    }
}