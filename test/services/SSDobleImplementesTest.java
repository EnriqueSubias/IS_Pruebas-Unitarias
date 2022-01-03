
package test.services;

import java.util.*;

import org.junit.jupiter.api.DisplayName;

import src.data.*;
import src.exceptions.*;
import src.services.*;
import src.publicadministration.*;

public class SSDobleImplementesTest implements SS {

    Map<Nif, AccredNumb> databaseAccred;
    Map<Nif, QuotePeriodsColl> databasePeriods;

    public SSDobleImplementesTest() throws AlreadyAddedException, NotValidAccredNumberException {
        databaseAccred = new HashMap<>();
        databasePeriods = new HashMap<>();
        addsToDatabase();
    }

    @Override
    @DisplayName("Comprobación de que está afiliado en la SS")
    public MemberAccreditationDoc getMembAccred(Nif nif) throws NotAffiliatedException, ConnectException {
        // TODO Auto-generated method stub

        if (databaseAccred.containsKey(nif)) {
            MemberAccreditationDoc doc = new MemberAccreditationDoc(nif, databaseAccred.get(nif));
            return doc;
        } else {
            throw new NotAffiliatedException();
        }
    }

    @Override
    @DisplayName("Comprobación de la vida laboral")
    public LaboralLifeDoc getLaboralLife(Nif nif) throws NotAffiliatedException, ConnectException {
        // TODO Auto-generated method stub
        if (databasePeriods.containsKey(nif) && databaseAccred.containsKey(nif)) {
            LaboralLifeDoc laboralLife = new LaboralLifeDoc(nif, databasePeriods.get(nif));
            return laboralLife;
        } else {
            throw new NotAffiliatedException();
        }
    }

    public void addsToDatabase() throws AlreadyAddedException, NotValidAccredNumberException {
        Nif user1 = new Nif("12345678A");
        Nif user2 = new Nif("12345678B");
        Nif user3 = new Nif("12345678C");
        // Nif user4 = new Nif("12345678D");
        AccredNumb accUser1 = new AccredNumb("12345678901");
        AccredNumb accUser2 = new AccredNumb("12345678902");
        AccredNumb accUser3 = new AccredNumb("12345678903");
        // AccredNumb accUser4 = new AccredNumb("12345678904");
        databaseAccred.put(user1, accUser1);
        databaseAccred.put(user2, accUser2);
        databaseAccred.put(user3, accUser3);
        // databaseAccred.put(user4, accUser4);
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
        Date date1 = cal.getTime();
        cal.set(2005, Calendar.JANUARY, 7); // Year, month and day of month
        Date date2 = cal.getTime();
        cal.set(2010, Calendar.JANUARY, 26); // Year, month and day of month
        Date date3 = cal.getTime();
        cal.set(2021, Calendar.JANUARY, 1); // Year, month and day of month
        Date date4 = cal.getTime();
        QuotePeriod period1 = new QuotePeriod(date1, 400);
        QuotePeriod period2 = new QuotePeriod(date2, 250);
        QuotePeriod period3 = new QuotePeriod(date3, 100);
        QuotePeriod period4 = new QuotePeriod(date4, 60);
        QuotePeriodsColl totalPeriods1 = new QuotePeriodsColl();
        totalPeriods1.addQuotePeriod(period2);
        totalPeriods1.addQuotePeriod(period4);
        totalPeriods1.addQuotePeriod(period1);
        QuotePeriodsColl totalPeriods2 = new QuotePeriodsColl();
        totalPeriods2.addQuotePeriod(period1);
        totalPeriods2.addQuotePeriod(period4);
        QuotePeriodsColl totalPeriods3 = new QuotePeriodsColl();
        totalPeriods3.addQuotePeriod(period4);
        databasePeriods.put(user1, totalPeriods1);
        databasePeriods.put(user2, totalPeriods2);
        databasePeriods.put(user3, totalPeriods3);
    }

}