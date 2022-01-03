package src.exceptions;

public class NullPathException extends Exception {
    public NullPathException() {
        super("Entered Path is null");
    }
}
