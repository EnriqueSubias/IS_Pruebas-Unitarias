package src.data;

/**
 * Essential data classes
 */

public final class AccredNumb {
    // Number of affiliation to the Social Security.
    // hemos quitado final de ssNumber
    private String ssNumber; // 11 digits length

    //public AccredNumb() {
    //    this.ssNumber = null;
    //}

    public AccredNumb(String code) throws IllegalArgumentException {
        if (code.length() != 11) {
            // Checks if the number has length 11 if not throws an exception
            throw new IllegalArgumentException("Invalid AccredNumb length");
        } else {
            boolean correctFormat = true;
            for (int i = 0; i < code.length(); i++) {
                if (!Character.isDigit(code.charAt(i))) {
                    correctFormat = false;
                    break;
                }
            }
            if (correctFormat) {
                this.ssNumber = code;
            } else {
                throw new IllegalArgumentException("Invalid AccredNumb format");
            }
        }

    }

    public String getAccredNumber() {
        return ssNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AccredNumb accredNumb = (AccredNumb) o;
        return ssNumber.equals(accredNumb.ssNumber);
    }

    @Override
    public int hashCode() {
        return ssNumber.hashCode();
    }

    @Override
    public String toString() {
        return "AccredNumb{" + "Numero de SS del ciudadano='" + ssNumber + '\'' + '}';
    }
}
