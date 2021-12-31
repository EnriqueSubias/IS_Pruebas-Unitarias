package src.exceptions;

public class IncorrectValDateException extends Exception {
    public IncorrectValDateException() {
        super("NIF and date do not correspond with the citizen");
    }
}
