package test.services;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import src.data.DocPath;
import src.exceptions.*;
import src.services.PDFDocument;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class PDFDocumentTest {
    PDFDocument pdfDoc;

    @Test
    @DisplayName("PdfDocument Creattion test")
    public void pdfDocumentCreationTest() throws NullValDateException, NullPathException, NullFileException {
        Date creatDate;
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

    //TODO OJO DESCOMENTAR IMPORTANTE!!!!

    // @Test
    // @DisplayName("Getters Tests PdfDocument")
    // public void getTestPdfDocument() throws NullValDateException, NullPathException, NullFileException {
    //     DocPath path;
    //     File file;
    //     Calendar cal = Calendar.getInstance();
    //     cal.set(2018, Calendar.JANUARY, 10); // Year, month and day of month
    //     Date createDate = cal.getTime();
    //     path = new DocPath();
    //     file = new File("");
    //     this.pdfDoc = new PDFDocument(null, null, null);
    //     assertThrows(NullValDateException.class, () -> this.pdfDoc.getCreatDate());
    //     assertThrows(NullPathException.class, () -> this.pdfDoc.getPath());
    //     assertThrows(NullFileException.class, () -> this.pdfDoc.getFile());
    //     this.pdfDoc = new PDFDocument(createDate, path, file);
    // }

    // @Test
    // @DisplayName("moveDoc Test PDFDocument")
    // public void moveDocTest() throws BadPathException, NullPathException, NullValDateException, NullFileException {
    //     Date createDate = new Date();
    //     DocPath path = new DocPath();
    //     File file = new File("");
    //     this.pdfDoc = new PDFDocument(createDate, path, file);
    //     assertThrows(NullPathException.class, () -> pdfDoc.moveDoc(null));
    //     assertThrows(BadPathException.class, () -> pdfDoc.moveDoc(path));
    // }

    // @Test
    // @DisplayName("openDoc Test PDFDocument")
    // public void openDocTest() throws BadPathException, NullPathException, NullValDateException, NullFileException {
    //     Date createDate = new Date();
    //     DocPath path = new DocPath();
    //     File file = new File("");
    //     this.pdfDoc = new PDFDocument(createDate, path, file);
    //     assertThrows(NullPathException.class, () -> pdfDoc.openDoc(null));
    //     assertThrows(BadPathException.class, () -> pdfDoc.openDoc(path));
    // }

}