package src.exceptions;

public class AlreadyAddedException extends Exception{
    public AlreadyAddedException() {
        super("Quote Period has already been added before");
    }
}
