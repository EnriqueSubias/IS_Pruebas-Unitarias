package src.exceptions;

public class NotValidCredException extends Exception {
    public NotValidCredException() {
        super("Credentials are incorrect");
    }
}
