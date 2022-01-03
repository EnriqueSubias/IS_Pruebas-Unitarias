package test.publicadministration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import src.exceptions.*;
import src.publicadministration.QuotePeriod;

public class QuotePeriodTest {

    public QuotePeriod testObjetivo;

    @BeforeAll
    public void setUp() {
        // Calendar cal = Calendar.getInstance();
        // cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
        // Date date = cal.getTime();
        Date date = Date.from((new java.util.Date()).toInstant().plusSeconds(1576800000));
        testObjetivo = new QuotePeriod(date, 10);
        // assertDoesNotThrow(new QuotePeriod(date, 10));
    }

    @Test
    @DisplayName("Comprobación de que la fecha se ha modificado")
    public void modifiedDate() throws NullPointerException, SameDateException, NullValDateException {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
        Date date = cal.getTime();
        testObjetivo = new QuotePeriod(date, 10);
        cal.set(2018, Calendar.JANUARY, 12);
        Date futureDate = cal.getTime();
        testObjetivo.setInitDay(futureDate);

        assertNotNull(testObjetivo);
        assertEquals(futureDate, testObjetivo.getInitDay());
        assertThrows(SameDateException.class, () -> testObjetivo.setInitDay(futureDate));
    }

    @Test
    @DisplayName("Comprobación de que la fecha se ha modificado")
    public void modifiedDays() throws NullPointerException, NegativeDaysPeriod, SameDaysException {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
        Date date = cal.getTime();
        testObjetivo = new QuotePeriod(date, 10);
        // cal.set(2018, Calendar.JANUARY, 12);
        // Date futureDate = cal.getTime();
        // testObjetivo.setInitDay(futureDate);
        
        assertNotNull(testObjetivo);
        assertEquals(10, testObjetivo.getNDays());
        assertThrows(NegativeDaysPeriod.class, () -> testObjetivo.setNDay(-10));
        assertThrows(SameDaysException.class, () -> testObjetivo.setNDay(10));
    }

}
