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
import um.edu.uy.business.entities.GateReservation;
import um.edu.uy.business.entities.RunwayReservation;
import um.edu.uy.business.entities.Session;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.persistence.RunwayReservationRepository;
import um.edu.uy.persistence.UserRepository;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class RunwayReservationsController implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private Button eraseBtn;
    @FXML
    private TableView<RunwayReservation> reservationsList;
    @FXML
    private TableColumn<RunwayReservation, Date> dateColumn;
    @FXML
    private TableColumn<RunwayReservation, String> flightNumberColumn;
    @FXML
    private TableColumn<RunwayReservation, String> runwayColumn;
    @FXML
    private TableColumn<RunwayReservation, String> stateColumn;

    @Autowired
    private RunwayReservationRepository runwayReservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AirportRepository airportRepository;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] company = userRepository.findByMail(Session.mail).get().getCompany().split("%");
        String userAirportName = company[1];
        //String userAirportName = "Carrasco";
        agregarElementosALista(userAirportName);
        reservationsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RunwayReservation>() {
            @Override
            public void changed(ObservableValue<? extends RunwayReservation> observable, RunwayReservation oldValue, RunwayReservation newValue) {
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
        runwayColumn.setCellValueFactory(new PropertyValueFactory<>("runwayNumber"));

        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<RunwayReservation> elementos = runwayReservationRepository.findByAirport_IATAAndFlightConfirmedFalse(airportRepository.findByName(userAirportName).getIATA());

        //creo la lista que se mostrará al usuario
        ObservableList<RunwayReservation> listaDeReservas = FXCollections.observableArrayList();

        //agrego vuelos que despegan y aterrizan
        for (RunwayReservation reservation : elementos) {
            listaDeReservas.add(reservation);
        }
        reservationsList.setItems(listaDeReservas);
    }

    @FXML
    void eraseButtonClicked(ActionEvent event) {
        RunwayReservation reservation = reservationsList.getSelectionModel().getSelectedItem();
        runwayReservationRepository.delete(reservation);
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airport/worker/AirportWorker.fxml", "Trabajador Aeropuerto");
    }
}
