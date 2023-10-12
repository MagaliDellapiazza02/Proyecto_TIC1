package um.edu.uy.ui.admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import um.edu.uy.Main;
import um.edu.uy.ui.user.LogInController;
import um.edu.uy.ui.user.UserController;

import java.io.IOException;

public class AdminAirlinesController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Label labTitle;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void backButtonClicked(ActionEvent event) {

        close(event);

        // Open the User window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/um/edu/uy/ui/user/admin/UserAdminMenu.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("User Admin Menu");

            // Configurar el controlador de la ventana de inicio de sesión (si es necesario)
            UserAdminWindowController controller = loader.getController();
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
