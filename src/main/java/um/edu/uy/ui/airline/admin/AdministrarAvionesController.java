package um.edu.uy.ui.airline.admin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.business.entities.Airplane;
import um.edu.uy.business.entities.Session;
import um.edu.uy.services.AirlineMgr;
import um.edu.uy.services.AirplaneMgr;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;
import um.edu.uy.ui.passenger.SignUpController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class AdministrarAvionesController implements Initializable {

    @FXML
    private ImageView airplaneIV;

    @FXML
    private TableView<Airplane> airplanesTableView;

    @FXML
    private Button btnAddPlane;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDeletePlane;

    @FXML
    private Button btnLogOut;

    @FXML
    private TableColumn<Airplane, Long> idColumn;

    @FXML
    private TableColumn<Airplane, String> licensePlateColumn;

    @FXML
    private TableColumn<Airplane, Integer> luggageColumn;

    @FXML
    private TableColumn<Airplane, Integer> seatsColumn;

    @FXML
    private TableColumn<Airplane, String> typeColumn;

    @Autowired
    private UserMgr userMgr;

    @Autowired
    private AirlineMgr airlineMgr;



    public void initialize(URL location, ResourceBundle resources) {
        try {
            String[] company = userMgr.findByMail(Session.mail).get().getCompany().split("%");
            String IATA = company[1];
            agregarElementosALista(IATA);
            airplanesTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airplane>() {
                @Override
                public void changed(ObservableValue<? extends Airplane> observable, Airplane oldValue, Airplane newValue) {
                    if (newValue != null) {
                        // Habilita el botón cuando se selecciona un vuelo
                        btnDeletePlane.setDisable(false);
                    } else {
                        // Deshabilita el botón cuando no hay vuelo seleccionado
                        btnDeletePlane.setDisable(true);
                    }
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agregarElementosALista(String airlineIATA) {
        // Configura las propiedades de las columnas
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seatCapacity"));
        luggageColumn.setCellValueFactory(new PropertyValueFactory<>("luggageCapacity"));

        Iterable<Airplane> elementos = airlineMgr.findAirplanesByIATA(airlineIATA);

        //creo la lista que se mostrará al usuario
        ObservableList<Airplane> listaDeAviones = FXCollections.observableArrayList();

        System.out.println("Aviones: " + listaDeAviones);

        for (Airplane airplane : elementos) {
            listaDeAviones.add(airplane);
        }
        airplanesTableView.setItems(listaDeAviones);
    }


    @FXML
    void deleteButtonClicked(ActionEvent event) {
        //lo de balta
    }


    @FXML
    void addAirplaneButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AddAirplane.fxml", "Agregar Aviones");
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/airline/admin/AlnWorkerAdmin.fxml", "Administrador Aerolinea");
    }



}
