package um.edu.uy.business.exceptions;

public class UserAlreadyExists extends Exception {

    public UserAlreadyExists(String message) {
        super(message);
    }

    public UserAlreadyExists() {
    }
}
