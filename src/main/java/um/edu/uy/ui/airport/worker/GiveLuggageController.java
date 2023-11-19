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
import um.edu.uy.business.entities.Luggage;
import um.edu.uy.business.entities.Session;
import um.edu.uy.persistence.FlightRepository;
import um.edu.uy.services.AirportMgr;
import um.edu.uy.services.LuggageMgr;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class GiveLuggageController implements Initializable {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelivered;

    @FXML
    private TableColumn<Luggage, String> colPassenger;

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


    @FXML
    public void deliveredButtonClicked(ActionEvent event) {
        try {
            Luggage luggage = tableLuggages.getSelectionModel().getSelectedItem();
            luggageMgr.luggageDelivered(luggage);
            PublicMethods.showAlert("Entregada", "Valija entregada");
        } catch (Exception e) {
            e.printStackTrace();
            PublicMethods.showAlert("ERROR!", "Seleccione un vuelo correctamente");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Flight flight = flightRepository.findOneByFlightNumber(Session.flightNumber);

        agregarElementosALista(flight);

        tableLuggages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Luggage>() {
            @Override
            public void changed(ObservableValue<? extends Luggage> observable, Luggage oldValue, Luggage newValue) {
                if (newValue != null) {
                    // Habilita el botón cuando se selecciona un vuelo
                    btnDelivered.setDisable(false);
                } else {
                    // Deshabilita el botón cuando no hay vuelo seleccionado
                    btnDelivered.setDisable(true);
                }
            }
        });
    }

    private void agregarElementosALista(Flight flight) {
        // Configura las propiedades de las columnas

        colTrackingCode.setCellValueFactory(new PropertyValueFactory<>("trackingCode"));
        colPassenger.setCellValueFactory(new PropertyValueFactory<>("passport"));
        colState.setCellValueFactory(new PropertyValueFactory<>("state"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));


        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<Luggage> elementos = luggageMgr.getLuggagesFromFlight(flight);

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
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airport/worker/GiveLuggageFlight.fxml", "Vuelo para entregar valijas");
    }
}
