package src.exceptions;

public class SameDateException extends Exception {
    public SameDateException() {
        super("Modificando la misma fecha");
    }
}
