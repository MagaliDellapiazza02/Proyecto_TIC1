package um.edu.uy.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import um.edu.uy.Main;
import um.edu.uy.ui.user.LogInControllerYaNoVa;

public class JavaFXApplication extends Application  {

    private Parent root;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);
        root = fxmlLoader.load(LogInControllerYaNoVa.class.getResourceAsStream("/um/edu/uy/ui/user/LogIn.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() {
        Main.getContext().close();
    }

}
