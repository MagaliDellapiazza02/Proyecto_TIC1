package um.edu.uy.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;

@Component
public class DeleteWorkerController {

    @Autowired
    private UserMgr userMgr;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLogOut;

    @FXML
    private TextField txtDocument;

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/AdminWorkers.fxml", "Administrar Trabajadores");
    }

    @FXML
    void deleteButtonClicked(ActionEvent event) {
        try{
            if (txtDocument.getText() == null || txtDocument.getText().equals("")) {
                PublicMethods.showAlert("Dato faltante!", "No se ingresó el documento para borrar el trabajador");

            }
        }catch (Exception e) {
            PublicMethods.showAlert("ERROR", "Ingrese correctamente los datos");
        }

        long doc = Long.valueOf(txtDocument.getText());
        if (userMgr.deleteUser(doc)) {
            PublicMethods.showAlert("Borrado!", "Se borro exitosamente!");
        } else {
            PublicMethods.showAlert("ERROR!", "No se encontró ese trabajador");
        }

    }

    @FXML
    void logOutButtonClicked(ActionEvent event) {
        PublicMethods.logOut(event);
    }
}
