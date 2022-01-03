package src.services;

import src.data.AccredNumb;
import src.data.Nif;

public class MemberAccreditationDoc extends PDFDocument { // Represents the member accreditation document

    private Nif nif;
    private AccredNumb numAffil;

    public MemberAccreditationDoc(Nif nif, AccredNumb nAff) { // Initializes attributes
        this.nif = nif;
        this.numAffil = nAff;
        // super(creatDate, path, file);
        // TODO intentar implementear el deep copy
    }

    // the getters
    public Nif getNif() {
        return this.nif;
    }

    public AccredNumb getAccredNumb() {
        return this.numAffil;
    }
}
