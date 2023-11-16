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
import um.edu.uy.business.entities.GateReservation;
import um.edu.uy.business.entities.RunwayReservation;
import um.edu.uy.business.entities.Session;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.persistence.GateReservationRepository;
import um.edu.uy.persistence.UserRepository;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
@Component
public class GateReservationsController implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private Button eraseBtn;
    @FXML
    private TableView<GateReservation> reservationsList;
    @FXML
    private TableColumn<GateReservation, Date> dateColumn;
    @FXML
    private TableColumn<GateReservation, String> flightNumberColumn;
    @FXML
    private TableColumn<GateReservation, String> gateColumn;
    @FXML
    private TableColumn<GateReservation, String> stateColumn;
    @Autowired
    private GateReservationRepository gateReservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private FlightRepository flightRepository;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] company = userRepository.findByMail(Session.mail).get().getCompany().split("%");
        String userAirportName = company[1];
        //String userAirportName = "Carrasco";
        agregarElementosALista(userAirportName);
        reservationsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GateReservation>() {
            @Override
            public void changed(ObservableValue<? extends GateReservation> observable, GateReservation oldValue, GateReservation newValue) {
                if (newValue != null) {
                    // Habilita el botón cuando se selecciona un vuelo
                    eraseBtn.setDisable(false);
                } else {
                    // Deshabilita el botón cuando no hay vuelo seleccionado
                    eraseBtn.setDisable(true);
                }
            }
        });
    }

    private void agregarElementosALista(String userAirportName) {
        // Configura las propiedades de las columnas
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        gateColumn.setCellValueFactory(new PropertyValueFactory<>("gateNumber"));

        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<GateReservation> elementos = gateReservationRepository.findByAirport_IATAAndFlightConfirmedFalse(airportRepository.findByName(userAirportName).getIATA());

        //creo la lista que se mostrará al usuario
        ObservableList<GateReservation> listaDeReservas = FXCollections.observableArrayList();

        //agrego vuelos que despegan y aterrizan
        for (GateReservation reservation : elementos) {
            listaDeReservas.add(reservation);
        }
        reservationsList.setItems(listaDeReservas);
    }

    @FXML
    void eraseButtonClicked(ActionEvent event) {
        GateReservation reservation = reservationsList.getSelectionModel().getSelectedItem();
        gateReservationRepository.delete(reservation);
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airport/worker/AirportWorker.fxml", "Trabajador Aeropuerto");
    }
}
