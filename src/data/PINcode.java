package src.data;

import src.exceptions.NotValidPINException;
import src.exceptions.NullPinException;

/**
 * Essential data classes
 */

public final class PINcode {
    // PinCode Class for the "Cl@ve Permanente".
    private final String pin;

    public PINcode(String code) throws NullPinException, NotValidPINException {
        if (code == null || "".equals(code)) {
            throw new NullPinException();
        }
        if (code.length() != 3) {
            throw new NotValidPINException();
        }
        for (int i = 0; i < code.length(); i += 1) {
            if (!Character.isDigit(code.charAt(i))) {
                throw new NotValidPINException();
            }
        }
        this.pin = code;

        // Se usaba esto porque code era int, ahora es String
        /*
         * if (code > 999 || code < 0) {
         * Wrong entered data.
         * throw new IllegalArgumentException("Ilegal Number (Should be positive and
         * lower than 999 ");
         * }
         */

        // this.pin = code;
    }

    public String getPin() throws NullPinException {
        if (this.pin == null) {
            throw new NullPinException();
        }
        return this.pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PINcode pinCode = (PINcode) o;
        try {
            return pin == pinCode.getPin();
        } catch (NullPinException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "PINcode{" + "pin ciudadano='" + pin + '\'' + '}';
    }
}
