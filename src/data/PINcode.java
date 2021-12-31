package src.data;

/**
 * Essential data classes
 */

public final class PINcode {
    // PinCode Class for the "Cl@ve Permanente".
    private final String pin;

    public PINcode(String code) throws IllegalArgumentException, NullPointerException {
        if (code == null || "".equals(code)) {
            throw new NullPointerException("No PIN entered as parameter");
        }
        if (code.length() != 3) {
            throw new IllegalArgumentException("Invalid PIN length");
        } else {
            boolean correctFormat = true;
            for (int i = 0; i < code.length(); i += 1) {
                if (!Character.isDigit(code.charAt(i))) {
                    correctFormat = false;
                    break;
                }
            }
            if (correctFormat) {
                this.pin = code;
            } else {
                throw new IllegalArgumentException("Invalid PIN format");
            }
            // Se usaba esto porque code era int, ahora es String
            /*
             * if (code > 999 || code < 0) {
             * Wrong entered data.
             * throw new IllegalArgumentException("Ilegal Number (Should be positive and
             * lower than 999 ");
             * }
             */
        }
        // this.pin = code;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PINcode pinCode = (PINcode) o;
        return pin == pinCode.getPin();
    }

    @Override
    public String toString() {
        return "PINcode{" + "pin ciudadano='" + pin + '\'' + '}';
    }
}
