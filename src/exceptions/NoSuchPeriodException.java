package src.exceptions;

public class NoSuchPeriodException extends Exception {
    public NoSuchPeriodException() {
        super("No esta el periodo buscado");
    }
}
