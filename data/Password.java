package data;
/**
 * Essential data classes
 */
final public class Password {
    // The tax identification number in the Spanish state.
    private final String password;
    public Password(String password) throws NullPointerException{
        if(password == null){
            throw new NullPointerException("password is null");
        }
        this.password = password;
    }
    public String getPassword () {
        return password;
    }
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; Password passwordClass = (Password) o;
        return password.equals(passwordClass.password);
    }
    @Override
    public int hashCode () { return password.hashCode(); }
    @Override
    public String toString () {
        return "Password{" + "password ciudadano='" + password + '\'' + '}';
    } }