
package test.data;

import java.util.Date;
import java.time.LocalDate;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import src.data.AccredNumb;
import src.data.DocPath;
import src.data.Nif;
import src.data.Password;
import src.data.PINcode;

import src.exceptions.*;
import src.publicadministration.*;
import src.services.CertificationAuthority;
import src.services.UnifiedPlatform;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.util.function.BooleanSupplier;

import java.util.HashMap;
import java.util.Map;

public class UnifiedPlatformTest {

    DocPath docPath;
    AccredNumb accredNumb;
    Nif nif;
    Password pass;
    PINcode pinCode;
    UnifiedPlatform uni;

    HashMap<String, String> institutionToSelect = new HashMap<>();

    @BeforeEach
    public void setUp() {
        this.docPath = new DocPath();
        this.accredNumb = new AccredNumb();
        this.uni = new UnifiedPlatform();
    }

    @Test
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
     @DisplayName("Comprobación del tipo de ")
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
    public void selectCertificationReportTest() {
        // TODO
        byte tramite0 = 0;
        uni.selectCertificationReport(tramite0);
        String certReport1 = uni.getCertReport();
        System.out.println(certReport1);

        byte tramite1 = 1;
        uni.selectCertificationReport(tramite1);
        String certReport2 = uni.getCertReport();
        System.out.println(certReport2);
    }

    @Test
    @DisplayName("Comprobación de la selección de autentificación")
    public void selectAuthMethodTest() {
        // TODO
        byte aut0 = 0;
        uni.selectAuthMethod(aut0);
        String authentication1 = uni.getAuthentication();
        System.out.println(authentication1);

        byte aut1 = 1;
        uni.selectAuthMethod(aut1);
        String authentication2 = uni.getAuthentication();
        System.out.println(authentication2);
    }

    @Test
    @DisplayName("Comprobación del NIF y PIN de Cl@ve")
    public void enterNIFandPINobtTest()
            throws NifNotRegisteredException, AnyMobileRegisteredException, ConnectException,
            NullPointerException, IllegalArgumentException, IncorrectValDateException {

        System.out.println("nif: " + nif);

        nif = new Nif("12345678A");

        System.out.println(nif);

        Date futureDate = Date.from((new java.util.Date()).toInstant().plusSeconds(1576800000));

        uni.enterNIFandPINobt(nif, futureDate);

        // assertEquals(expected, actual);

    }

    // a.addAccredNumb("47294716742");

    // @Test
    // public void sendPinTest() throws NifNotRegisteredException,
    // IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
    // Nif nifNumber = new Nif("12345678A");
    // Date valDate = new Date(14 / 02 / 2022);
    // // PINcode pinCodeTest = new enterNIFPINobt(nifNumber, valDate);

    // // UnifiedPlatform.enterNIFPINobt(nifNumber, valDate);
    // }

    @Test
    @DisplayName("Comprobacion de la fecha de validez del NIF")
    public void dateValidTest() throws IncorrectValDateException {
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
