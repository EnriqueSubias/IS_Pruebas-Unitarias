package test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import src.services.LaboralLifeDoc;
import src.data.Nif;
import src.exceptions.*;
import src.publicadministration.QuotePeriod;
import src.publicadministration.QuotePeriodsColl;

public class LaboralLifeDocTest {

    LaboralLifeDoc testObjetivo;
    Nif nif;
    QuotePeriod quotePeriodo;

    @BeforeEach
    public void setUp() throws AlreadyAddedException {
        QuotePeriodsColl QuotesColl = new QuotePeriodsColl();
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
        Date date = cal.getTime();
        quotePeriodo = new QuotePeriod(date, 10);
        QuotesColl.addQuotePeriod(quotePeriodo);
    }

    @Test
    public void testLaboralLifeDoc() throws NullNifException, NotValidNifException {
        nif = new Nif("12345678A");
        testObjetivo = new LaboralLifeDoc(null, null);
        // assertThrows(NoSuchPeriodException.class, () -> testObjetivo.getQuotePds());
        // assertThrows(NifNotRegisteredException.class, () -> testObjetivo.getNif());
    }
}
