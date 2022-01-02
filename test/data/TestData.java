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
    public void dateValidTest() throws IncorrectValDateException {
        Date futureDate = new Date(2022/12/16);
        // LocalDate.from(futuretDate.toInstant().plusSeconds((long) 36000 * 7));
        assertTrue(UnifiedPlatform.dateValid(futureDate));

        // Date pastDate = new Date();
        // LocalDate.from(pastDate.toInstant().plusDays(7))
        // assertFalse(dateValid(pastDate));

    }

    // @Test
    // public void sendPinTest() throws NifNotRegisteredException,
    // IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
    // Nif nifNumber = new Nif("12345678A");
    // Date valDate = new Date(14 / 02 / 2022);
    // // PINcode pinCodeTest = new enterNIFPINobt(nifNumber, valDate);

    // // UnifiedPlatform.enterNIFPINobt(nifNumber, valDate);
    // }

}
