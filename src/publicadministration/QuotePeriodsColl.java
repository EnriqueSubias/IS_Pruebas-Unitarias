package src.publicadministration;

import java.util.*;

import src.exceptions.AlreadyAddedException;
import src.exceptions.NoSuchPeriodException;
import src.exceptions.NullValDateException;

public class QuotePeriodsColl { // Represents the total quote periods known as a registered worker

    // Its components, that is, the set of quote periods
    private HashMap<Date, QuotePeriod> cola;

    public QuotePeriodsColl() { // Initializes the object
        cola = new HashMap<>();
    }

    // the getters
    public Map<Date, QuotePeriod> getPeriods() {
        return this.cola;
    }

    public QuotePeriod getSpecificPeriod(Date e) throws NoSuchPeriodException, NullValDateException {
        QuotePeriod specific = null;
        if (e != null) {
            if (cola.containsKey(e)) {
                specific = cola.get(e);
            } else {
                throw new NoSuchPeriodException();
            }
        } else {
            throw new NullValDateException();
        }

        return specific;
    }

    public boolean addQuotePeriod(QuotePeriod qPd) throws AlreadyAddedException {
        // Adds a quote period, always respecting the sorting by date, from oldest to
        // later in time
        if (qPd == null) {
            throw new NoSuchElementException("qPd element is null");
        } else if (cola.containsKey(qPd.getInitDay())) { // Duplicated
            throw new AlreadyAddedException();
        } else {
            cola.put(qPd.getInitDay(), qPd);
            return true;
        }
    }

    @Override
    public String toString() { // Converts to String
        // StringBuilder bld = new StringBuilder();
        // for (Date i : cola.keySet()) {
        // for (HashMap.Entry<Date, QuotePeriod> entry: cola.entrySet()) {
        // bld.append("Data : " + i + "Num Days: " + cola.get(i).getNDays());
        // bld.append("Data : " + entry.getKey() + "Num Days: " + entry.getValue();
        // }
        return cola.toString();
    }
}
