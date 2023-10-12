package um.edu.uy;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import um.edu.uy.ui.JavaFXApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.Connection;

@SpringBootApplication
public class Main {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Main.context = SpringApplication.run(Main.class);
        //Conection c = new Conection();
        //c.getConexion();
        Application.launch(JavaFXApplication.class, args);
    }



    public static ConfigurableApplicationContext getContext() {
        return context;
    }

}

