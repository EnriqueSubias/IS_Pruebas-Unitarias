package src.exceptions;

public class NullPasswordException extends Exception {
    public NullPasswordException() {
        super("Entered Password is null");
    }
}
