package src.publicadministration;

import java.util.Date;

import src.exceptions.NegativeDaysPeriod;
import src.exceptions.NullValDateException;
import src.exceptions.SameDateException;
import src.exceptions.SameDaysException;

public class QuotePeriod { // Represents a quote period as a registered worker

    private Date initDay;
    private int numDays;

    public QuotePeriod(Date date, int ndays) { // Initializes attributes
        this.initDay = date;
        this.numDays = ndays;
    }

    public void setInitDay(Date date) throws SameDateException, NullValDateException {
        if (date == null) {
            throw new NullValDateException();
        } else {
            if (!this.initDay.equals(date)) {
                this.initDay = date;
            } else {
                throw new SameDateException();
            }
        }
    }

    public void setNDay(int ndays) throws NegativeDaysPeriod, SameDaysException {
        if (ndays < 0) {
            throw new NegativeDaysPeriod();
        } else {
            if (this.numDays != ndays) {
                this.numDays = ndays;
            } else {
                throw new SameDaysException();
            }
        }
    }

    // the getters
    public Date getInitDay(){
        return this.initDay;
    }

    public int getNDays(){
        return this.numDays;
    }

    @Override
    public String toString() { // converts to String
        return "Data: " + this.initDay.toString() + " Num Days: " + this.numDays;
        // return "initDay{" + "fecha inicial='" + this.initDay.toString() + '\'' + '}'
        // + "numDays{" + "numero de dias='" + this.numDays + '\'' + '}'
    }

}
