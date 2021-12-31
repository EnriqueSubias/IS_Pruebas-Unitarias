package src.exceptions;

public class AnyMobileRegisteredException extends Exception {
    public AnyMobileRegisteredException() {
        super("There is no mobile phone registered");
    }
}
