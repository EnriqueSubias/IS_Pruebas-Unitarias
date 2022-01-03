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

public class DataTest {

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
        docPath.addDocPath(path);
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

}
