package um.edu.uy.ui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Airport;
import um.edu.uy.persistence.AirlineRepository;
import um.edu.uy.persistence.AirportRepository;
import um.edu.uy.ui.PublicMethods;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class SearchAirportController implements Initializable {

    @FXML
    private TableView<Airport> airportTableView;
    @FXML
    private TableColumn<Airport, Long> idTableColumn;
    @FXML
    private TableColumn<Airport, String> IATATableColumn;
    @FXML
    private TableColumn<Airport, String> nameTableColumn;
    @FXML
    private TableColumn<Airport, String> countryTableColumn;
    @FXML
    private TableColumn<Airport, String> typeTableColumn;
    
    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtBar2;
    

    @Autowired
    private AirportRepository airportRepository;

    private List<Airport> elementosAirport;
    
    private ObservableList<Airport> airportObservableList = FXCollections.observableArrayList();
    

    @FXML
    void searchButtonClicked(ActionEvent event) {

    }


    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAirports.fxml", "Administrar Aeropuertos");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        elementosAirport.clear();
        PublicMethods.logOut(event);
    }

    public void initialize(URL location, ResourceBundle resources) {
        if(airportObservableList != null){
            airportObservableList.clear();
        }
        elementosAirport = airportRepository.findAll();
        try {

            //elementos = alnRepository.getAirlineByAlnIATA(txtIATA.getText());

            idTableColumn.setCellValueFactory(new PropertyValueFactory<Airport, Long>("id"));
            nameTableColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("name"));
            typeTableColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("type"));
            countryTableColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("country"));
            IATATableColumn.setCellValueFactory(new PropertyValueFactory<Airport, String>("IATA"));

            for (Airport airport : elementosAirport) {
                airportObservableList.add(airport);
            }

            airportTableView.setItems(airportObservableList);

            FilteredList<Airport> filteredData = new FilteredList<>(airportObservableList, b -> true);

            txtBar2.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(airport -> {
                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if(String.valueOf(airport.getId()).indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else if (airport.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 && airport.getId() != null) {
                        return true;
                    } else if (airport.getIATA().toLowerCase().indexOf(lowerCaseFilter) != -1 && airport.getIATA() != null) {
                        return true;
                    } else if (airport.getCountry().toLowerCase().indexOf(lowerCaseFilter) != -1 && airport.getCountry() != null) {
                        return true;
                    } else if (airport.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 && airport.getType() != null) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<Airport> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(airportTableView.comparatorProperty());

            airportTableView.setItems(sortedData);

        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se encontró el aeropuerto");
        }

    }

}
