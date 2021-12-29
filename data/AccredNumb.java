package data;
/**
 * Essential data classes
 */
final public class AccredNumb {
    // Number of affiliation to the Social Security.
    private final String ss_number;//11 digits length
    public AccredNumb(String ss_number) throws IllegalArgumentException{
        if(ss_number.length() != 11){
            // Checks if the number has length 11 if not throws an exception
            throw new IllegalArgumentException("Incorrect format");
        }
        this.ss_number= ss_number;
    }
    public String getAccredNumber () {
        return ss_number;
    }
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; AccredNumb accredNumb = (AccredNumb) o;
        return ss_number.equals(accredNumb.ss_number);
    }
    @Override
    public int hashCode () { return ss_number.hashCode(); }
    @Override
    public String toString () {
        return "AccredNumb{" + "Numero de SS del ciudadano='" + ss_number + '\'' + '}';
    } }