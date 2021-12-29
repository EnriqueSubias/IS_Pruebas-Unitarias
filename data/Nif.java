package data;
/**
 * Essential data classes
 */
final public class Nif {
    // The tax identification number in the Spanish state.
    private final String nif;
    public Nif (String nif) throws NullPointerException, IllegalArgumentException{
        if(nif==null){
            throw new NullPointerException("No Nif entered as parameter");
        }
        if(nif.length()!=9){
            throw new IllegalArgumentException("Incorrect NIF format");
        }else{
            boolean a=false;
            for (int i=0;i<nif.length()-1;i+=1){
                Character.isDigit(nif.charAt(i));
            }
        }
        this.nif = nif;
    }
    public String getNif () {
        return nif;
    }
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; Nif niff = (Nif) o;
        return nif.equals(niff.nif);
    }
    @Override
    public int hashCode () { return nif.hashCode(); }
    @Override
    public String toString () {
        return "Nif{" + "nif ciudadano='" + nif + '\'' + '}';
    } }