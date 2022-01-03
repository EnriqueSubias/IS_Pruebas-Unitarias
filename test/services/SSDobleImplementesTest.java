
package test.services;

import java.util.*;

import src.data.*;
import src.exceptions.*;
import src.services.*;
import src.publicadministration.*;

public class SSDobleImplementesTest implements SS {

    Map<Nif, AccredNumb> databaseAccred;
    Map<Nif, QuotePeriod> databasePeriods;

    public SSDobleImplementesTest() {
        databaseAccred = new HashMap<>();
        databasePeriods = new HashMap<>();

    }

    @Override
    public MemberAccreditationDoc getMembAccred(Nif nif) throws NotAffiliatedException, ConnectException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LaboralLifeDoc getLaboralLife(Nif nif) throws NotAffiliatedException, ConnectException {
        // TODO Auto-generated method stub
        return null;
    }

    public void addsToDatabase() {
        Nif user1 = new Nif("12345678A");
        Nif user2 = new Nif("12345678B");
        Nif user3 = new Nif("12345678C");
        Nif user4 = new Nif("12345678D");
        AccredNumb accUser1 = new AccredNumb("12345678901");
        AccredNumb accUser2 = new AccredNumb("12345678902");
        AccredNumb accUser3 = new AccredNumb("12345678903");
        AccredNumb accUser4 = new AccredNumb("12345678904");
        databaseAccred.put(user1, accUser1);
        databaseAccred.put(user2, accUser2);
        databaseAccred.put(user3, accUser3);
        databaseAccred.put(user4, accUser4);
        
    }

}