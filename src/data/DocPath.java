package src.data;

import src.exceptions.NullPathException;

/**
 * Essential data classes
 */
public final class DocPath {
    // Document Path Class.
    private String path;

    public DocPath() {
        this.path = null;
    }

    // private final AccredNumb numberSS;
    public void addDocPath(String path) throws NullPathException {
        if (path == null) {
            throw new NullPathException();
        }
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public int hashCode() {
        return (this.path.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DocPath docPathh = (DocPath) o;
        return this.path.equals(docPathh.getPath());
    }

    @Override
    public String toString() {
        return "DocPath{" + "Doc Path='" + '\'' + '}';
    }
}
