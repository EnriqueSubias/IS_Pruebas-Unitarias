package src.publicadministration;

import java.util.Date;

public class QuotePeriod { // Represents a quote period as a registered worker

    private Date initDay;
    private int numDays;

    public QuotePeriod(Date date, int ndays) { // Initializes attributes
        this.initDay = date;
        this.numDays = ndays;
    }

    public void setInitDay(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date invalid");
        } else {
            this.initDay = date;
        }
    }

    public void setNDay(int ndays) {
        if (ndays < 0) {
            throw new IllegalArgumentException("Date invalid");
        } else {
            this.numDays = ndays;
        }
    }

    // the getters
    public Date getInitDay() {
        return this.initDay;
    }

    public int getNDays() {
        return this.numDays;
    }

    @Override
    public String toString() { // converts to String
        return "Data: " + this.initDay.toString() + " Num Days: " + this.numDays;
        // return "initDay{" + "fecha inicial='" + this.initDay.toString() + '\'' + '}'
        // + "numDays{" + "numero de dias='" + this.numDays + '\'' + '}';
    }

}
