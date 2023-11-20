package um.edu.uy.ui.airline.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.business.entities.Session;
import um.edu.uy.services.AirlineMgr;
import um.edu.uy.services.FlightMgr;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class ShowAlnFlightsController implements Initializable {
    @FXML
    private ImageView airplaneIV;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogOut;

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
    private UserMgr userMgr;

    @Autowired
    private FlightMgr flightMgr;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            String[] company = userMgr.findByMail(Session.mail).get().getCompany().split("%");
            String IATA = company[1];
            System.out.println(IATA);
            agregarElementosALista(IATA);
        }
        catch (Exception e){
        e.printStackTrace();
        }
    }

    private void agregarElementosALista(String airline) {
        // Configura las propiedades de las columnas

        colNumFlight.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        colOriginAirport.setCellValueFactory(new PropertyValueFactory<>("originAirportIATA"));
        colDestinyAirport.setCellValueFactory(new PropertyValueFactory<>("destinyAirportIATA"));
        colArrivalTime.setCellValueFactory(new PropertyValueFactory<>("scheduledArrival"));
        colDepartureTime.setCellValueFactory(new PropertyValueFactory<>("scheduledDeparture"));


        //agrego vuelos pendientes de validar que aterricen en el aeropuerto del usuario
        Iterable<Flight> elementos = flightMgr.getFlightsByFlightIATA(airline);

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
        PublicMethods.changeWindow(event,"/um/edu/uy/ui/user/airline/admin/AdministrarVuelos.fxml", "Trabajado Aeropuerto");
    }

}
