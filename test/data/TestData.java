package test.data;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.data.AccredNumb;
import src.data.DocPath;
import src.data.Nif;
import src.data.Password;
import src.data.PINcode;
import src.services.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestData {

    DocPath docPath;
    AccredNumb accredNumb;
    Nif nif;
    Password pass;
    PINcode pinCode;

    @BeforeEach
    public void setUp() {
        this.docPath = new DocPath();
        this.accredNumb = new AccredNumb();
    }

    @Test
    public void addPathTest() throws NullPointerException {
        String path = "/doc/vida_laboral.pdf";
        docPath.addDocPath(path, accredNumb);
        assertEquals(path, docPath.getPath());
    }

    @Test
    public void addValidAccredNumbTest() throws IllegalArgumentException {
        String code = "75634816211";
        accredNumb.addAccredNumb(code);
        assertEquals(code, accredNumb.getAccredNumber());
    }

    // data
    @Test
    public void addValidNifTest() throws NullPointerException, IllegalArgumentException {
        String nifNumb = "12345678A";
        this.nif = new Nif(nifNumb);
        assertEquals(nifNumb, this.nif.getNif());
    }

    @Test
    public void addValidPasswordTest() throws NullPointerException {
        String password = "Holas123";
        this.pass = new Password(password);
        assertEquals(password, this.pass.getPassword());
    }

    @Test
    public void addValidPinCodeTest() throws IllegalArgumentException, NullPointerException {
        String pin = "123";
        this.pinCode = new PINcode(pin);
        assertEquals(pin, this.pinCode.getPin());
    }

    @Test
    public void dateValidTest() {
        Date futuretDate = new Date();
        LocalDate.from(futuretDate.toInstant().plusDays(7))
        assertTrue(UnifiedPlatform.dateValid(futureDate));

        //Date pastDate = new Date();
        //LocalDate.from(pastDate.toInstant().plusDays(7))
        //assertFalse(dateValid(pastDate));

    }
}
