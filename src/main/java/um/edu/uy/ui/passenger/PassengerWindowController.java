package um.edu.uy.ui.passenger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import um.edu.uy.ui.user.LogInController;

public class PassengerWindowController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button rastrearEquipajeButton;

    @FXML
    private Button verVuelosButton;

    @FXML
    private Button logOutButton;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void rastrearEquipajeButtonClicked(ActionEvent event) {
        // Manejo del evento para el botón "Rastrear equipaje"
        // Puedes agregar aquí la lógica para rastrear el equipaje
    }

    @FXML
    private void verVuelosButtonClicked(ActionEvent event) {
        // Manejo del evento para el botón "Ver vuelos"
        // Puedes agregar aquí la lógica para ver los vuelos
    }

    @FXML
    private void logOutButtonClicked(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        // Abrir la ventana de inicio de sesión (puede ser "LogIn.fxml")
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/um/edu/uy/ui/user/LogIn.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Log In");

            // Configurar el controlador de la ventana de inicio de sesión (si es necesario)
            LogInController loginController = loader.getController();
            // Puedes pasar datos al controlador de inicio de sesión si es necesario

            Scene scene = new Scene(root);
            loginStage.setScene(scene);

            // Mostrar la ventana de inicio de sesión
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}