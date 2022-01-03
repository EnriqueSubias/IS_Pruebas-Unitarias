package src.exceptions;

public class NotValidPasswordException extends Exception {
    public NotValidPasswordException() {
        super("Wrong Electronic certificate password");
    }
}
