package um.edu.uy.ui.admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import um.edu.uy.Main;
import um.edu.uy.ui.user.LogInController;
import um.edu.uy.ui.passenger.SignUpController;
import um.edu.uy.ui.user.UserController;

import java.io.IOException;

@ComponentScan
@Controller
public class UserAdminWindowController {

    @FXML
    private Button btnAln;

    @FXML
    private Button btnAirports;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnWorkers;

    /*
    @Bean
    public UserAdminWindowController userAdminWindowController() {
        return new UserAdminWindowController();
    }
*/
    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void adminAln(ActionEvent event) {

        // Open the User window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/um/edu/uy/ui/user/admin/AdminAln.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Admin Airlines");

            // Configurar el controlador de la ventana de inicio de sesión (si es necesario)
            AdminAirlinesController controller = loader.getController();
            // Puedes pasar datos al controlador de inicio de sesión si es necesario

            Scene scene = new Scene(root);
            loginStage.setScene(scene);

            // Mostrar la ventana de inicio de sesión
            loginStage.show();

            close(event);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void adminAirports(ActionEvent event) {
        // Open the User window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/um/edu/uy/ui/user/admin/AdminAirports.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Admin Airports");

            // Configurar el controlador de la ventana (si es necesario)
            AdminAirportsController controller = loader.getController();
            // Puedes pasar datos al controlador si es necesario

            Scene scene = new Scene(root);
            loginStage.setScene(scene);

            // Mostrar la ventana de inicio de sesión
            loginStage.show();

            close(event);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @FXML
    void adminWorkersbtnClicked(ActionEvent event) {


        // Open the User window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/um/edu/uy/ui/user/admin/AdminWorkers.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Admin Worker");

            // Configurar el controlador de la ventana (si es necesario)
            AdminWorkersController controller = loader.getController();
            // Puedes pasar datos al controlador si es necesario

            Scene scene = new Scene(root);
            loginStage.setScene(scene);

            // Mostrar la ventana de inicio de sesión
            loginStage.show();

            close(event);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backButtonClicked(ActionEvent event) {

        close(event);

        // Open the User window
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

