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
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/AdminAln.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Airlines");
            stage.show();

            close(event);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void adminAirports(ActionEvent event) {
        // Open the User window
        try {

            close(event);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/AdminAirports.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Airports");
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @FXML
    void adminWorkersbtnClicked(ActionEvent event) {
        // Open the User window
        try {
            close(event);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/admin/AdminWorkers.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Worker");
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backButtonClicked(ActionEvent event) {

        close(event);

        // Open the User window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(SignUpController.class.getResourceAsStream("/um/edu/uy/ui/user/LogIn.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Log In");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

