package src.exceptions;

public class NullFileException extends Exception {
    public NullFileException() {
        super("File is null");
    }
}
