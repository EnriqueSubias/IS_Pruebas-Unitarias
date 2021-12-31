package src.data;

import java.nio.file.Path;

/**
 * Essential data classes
 */
public final class DocPath {
    // Document Path Class.
    private String path;

    public DocPath(){
        this.path = null;
    }

    // private final AccredNumb numberSS;
    public void addDocPath(String path, AccredNumb numberSS) throws NullPointerException {
        if (path == null) {
            throw new NullPointerException("path or numberSS entered as null Parameter");
        }
        // this.numberSS=numberSS;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public int hashCode () { return (this.path.hashCode());}

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DocPath docPathh = (DocPath) o;
        /*if (this.path.compareTo(DocPathh.getPath()) == 0){
            return true;
        }else{
            return false;
        }*/
        return this.path.equals(docPathh.getPath());
    }

    @Override
    public String toString() {
        return "DocPath{" + "Doc Path='" + '\'' + '}';
    }
}
