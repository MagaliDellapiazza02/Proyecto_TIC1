package um.edu.uy.ui.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.Label;
import um.edu.uy.business.entities.Airline;
import um.edu.uy.business.entities.Flight;
import um.edu.uy.persistence.AirlineRepository;
import um.edu.uy.ui.PublicMethods;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class SearchAirlineController implements Initializable {

    @FXML
    private TableView<Airline> airlineTableView;
    @FXML
    private TableColumn<Airline, Long> idTableColumn;
    @FXML
    private TableColumn<Airline, String> alnIATATableColumn;
    @FXML
    private TableColumn<Airline, String> alnICATOTableColumn;
    @FXML
    private TableColumn<Airline, String> alnNameTableColumn;
    @FXML
    private TableColumn<Airline, String> alnCountryTableColumn;

    @FXML
    private Label labAirline;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogOut;

    @FXML
    private TextField txtBar;

    @Autowired
    private AirlineRepository alnRepository;

    private List<Airline> elementos;

    ObservableList<Airline> airlineObservableList = FXCollections.observableArrayList();


    @FXML
    void backButtonClicked(ActionEvent event) {
        elementos.clear();
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminAln.fxml", "Administrar Aerolineas");
    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(airlineObservableList != null){
            airlineObservableList.clear();
        }
        elementos = alnRepository.findAll();
        try {

            //elementos = alnRepository.getAirlineByAlnIATA(txtIATA.getText());

            idTableColumn.setCellValueFactory(new PropertyValueFactory<Airline, Long>("id"));
            alnIATATableColumn.setCellValueFactory(new PropertyValueFactory<Airline, String>("alnIATA"));
            alnICATOTableColumn.setCellValueFactory(new PropertyValueFactory<Airline, String>("alnICAO"));
            alnNameTableColumn.setCellValueFactory(new PropertyValueFactory<Airline, String>("alnName"));
            alnCountryTableColumn.setCellValueFactory(new PropertyValueFactory<Airline, String>("alnCountry"));

            for (Airline airline : elementos) {
                airlineObservableList.add(airline);
            }

                airlineTableView.setItems(airlineObservableList);

                FilteredList<Airline> filteredData = new FilteredList<>(airlineObservableList, b -> true);

                txtBar.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredData.setPredicate(airline -> {
                        if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                            return true;
                        }

                        String lowerCaseFilter = newValue.toLowerCase();


                        if(String.valueOf(airline.getId()).indexOf(lowerCaseFilter) != -1){
                            return true;
                        }
                        if (airline.getAlnIATA().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (airline.getAlnICAO().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (airline.getAlnName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (airline.getAlnCountry().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else
                            return false;
                    });
                });

                SortedList<Airline> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(airlineTableView.comparatorProperty());

                airlineTableView.setItems(sortedData);

            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se encontró la aerolínea");
            }

        }

    }


