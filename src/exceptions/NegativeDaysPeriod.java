package src.exceptions;

public class NegativeDaysPeriod extends Exception {
    public NegativeDaysPeriod() {
        super("El periodo no puede ser negativo");
    }

}
