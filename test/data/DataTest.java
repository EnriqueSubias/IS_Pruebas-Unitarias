package test.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import src.data.AccredNumb;
import src.data.DocPath;
import src.data.Nif;
import src.data.Password;
import src.data.PINcode;

import src.exceptions.*;
import src.services.UnifiedPlatform;
import test.services.*;

import static org.junit.jupiter.api.Assertions.*;

public class DataTest {

    DocPath docPath;
    AccredNumb accredNumb;
    Nif nif;
    Password pass;
    PINcode pinCode;
    UnifiedPlatform uni;

    @BeforeEach
    public void setUp() throws AlreadyAddedException, NullPasswordException, NotValidPasswordException,
            NotValidAccredNumberException, NullAccredNumberException, NullNifException, NotValidNifException {
        this.docPath = new DocPath();
        CertAutoDobleImplementsTest cert = new CertAutoDobleImplementsTest();
        SSDobleImplementesTest ss = new SSDobleImplementesTest();
        this.uni = new UnifiedPlatform(cert, ss);
    }

    @Test
    @DisplayName("Comprobación del docPath")
    public void addPathTest() {
        assertThrows(NullPathException.class, () -> this.docPath.addDocPath(null));
        assertDoesNotThrow(() -> docPath.addDocPath("/doc/vida_laboral.pdf"));
        assertEquals("/doc/vida_laboral.pdf", docPath.getPath());
    }

    @Test
    @DisplayName("Comprobación del numero de acreditaccion")
    public void addValidAccredNumbTest() throws NotValidAccredNumberException {
        assertThrows(NullAccredNumberException.class, () -> this.accredNumb = new AccredNumb(null));
        assertThrows(NotValidAccredNumberException.class, () -> this.accredNumb = new AccredNumb("651348562651"));
        assertDoesNotThrow(() -> this.accredNumb = new AccredNumb("75634816211"));
        assertEquals("75634816211", accredNumb.getAccredNumber());
    }

    @Test
    @DisplayName("Comprobación del NIF")
    public void addValidNifTest() throws NotValidNifException {
        assertThrows(NullNifException.class, () -> this.nif = new Nif(null));
        assertThrows(NotValidNifException.class, () -> this.nif = new Nif("7654321Z"));
        assertDoesNotThrow(() -> this.nif = new Nif("12345678A"));
        assertEquals("12345678A", this.nif.getNif());
    }

    @Test
    @DisplayName("Comprobación de la contraseña")
    public void addValidPasswordTest() {
        assertThrows(NullPasswordException.class, () -> this.pass = new Password(null));
        assertThrows(NotValidPasswordException.class,
                () -> this.pass = new Password("ThisPasswordMayBeTooLongForThePasswordRequiements"));
        assertDoesNotThrow(() -> this.pass = new Password("Holas123"));
        assertEquals("Holas123", this.pass.getPassword());
    }

    @Test
    @DisplayName("Comprobación del pin code")
    public void addValidPinCodeTest() throws NullPinException {
        assertThrows(NullPinException.class, () -> this.pinCode = new PINcode(null));
        assertThrows(NotValidPINException.class, () -> new PINcode("1"));
        assertDoesNotThrow(() -> this.pinCode = new PINcode("123"));
        assertEquals("123", this.pinCode.getPin());
    }

    @Test
    @DisplayName("Comprobación de AccredNumb Class")
    public void accredNumberTest() {
        assertThrows(NotValidAccredNumberException.class, () -> new AccredNumb("length_is_not_11"));
        assertThrows(NotValidAccredNumberException.class, () -> new AccredNumb("11111111|11"));
        assertThrows(NotValidAccredNumberException.class, () -> new AccredNumb("").getAccredNumber());
    }

}
