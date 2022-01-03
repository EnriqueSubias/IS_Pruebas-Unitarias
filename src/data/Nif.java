package src.data;

import src.exceptions.*;

/**
 * Essential data classes
 */

public final class Nif {
    // The tax identification number in the Spanish state.
    private final String nif;

    public Nif(String code) throws NullNifException, NotValidNifException {
        if (code == null || "".equals(code)) {
            throw new NullNifException();
        }
        if (code.length() != 9 || Character.isLetter(code.length() - 1)) {
            throw new NotValidNifException();
        } else {
            boolean correctFormat = true;
            for (int i = 0; i < code.length() - 1; i += 1) {
                if (!Character.isDigit(code.charAt(i))) {
                    i = code.length();
                    correctFormat = false;
                }
            }
            if (correctFormat) {
                this.nif = code;
            } else {
                throw new IllegalArgumentException("Invalid NIF format");
            }
        }
    }

    public String getNif() throws NotValidNifException {
        if (this.nif != null) {
            return this.nif;
        } else {
            // return null;
            throw new NotValidNifException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Nif niff = (Nif) o;
        return nif.equals(niff.nif);
    }

    @Override
    public int hashCode() {
        return this.nif.hashCode();
    }

    @Override
    public String toString() {
        return "Nif{" + "nif ciudadano='" + nif + '\'' + '}';
    }
}
