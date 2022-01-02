
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

import java.util.HashMap;
import java.util.Map;

public class enterKeywordsTest {

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
    public void successTest() throws AnyKeyWordProcedureException {
        institutionToSelect.put("vida laboral", "SS");
        institutionToSelect.put("numero seguridad social", "SS");
        institutionToSelect.put("puntos del carnet", "DGT");

        uni.loadDatabase(institutionToSelect);

        String keyWordSuccess1 = "SS";
        String keyWordSuccess2 = "DGT";

        uni.enterKeyWords("vida laboral");
        assertEquals(keyWordSuccess1, uni.getInstitution());

        uni.enterKeyWords("puntos del carnet");
        assertEquals(keyWordSuccess2, uni.getInstitution());
    }

    @Test
    public void failedTest() {

        institutionToSelect.put("vida laboral", "SS");
        institutionToSelect.put("numero seguridad social", "SS");
        uni.loadDatabase(institutionToSelect);

        String keyWordFailed1 = "";
        String keyWordFailed2 = "OADF";

        Exception exception1 = assertThrows(AnyKeyWordProcedureException.class,
                () -> uni.enterKeyWords(keyWordFailed1));
        String actualMessage1 = exception1.getMessage();
        assertTrue(actualMessage1.contains("Tramite no encontrado"));

        Exception exception2 = assertThrows(AnyKeyWordProcedureException.class,
                () -> uni.enterKeyWords(keyWordFailed2));
        String actualMessage2 = exception2.getMessage();
        assertTrue(actualMessage2.contains("Tramite no encontrado"));
    }

}
