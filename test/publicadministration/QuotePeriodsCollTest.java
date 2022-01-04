package test.publicadministration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import src.exceptions.*;
import src.publicadministration.QuotePeriod;
import src.publicadministration.QuotePeriodsColl;

public class QuotePeriodsCollTest {
    QuotePeriodsColl testObjetivo;

    @BeforeAll
    public void setUp() {
        testObjetivo = new QuotePeriodsColl();
    }

    @Test
    @DisplayName("Comprobación de que los peridos se han modificado correctamente")
    public void modifiedPeriods()
            throws SameDateException, NullValDateException, AlreadyAddedException,
            NoSuchPeriodException {
        testObjetivo = new QuotePeriodsColl();
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
        Date date = cal.getTime();
        QuotePeriod quotePeriodo = new QuotePeriod(date, 10);
        cal.set(2018, Calendar.JANUARY, 25); // Year, month and day of month
        Date newDate = cal.getTime();
        assertNotNull(testObjetivo);
        assertEquals(true, testObjetivo.addQuotePeriod(quotePeriodo));
        assertEquals(quotePeriodo, testObjetivo.getSpecificPeriod(date));
        assertThrows(AlreadyAddedException.class, () -> testObjetivo.addQuotePeriod(quotePeriodo));
        assertThrows(NullValDateException.class, () -> testObjetivo.getSpecificPeriod(null));
        assertThrows(NoSuchPeriodException.class, () -> testObjetivo.getSpecificPeriod(newDate));
    }

}