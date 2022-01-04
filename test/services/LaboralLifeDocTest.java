package test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
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
    QuotePeriodsColl quotesColl;

    @Test
    @DisplayName("Laboral Life Doc Test")
    public void testLaboralLifeDoc()
            throws NullNifException, NotValidNifException, NoSuchPeriodException, NifNotRegisteredException,
            AlreadyAddedException {
        quotesColl = new QuotePeriodsColl();
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
        Date date = cal.getTime();
        quotePeriodo = new QuotePeriod(date, 10);
        quotesColl.addQuotePeriod(quotePeriodo);
        nif = new Nif("12345678A");
        assertThrows(NifNotRegisteredException.class, () -> new LaboralLifeDoc(null, null));
        assertThrows(NoSuchPeriodException.class, () -> new LaboralLifeDoc(nif, null));
        testObjetivo = new LaboralLifeDoc(nif, quotesColl);
        assertNotNull(testObjetivo);
        assertEquals(nif, testObjetivo.getNif());
        assertEquals(quotesColl, testObjetivo.getQuotePds());
    }
}
