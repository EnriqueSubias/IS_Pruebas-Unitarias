package test.services;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import src.data.DocPath;
import src.exceptions.*;
import src.services.PDFDocument;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class PDFDocumentTest {
    PDFDocument pdfDoc;

    @Test
    @DisplayName("PdfDocument Creattion test")
    public void pdfDocumentCreationTest() {
        // Date creatDate;
        DocPath path;
        File file;
        // Date createDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
        Date createDate = cal.getTime();
        path = new DocPath();
        file = new File("");
        assertThrows(NullValDateException.class, () -> this.pdfDoc = new PDFDocument(null, null, null));
        assertThrows(NullPathException.class, () -> this.pdfDoc = new PDFDocument(createDate, null, null));
        assertThrows(NullFileException.class, () -> this.pdfDoc = new PDFDocument(createDate, path, null));
        assertDoesNotThrow(() -> new PDFDocument(createDate, path, file));
        /*
         * assertThrows(NotValidPasswordException.class,
         * () -> this.pass = new
         * Password("ThisPasswordMayBeTooLongForThePasswordRequiements"));
         * assertDoesNotThrow(() -> this.pass = new Password("Holas123"));
         * assertEquals("Holas123", this.pass.getPassword());
         */
    }

    @Test
    @DisplayName("Getters Tests PdfDocument")
    public void getPdfDocumentTest() throws NullValDateException, NullPathException, NullFileException {
        DocPath path;
        File file;
        Calendar cal = Calendar.getInstance();
        cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
        Date createDate = cal.getTime();
        path = new DocPath();
        file = new File("");
        this.pdfDoc = new PDFDocument(createDate, path, file);
        assertEquals(createDate, pdfDoc.getCreatDate());
        assertEquals(file, pdfDoc.getFile());
        assertEquals(path, pdfDoc.getPath());
    }

    @Test
    @DisplayName("moveDoc Test PDFDocument")
    public void moveDocTest() throws NullPathException, NullValDateException, NullFileException {
        Date createDate = new Date();
        DocPath destPath = new DocPath();
        destPath.addDocPath("/Users/enriquesubias/Documents/GitHub/IS_Pruebas-Unitarias/test/a.txt");
        File file = new File("/Users/enriquesubias/Documents/GitHub/IS_Pruebas-Unitarias/test/services/a.txt");

        this.pdfDoc = new PDFDocument(createDate, destPath, file);
        assertThrows(NullPathException.class, () -> pdfDoc.moveDoc(null));
        assertDoesNotThrow(() -> pdfDoc.moveDoc(destPath));
        assertFalse(file.exists());

        DocPath destPath2 = new DocPath();
        destPath2.addDocPath("/Users/enriquesubias/Documents/GitHub/IS_Pruebas-Unitarias/test/services/a.txt");
        File file2 = new File("/Users/enriquesubias/Documents/GitHub/IS_Pruebas-Unitarias/test/a.txt");
        this.pdfDoc = new PDFDocument(createDate, destPath2, file2);
        assertDoesNotThrow(() -> pdfDoc.moveDoc(destPath2));
        assertTrue(file.exists());
    }

    @Test
    @DisplayName("openDoc Test PDFDocument")
    public void openDocTest() throws NullPathException, NullValDateException, NullFileException {
        Date createDate = new Date();
        DocPath path = new DocPath();
        path.addDocPath("/Users/enriquesubias/Documents/GitHub/IS_Pruebas-Unitarias/test/services/a.txt");
        File file = new File("");
        this.pdfDoc = new PDFDocument(createDate, path, file);
        assertThrows(NullPathException.class, () -> pdfDoc.openDoc(null));
        DocPath badPath = new DocPath();
        badPath.addDocPath("/Users/enriquesubias/Documents/GitHub/IS_Pruebas-Unitarias/a.txt");
        assertThrows(BadPathException.class, () -> pdfDoc.openDoc(badPath));
        assertDoesNotThrow(() -> pdfDoc.openDoc(path));
    }

}