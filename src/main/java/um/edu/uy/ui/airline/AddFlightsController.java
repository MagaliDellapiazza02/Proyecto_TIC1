package um.edu.uy.ui.airline;

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

    @FXML
    void backButtonClicked(javafx.event.ActionEvent event) throws IOException {

        close(event);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(AdministrarVuelosController.class.getResourceAsStream("/um/edu/uy/ui/user/airline/AdministrarVuelos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Administrar vuelos");
        stage.show();
    }

    @FXML
    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    private void close(javafx.event.ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void addFlight(Flight a) throws EntityAlreadyExists {
        //verificar en el front que los datos sean de tipo correcto antes de crear el avion. Checkear que el role este correcto

        if (flightRepository.findOneByFlightNumber(a.getFlightNumber()) != null) {
            throw new EntityAlreadyExists();
        }

        flightRepository.save(a);
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

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {
            try {
                //Airline airline = airlineRepository.findOneByAlnIATA(userRepository.findByMail(Session.mail).get().getCompany());//asumo que company es el IATA de la aerolinea
                Airline airline = airlineRepository.findOneByAlnIATA("PLU");

                String flightNumber = airline.getAlnIATA() + " " + txtFlightNumber.getText();

                Airport originAirport = airportRepository.findByIATA(txtOriginIATA.getText());
                Airport destinyAirport = airportRepository.findByIATA(txtDestinyIATA.getText());

                Airplane airplane = airplaneRepository.findByLicensePlate(txtLicensePlate.getText());

                // Convierto los strings en objetos Time
                Time arrivalTime = Time.valueOf(txtArrivalTime.getText()+":00");
                Time departureTime = Time.valueOf(txtDepartureTime.getText()+":00");

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
                addFlight(newA);

                if (reservaValidada(originAirport, destinyAirport, departureDate, arrivalDate, newA)) {
                    newA.setFlightState("Confirmed");
                    flightRepository.save(newA);
                    showAlert("Finalizado", "Vuelo agregado con éxito");
                } else {
                    showAlert("No hay disponibilidad", "No hay pistas o puertas disponibles para el vuelo");
                }

            } catch (Exception e) {
                e.printStackTrace();
                showAlert("", "Hubo un error al guardar o validar el vuelo");
            }
            close(event);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                Parent root = fxmlLoader.load(AdministrarVuelosController.class.getResourceAsStream("/um/edu/uy/ui/user/airline/AdministrarVuelos.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Administrar vuelos");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean reservaValidada(Airport originAirport, Airport destinyAirport, Date departureDate, Date arrivalDate, Flight flight) {
        List<Runway> availableRunwaysAtOrigin = runwayRepository.findAvailableOnesByAirportIDAndDate(originAirport.getId(), departureDate);
        List<Gate> availableGatesAtOrigin = gateRepository.findAvailableOnesByAirportIDAndDate(originAirport.getId(), departureDate);

        List<Gate> availableGatesAtDestiny = gateRepository.findAvailableOnesByAirportIDAndDate(destinyAirport.getId(), arrivalDate);
        List<Runway> availableRunwaysAtDestiny = runwayRepository.findAvailableOnesByAirportIDAndDate(destinyAirport.getId(), arrivalDate);

        if (availableGatesAtOrigin.isEmpty() || availableRunwaysAtOrigin.isEmpty() || availableGatesAtDestiny.isEmpty() || availableRunwaysAtDestiny.isEmpty()) {
            return false;
        } else {
            Gate originGate = availableGatesAtOrigin.get(0);
            Gate arrivalGate = availableGatesAtDestiny.get(0);
            Runway originRunway = availableRunwaysAtOrigin.get(0);
            Runway arrivalRunway = availableRunwaysAtDestiny.get(0);

            //creo tiempos predeterminados para cada reserva de puerta y pista
            Time gateReserveTime = new Time(0, 30, 0);
            Time runwayReserveTime = new Time(0, 2, 0);

            //creo las reservas de puerta y pista
            GateReservation originGateReservation = new GateReservation(originAirport, originGate, departureDate, flight, gateReserveTime);
            GateReservation arrivalGateReservation = new GateReservation(destinyAirport, arrivalGate, arrivalDate, flight, gateReserveTime);
            RunwayReservation originRunwayReservation = new RunwayReservation(originAirport, originRunway, departureDate, flight, runwayReserveTime);
            RunwayReservation arrivalRunwayReservation = new RunwayReservation(destinyAirport, arrivalRunway, arrivalDate, flight, runwayReserveTime);

            //guardo las reservas
            gateReservationRepository.save(originGateReservation);
            gateReservationRepository.save(arrivalGateReservation);
            runwayReservationRepository.save(originRunwayReservation);
            runwayReservationRepository.save(arrivalRunwayReservation);
            return true;
        }
    }
}