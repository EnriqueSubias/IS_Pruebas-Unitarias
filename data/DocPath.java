package data;
/**
 * Essential data classes
 */
final public class DocPath {
    // Document Path Class.
    private final String path;
    private final AccredNumb numberSS;
    public DocPath(String path, AccredNumb numberSS) throws NullPointerException{
        if(path == null || numberSS == null){
            throw new NullPointerException("path or numberSS entered as null Parameter");
        }
        this.numberSS=numberSS;
        this.path = path;
    }
    public String getPath () {
        return path;
    }
    public String getNumberSS(){
        return numberSS.getAccredNumber();
    }
    @Override
    public int hashCode () { return (path+numberSS.toString()).hashCode();}
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false; DocPath docPath = (DocPath) o;
        return (path+numberSS.toString()).equals(docPath.path+docPath.numberSS.toString());
    }
    @Override
    public String toString () {
        return "DocPath{" + "Doc Path='" + path + "Number SS:" + numberSS + '\'' + '}';
    }
}