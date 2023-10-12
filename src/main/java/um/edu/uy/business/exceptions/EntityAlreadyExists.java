package um.edu.uy.business.exceptions;

public class EntityAlreadyExists extends Exception {

    public EntityAlreadyExists(String message) {
        super(message);
    }

    public EntityAlreadyExists() {
    }
}
