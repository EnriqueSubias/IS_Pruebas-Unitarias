package src.services;

import src.data.AccredNumb;
import src.data.Nif;
import src.exceptions.NifNotRegisteredException;
import src.exceptions.NotValidAccredNumberException;
import src.exceptions.NullAccredNumberException;
import src.exceptions.NullNifException;

public class MemberAccreditationDoc extends PDFDocument { // Represents the member accreditation document

    private Nif nif;
    private AccredNumb nAff;

    public MemberAccreditationDoc(Nif nif, AccredNumb nAff) // Date creatDate, DocPath path, File file,
            throws NullNifException, NullAccredNumberException { // Initializes
        // attributes
        // super(creatDate, path, file);
        if (nif == null) {
            throw new NullNifException();
        }
        if (nAff == null) {
            throw new NullAccredNumberException();
        }
        this.nif = nif;
        this.nAff = nAff;
    }

    // the getters
    public Nif getNif() throws NifNotRegisteredException {
        if (this.nif == null) {
            throw new NifNotRegisteredException();
        }
        return this.nif;
    }

    public AccredNumb getAccredNumb() throws NotValidAccredNumberException {
        if (this.nAff == null) {
            throw new NotValidAccredNumberException();
        }
        return this.nAff;
    }
}
