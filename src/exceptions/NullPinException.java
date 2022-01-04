package src.exceptions;

public class NullPinException extends Exception {
    public NullPinException() {
        super("Entered PIN is null");
    }
}
