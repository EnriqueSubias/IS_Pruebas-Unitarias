
package test.services;

import java.util.Date;

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

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class UnifiedPlatformTest {

    DocPath docPath;
    AccredNumb accredNumb;
    Nif nif;
    Password pass;
    PINcode pinCode;
    UnifiedPlatform uni;

    HashMap<String, String> institutionToSelect = new HashMap<>();

    @BeforeEach
    public void setUp() throws AlreadyAddedException, NullPasswordException, NotValidPasswordException,
            NotValidAccredNumberException, NullAccredNumberException, NullNifException, NotValidNifException {
        this.docPath = new DocPath();
        this.accredNumb = new AccredNumb("12345678901");
        CertAutoDobleImplementsTest cert = new CertAutoDobleImplementsTest();
        SSDobleImplementesTest ss = new SSDobleImplementesTest();
        this.uni = new UnifiedPlatform(cert, ss);
    }

    @Test
    @DisplayName("Comprobación en la busqueda de tramites")
    public void keywordSuccessTest() throws AnyKeyWordProcedureException {
        institutionToSelect.put("vida laboral", "SS");
        institutionToSelect.put("numero seguridad social", "SS");
        institutionToSelect.put("puntos del carnet", "DGT");

        uni.loadDatabase(institutionToSelect);

        uni.enterKeyWords("vida laboral");
        assertEquals("SS", uni.getInstitution());

        uni.enterKeyWords("puntos del carnet");
        assertEquals("DGT", uni.getInstitution());
    }

    @Test
    @DisplayName("Comprobación de errores en la busqueda de tramites")
    public void keywordFailedTest() {
        institutionToSelect.put("vida laboral", "SS");
        institutionToSelect.put("numero seguridad social", "SS");
        uni.loadDatabase(institutionToSelect);

        Exception exception1 = assertThrows(AnyKeyWordProcedureException.class,
                () -> uni.enterKeyWords(""));
        String actualMessage1 = exception1.getMessage();
        assertTrue(actualMessage1.contains("Tramite no encontrado"));

        Exception exception2 = assertThrows(AnyKeyWordProcedureException.class,
                () -> uni.enterKeyWords("numero"));
        String actualMessage2 = exception2.getMessage();
        assertTrue(actualMessage2.contains("Tramite no encontrado"));
    }

    @Test
    @DisplayName("Comprobación del tipo de AAPP")
    public void selectAAPPTest() {
        assertEquals(null, uni.getInstitution());

        uni.selectSS();
        assertEquals("SS", uni.getInstitution());

        uni.selectAEAT();
        assertEquals("AEAT", uni.getInstitution());

        uni.selectSS();
        assertEquals("SS", uni.getInstitution());
    }

    @Test
    @DisplayName("Comprobación de si es persona fisica o juridica")
    public void selectPersonTypeTest() {
        uni.selectCitizens();
        assertEquals(null, uni.getPersonType());

        uni.selectSS();
        uni.selectCitizens();
        assertEquals("persona fisica", uni.getPersonType());

        uni.selectBusiness();
        assertEquals("persona juridica", uni.getPersonType());

        uni.selectMJ();
        uni.selectCitizens();
        assertEquals("persona fisica", uni.getPersonType());
    }

    @Test
    @DisplayName("Comprobación de errores de selección de trámites")
    public void selectReportsTest() {
        assertEquals(null, uni.getReport());

        uni.selectSS();
        assertEquals(null, uni.getReport());

        uni.selectCitizens();
        assertEquals(null, uni.getReport());

        uni.selectReports();
        assertEquals("informes y certificados", uni.getReport());

        uni.selectDGT();
        assertEquals("informes y certificados", uni.getReport());
    }

    @Test
    @DisplayName("Comprobación de los trámites")
    public void selectCertificationReportTest1() {
        assertEquals(null, uni.getCertReport());

        uni.selectSS();
        assertEquals(null, uni.getCertReport());

        uni.selectCitizens();
        assertEquals(null, uni.getCertReport());

        uni.selectReports();
        assertEquals(null, uni.getCertReport());

        uni.selectCertificationReport((byte) 0);
        assertEquals("vida laboral", uni.getCertReport());

        uni.selectCertificationReport((byte) 1);
        assertEquals("numero seguridad social", uni.getCertReport());
    }

    @Test
    @DisplayName("Comprobación de la selección de autentificación")
    public void selectAuthMethodTest() {
        assertEquals(null, uni.getAuthentication());

        uni.selectSS();
        assertEquals(null, uni.getAuthentication());

        uni.selectCitizens();
        assertEquals(null, uni.getAuthentication());

        uni.selectReports();
        assertEquals(null, uni.getAuthentication());

        uni.selectCertificationReport((byte) 1);
        assertEquals(null, uni.getAuthentication());

        uni.selectAuthMethod((byte) 0);
        assertEquals("Cl@ve PIN", uni.getAuthentication());
    }

    @Test
    @DisplayName("Comprobación del NIF y PIN de Cl@ve")
    public void enterNIFandPINobtTest()
            throws NullNifException, NotValidNifException {
        uni.selectSS();
        uni.selectCitizens();
        uni.selectReports();
        uni.selectCertificationReport((byte) 1);
        uni.selectAuthMethod((byte) 0);

        assertThrows(NullNifException.class, () -> uni.enterNIFandPINobt(null, null));
        nif = new Nif("12345678A");
        assertThrows(NullValDateException.class, () -> uni.enterNIFandPINobt(nif, null));
        Date futureDate = Date.from((new java.util.Date()).toInstant().plusSeconds(1576800000));
        Nif nif2 = new Nif("11111111A");
        assertThrows(NifNotRegisteredException.class, () -> uni.enterNIFandPINobt(nif2, futureDate));
        Date pastDate = Date.from((new java.util.Date()).toInstant().minusSeconds(1576800000));
        assertThrows(IncorrectValDateException.class, () -> uni.enterNIFandPINobt(nif2, pastDate));
        Nif nif3 = new Nif("12345678D");
        assertThrows(AnyMobileRegisteredException.class, () -> uni.enterNIFandPINobt(nif3, futureDate));
        assertDoesNotThrow(() -> uni.enterNIFandPINobt(nif, futureDate));
        assertTrue(uni.getEnterNIFandPINobtState());
    }

    @Test
    @DisplayName("Comprobación enterPin")
    public void enterPinTest()
            throws NullPinException, NotValidPINException, NullNifException, NullPointerException,
            NotValidNifException {
        // Emulacion hasta llegar a enterPin
        uni.selectSS();
        uni.selectCitizens();
        uni.selectReports();
        uni.selectCertificationReport((byte) 1);
        uni.selectAuthMethod((byte) 0);

        nif = new Nif("12345678A");
        Date futureDate = Date.from((new java.util.Date()).toInstant().plusSeconds(1576800000));
        assertDoesNotThrow(() -> uni.enterNIFandPINobt(nif, futureDate));
        PINcode pin = new PINcode("000");
        assertDoesNotThrow(() -> uni.enterPIN(pin));
        PINcode pin2 = new PINcode("111");
        assertThrows(NullPinException.class, () -> uni.enterPIN(null));
        assertThrows(IncorrectPinException.class, () -> uni.enterPIN(pin2));
    }

    @Test
    @DisplayName("Comprobación Con clave permanente")
    public void clavePermanenteTest()
            throws NullPasswordException, NotValidPasswordException, NullNifException, NotValidNifException {
        uni.selectSS();
        uni.selectCitizens();
        uni.selectReports();
        uni.selectCertificationReport((byte) 1);
        uni.selectAuthMethod((byte) 1);

        nif = new Nif("12345678A");
        Date futureDate = Date.from((new java.util.Date()).toInstant().plusSeconds(1576800000));
        Password pass1 = new Password("Pepito01");

        assertDoesNotThrow(() -> uni.enterClavePermanente(nif, pass1, futureDate));
        assertThrows(NullNifException.class, () -> uni.enterClavePermanente(null, pass1, futureDate));
        assertThrows(NullPasswordException.class, () -> uni.enterClavePermanente(nif, null, futureDate));
        assertThrows(NullValDateException.class, () -> uni.enterClavePermanente(nif, pass1, null));
        Nif nif2 = new Nif("82345678X");
        assertThrows(NifNotRegisteredException.class, () -> uni.enterClavePermanente(nif2, pass1, futureDate));
        Password pass2 = new Password("Pepito02");
        assertThrows(NotValidCredException.class, () -> uni.enterClavePermanente(nif, pass2, futureDate));
        Nif nif3 = new Nif("12345678D");
        assertThrows(AnyMobileRegisteredException.class, () -> uni.enterClavePermanente(nif3, pass1, futureDate));

    }

    @Test
    @DisplayName("Comprobacion de la fecha de validez del NIF")
    public void dateValidTest() throws IncorrectValDateException, NullValDateException {
        Date pastDate = Date.from((new java.util.Date()).toInstant().minusSeconds(1576800000));
        Exception exception = assertThrows(IncorrectValDateException.class, () -> uni.dateValid(pastDate));
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains("NIF and date do not correspond with the citizen"));

        Date actualDate = Date.from(new java.util.Date().toInstant());
        assertTrue(uni.dateValid(actualDate));

        Date futureDate = Date.from((new java.util.Date()).toInstant().plusSeconds(1576800000));
        assertTrue(uni.dateValid(futureDate));
    }

}
