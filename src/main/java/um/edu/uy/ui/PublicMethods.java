package um.edu.uy.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import um.edu.uy.Main;
import um.edu.uy.ui.user.LController;

import java.io.IOException;


public class PublicMethods {
    static final String CSS_PATH_ALERT = "/um/edu/uy/ui/user/stylebases/alertdialog.css";

    static final String CSS_BUTTONS_PATH = "/um/edu/uy/ui/user/stylebases/buttons.css";
    public static void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);

        String css = PublicMethods.class.getResource(CSS_PATH_ALERT).toExternalForm();
        alert.getDialogPane().getStylesheets().add(css);

        ImageView icon = new ImageView(new Image("/um/edu/uy/ui/user/stylebases/alert.png"));
        alert.setGraphic(icon);
        /*alert.getGraphic().setScaleX(0.8);
        alert.getGraphic().setScaleY(0.8);*/

        alert.showAndWait();
    }
    public static void changeWindow(ActionEvent event, String newWindow, String title) {
        try {
            close(event);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(LController.class.getResourceAsStream(newWindow));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);

            String css = PublicMethods.class.getResource(CSS_BUTTONS_PATH).toExternalForm();
            scene.getStylesheets().add(css);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("ERROR!", "Error cambiando de ventana.");
        }
    }

    public static void logOut(ActionEvent event) {
        close(event);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(LController.class.getResourceAsStream("/um/edu/uy/ui/user/LogIn.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()) .getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Log In");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("ERROR!", "Error cerrando sesión.");
        }
    }

}
