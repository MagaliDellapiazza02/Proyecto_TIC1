package um.edu.uy.ui.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import um.edu.uy.services.UserMgr;
import um.edu.uy.ui.PublicMethods;

@Controller
public class adminGeneralController {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogIn;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtUser;

    @Autowired
    private UserMgr userMgr;

    @FXML
    void loginButtonClicked(ActionEvent event) {
        // Obtener los valores de los campos de entrada
        String username = txtUser.getText();
        String password = txtPass.getText();

        // Verificar si los campos de usuario y contraseña son nulos o están vacíos
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // Mostrar una alerta de campos vacíos
            PublicMethods.showAlert("Campos vacíos", "Por favor, complete todos los campos.");
            return; // No hacer nada más si los campos son nulos o vacíos
        }
        if (!userMgr.checkAdminLogIn(username, password)) {
            PublicMethods.showAlert("Administrador invalido", "Contraseña incorrecta o no es administrador");
        } else {
            PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/admin/UserAdminMenu.fxml", "Admin Menu");
        }
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        PublicMethods.changeWindow(event, "/um/edu/uy/ui/user/LogIn.fxml", "Log In");
    }
}
