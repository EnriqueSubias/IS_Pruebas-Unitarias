package src.exceptions;

public class NifNotRegisteredException extends Exception {
    public NifNotRegisteredException() {
        super("NIF coundn't be registered in Cl@ve PIN");
    }
}
