package src.exceptions;

public class IncorrectPinException extends Exception {
    public IncorrectPinException() {
        super("PIN does not correspond");
    }
}
