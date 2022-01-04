package src.exceptions;

public class SameDaysException extends Exception {
    public SameDaysException() {
        super("Modificando el mismo periodo");
    }
}
