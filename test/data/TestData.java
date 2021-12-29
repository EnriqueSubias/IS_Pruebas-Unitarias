package test.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.data.AccredNumb;
import src.data.DocPath;

import static org.junit.jupiter.api.Assertions.*;

public class TestData {

    DocPath docPath;
    AccredNumb accredNumb;

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
    public void addAccredNumbTest() throws IllegalArgumentException {
        String code = "75634816211";
        accredNumb.addAccredNumb(code);
        assertEquals(code, accredNumb.getAccredNumber());
    }

}
