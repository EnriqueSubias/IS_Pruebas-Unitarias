package test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.util.Calendar;
import java.util.Date;

import javax.accessibility.AccessibleRelation;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import src.data.AccredNumb;
import src.data.Nif;
import src.exceptions.*;

import src.services.MemberAccreditationDoc;

public class MemberAcreditationDocTest {

    MemberAccreditationDoc memAcred;

    // // TODO falta comprobar test
    // @Test
    // public void getNullNifTest() {
    // assertNull(memAcred.getNif());
    // }

    // // TODO falta comprobar test
    // @Test
    // public void getNullAcredNumbTest() {
    // assertNull(memAcred.getAccredNumb());
    // }

    @BeforeEach
    public void setUp()
            throws NotValidAccredNumberException, NullAccredNumberException, NullNifException, NotValidNifException {
        Nif nif = new Nif("12345678A");
        AccredNumb acreedNumb = new AccredNumb("123456781");
        memAcred = new MemberAccreditationDoc(nif, acreedNumb);
    }

    @Test
    public void getNifTest() {
        assertEquals("12345678A", memAcred.getNif());
        // TODO Falla
    }

    @Test
    public void getAcredNumbTest() {

        assertEquals("123456781", memAcred.getAccredNumb());
        // TODO Falla
    }
}
