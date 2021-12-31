package src.exceptions;

public class ConnectException extends Exception {
    public ConnectException() {
        super("The connection hasn't been stablished");
    }
}
