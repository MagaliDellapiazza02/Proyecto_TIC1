package um.edu.uy.ui.airline.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.business.entities.*;
import um.edu.uy.business.exceptions.EntityAlreadyExists;
import um.edu.uy.persistence.*;
import um.edu.uy.services.FlightMgr;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class AddFlightsController {
    @FXML
    private DatePicker arrivalDate;

    @FXML
    private DatePicker departureDate;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;

    @FXML
    private TextField txtArrivalTime;

    @FXML
    private TextField txtDepartureTime;

    @FXML
    private TextField txtDestinyIATA;

    @FXML
    private TextField txtFlightNumber;

    @FXML
    private TextField txtLicensePlate;

    @FXML
    private TextField txtOriginIATA;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private RunwayRepository runwayRepository;

    @Autowired
    private RunwayReservationRepository runwayReservationRepository;

    @Autowired
    private GateReservationRepository gateReservationRepository;

    @Autowired
    private UserMgr userMgr;
    @Autowired
    private FlightMgr flightMgr;

    @FXML
    void backButtonClicked(javafx.event.ActionEvent event) {
        String role = userMgr.getRoleByMail(Session.mail);
        if (role.equals("administrador")) {
            PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AdministrarVuelos.fxml", "Administrar vuelos");
        } else {
            PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/worker/WorkerVuelos.fxml", "Administrar Vuelos");
        }
    }

    @FXML
    private void close(javafx.event.ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


    @FXML
    void addBtnClicked(ActionEvent event) {
        if (txtArrivalTime.getText() == null || txtArrivalTime.getText().equals("") ||
                txtLicensePlate.getText() == null || txtLicensePlate.getText().equals("") ||
                txtDepartureTime.getText() == null || txtDepartureTime.getText().equals("") ||
                txtDestinyIATA.getText() == null || txtDestinyIATA.getText().equals("") ||
                txtFlightNumber.getText() == null || txtFlightNumber.getText().equals("") ||
                txtOriginIATA.getText() == null || txtOriginIATA.getText().equals("") ||
                arrivalDate == null || departureDate == null) {

            PublicMethods.showAlert("Datos faltantes!", "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {
                String[] company = userRepository.findByMail(Session.mail).get().getCompany().split("%");
                String alnIATA = company[1];
                Airline airline = airlineRepository.findOneByAlnIATA(alnIATA);
                //Airline airline= airlineRepository.findOneByAlnName("Pluna");
                String flightNumber = airline.getAlnIATA() + " " + txtFlightNumber.getText();

                Airport originAirport = airportRepository.findByIATA(txtOriginIATA.getText());
                Airport destinyAirport = airportRepository.findByIATA(txtDestinyIATA.getText());

                Airplane airplane = airplaneRepository.findByLicensePlate(txtLicensePlate.getText());

                // Convierto los strings en objetos Time
                Time arrivalTime = Time.valueOf(txtArrivalTime.getText() + ":00");
                Time departureTime = Time.valueOf(txtDepartureTime.getText() + ":00");

                // Convierto los DatePicker en objetos Date
                LocalDate departureDate1 = departureDate.getValue();
                LocalDate arrivalDate1 = arrivalDate.getValue();

                Date departureDate = Date.from(departureDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date arrivalDate = Date.from(arrivalDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());

                // Convierto los Time a milisegundos
                long arrivalTimeInMilliseconds = arrivalTime.getTime();
                long departureTimeInMilliseconds = departureTime.getTime();

                // Sumo los milisegundos a los Date
                arrivalDate.setTime(arrivalDate.getTime() + arrivalTimeInMilliseconds - 10800000); //resto 10.800.000 milisegundos (3 horas) para que quede en el horario correcto (no se por qué pero se suman 3hs)
                departureDate.setTime(departureDate.getTime() + departureTimeInMilliseconds - 10800000);

                Flight newA = new Flight(airline, originAirport, destinyAirport, airplane, departureDate, arrivalDate, flightNumber);
                flightMgr.addFlight(newA);
                PublicMethods.showAlert("Finalizado", "Vuelo agregado con éxito\nPendiente a validación de aeropuertos");

            } catch (Exception e) {
                e.printStackTrace();
                PublicMethods.showAlert("", "Hubo un error al guardar o validar el vuelo");
            }
            backButtonClicked(event);
        }
    }

}