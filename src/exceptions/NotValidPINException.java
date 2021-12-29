package src.exceptions;

public class NotValidPINException extends Exception {
    public NotValidPINException() {
        super("PIN does not correspond with the one emitted by the system, or has been expired");
    }
}
