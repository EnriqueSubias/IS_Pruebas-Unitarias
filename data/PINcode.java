package data;
/**
 * Essential data classes
 */
final public class PINcode {
    // PinCode Class for the "Cl@ve Permanente".
    private final int pin;
    public PINcode(int code) throws IllegalArgumentException, NullPointerException {
        if (code>999 || code<0){
            //Wrong entered data.
            throw new IllegalArgumentException("Ilegal Number (Should be positive and lower than 999 ");
        }
        this.pin = code;
    }
    public Integer getPin () {
        return pin;
    }
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; PINcode pinCode = (PINcode) o;
        return pin == pinCode.pin;
    }
    @Override
    public String toString () {
        return "PINcode{" + "pin ciudadano='" + pin + '\'' + '}';
    } }