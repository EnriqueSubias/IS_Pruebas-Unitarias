package src.exceptions;

public class NotAffiliatedException extends Exception {
    public NotAffiliatedException() {
        super("Ciudadano no afiliado a la SS");
    }
}
