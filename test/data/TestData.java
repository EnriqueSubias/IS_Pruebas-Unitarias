package test.data;

import java.util.Date;
import java.time.LocalDate;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
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

public class TestData {

    DocPath docPath;
    AccredNumb accredNumb;
    Nif nif;
    Password pass;
    PINcode pinCode;
    UnifiedPlatform uni;

    @BeforeEach
    public void setUp() {
        this.docPath = new DocPath();
        this.accredNumb = new AccredNumb();
        this.uni = new UnifiedPlatform();
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

    @Test
    public void enterNIFandPINobtTest() throws NifNotRegisteredException, AnyMobileRegisteredException, ConnectException,
            NullPointerException, IllegalArgumentException, IncorrectValDateException {
        Date futureDate = Date.from((new java.util.Date()).toInstant().plusSeconds(1576800000));

        uni.enterNIFandPINobt(nif, futureDate);
        //assertEquals(expected, actual);

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

}
