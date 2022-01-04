package test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import src.data.AccredNumb;
import src.data.DocPath;
import src.data.Nif;
import src.exceptions.*;

import java.io.File;

import src.services.MemberAccreditationDoc;

public class MemberAcreditationDocTest {

    MemberAccreditationDoc memAcred;

    Date creatDate;
    DocPath path;
    File file;
    Nif nif;
    AccredNumb nAff;

    @BeforeEach
    public void setUp()
            throws NotValidAccredNumberException, NullAccredNumberException, NullNifException, NotValidNifException,
            NullPathException {
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
        creatDate = cal.getTime();
        path = new DocPath();
        path.addDocPath("/Users/enriquesubias/Documents/GitHub/IS_Pruebas-Unitarias/test/services/a.txt");
        file = new File("");

        nif = new Nif("12345678A");
        nAff = new AccredNumb("12345678911");

        memAcred = new MemberAccreditationDoc(nif, nAff);
    }

    @Test
    public void memberAccreditationDocConstructorTest() throws NullNifException, NotValidNifException {
        nif = new Nif("12345678A");

        assertThrows(NullNifException.class,
                () -> new MemberAccreditationDoc(null, nAff));
        assertThrows(NullAccredNumberException.class,
                () -> new MemberAccreditationDoc(nif, null));

    }

    @Test
    public void getNifTest() throws NifNotRegisteredException, NullNifException, NullAccredNumberException,
            NotValidNifException, NotValidAccredNumberException {
        nif = new Nif("12345678A");
        nAff = new AccredNumb("12345678911");
        memAcred = new MemberAccreditationDoc(nif, nAff);

        assertDoesNotThrow(() -> memAcred.getNif());
        assertEquals(nif, memAcred.getNif());
    }

    @Test
    public void getAcredNumbTest() throws NotValidAccredNumberException, NullNifException, NotValidNifException,
            NullAccredNumberException {

        nif = new Nif("12345678A");
        nAff = new AccredNumb("12345678911");
        memAcred = new MemberAccreditationDoc(nif, nAff);

        assertDoesNotThrow(() -> memAcred.getAccredNumb());
        assertEquals(nAff, memAcred.getAccredNumb());
    }
}
