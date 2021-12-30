package src.services;

import src.publicadministration.QuotePeriodsColl;
import src.data.Nif;

public class LaboralLifeDoc extends PDFDocument { // Represents the laboral life

    private Nif nif;
    private QuotePeriodsColl quotePds;

    public LaboralLifeDoc(Nif nif, QuotePeriodsColl qtP) { // Initializes attributes
        this.nif = nif;
        this.quotePds = qtP;
    }

    // the getters
    public Nif getNif() {
        return this.nif;
    }

    public QuotePeriodsColl getQuotePds() {
        return this.quotePds;
    }
}
