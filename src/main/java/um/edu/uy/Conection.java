package um.edu.uy;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {
        public static final String URL = "jdbc:mysql://34.41.26.182:3306/easeflightdb";
        public static final String USER = "root";
        public static final String CLAVE = "admin";

        public Connection getConexion() {
            Connection con = null;
            try {
                Class.forName("com.mysq l.cj.jdbc.Driver");
                con = (Connection) DriverManager.getConnection(URL, USER, CLAVE);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            return con;
        }
}
