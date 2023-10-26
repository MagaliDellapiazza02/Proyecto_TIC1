package um.edu.uy.ui.passenger;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.Main;
import um.edu.uy.business.entities.Luggage;
import um.edu.uy.persistence.AirplaneRepository;
import um.edu.uy.persistence.LuggageRepository;
import um.edu.uy.persistence.PassengerRepository;
import um.edu.uy.ui.user.LogInController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class TrackLuggageController implements Initializable {

    @FXML
    private TableView<Luggage> luggageList;
    @FXML
    private TableColumn<Luggage, String> trackingCodeColumn;
    @FXML
    private TableColumn<Luggage, Integer> weightColumn;
    @FXML
    private TableColumn<Luggage, String> stateColumn;

    @FXML
    private Button backBtn;

    @Autowired
    private LuggageRepository luggageRepository;
    @Autowired
    private LogInController loginController;
    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        agregarElementosALista();
    }

    private void agregarElementosALista() {
        trackingCodeColumn.setCellValueFactory(new PropertyValueFactory<>("trackingCode"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        Iterable<Luggage> elementos = passengerRepository.findByMail(loginController.getTxtUsername().getText()).get().getLuggageList();

        ObservableList<Luggage> listaDeEquipaje = FXCollections.observableArrayList();

        for (Luggage luggage : elementos) {
            listaDeEquipaje.add(luggage);
        }

        luggageList.setItems(listaDeEquipaje);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void backButtonClicked(ActionEvent event) throws Exception {

        close(event);

        // Open the User window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(PassengerWindowController.class.getResourceAsStream("/um/edu/uy/ui/user/passenger/PassengerWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Información");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
