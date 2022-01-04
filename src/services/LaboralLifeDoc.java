package src.services;

import src.publicadministration.QuotePeriodsColl;
import src.data.Nif;
import src.exceptions.*;

public class LaboralLifeDoc extends PDFDocument { // Represents the laboral life

    private Nif nif;
    private QuotePeriodsColl quotePds;

    public LaboralLifeDoc(Nif nif, QuotePeriodsColl qtP) { // Initializes attributes
        this.nif = nif;
        this.quotePds = qtP;
    }

    // the getters
    public Nif getNif() throws NifNotRegisteredException {
        if (this.nif == null) {
            throw new NifNotRegisteredException();
        }
        return this.nif;
    }

    public QuotePeriodsColl getQuotePds() throws NoSuchPeriodException {
        if (this.quotePds == null) {
            throw new NoSuchPeriodException();
        }
        return this.quotePds;
    }
}
