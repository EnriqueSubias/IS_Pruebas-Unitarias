package src.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.awt.Desktop;
import src.data.DocPath;
import src.exceptions.*;

public class PDFDocument { // Represents a PDF document

    private Date creatDate;
    private DocPath path;
    public File file;

    public PDFDocument() {
    }

    public PDFDocument(Date creatDate, DocPath path, File file)
            throws NullValDateException, NullPathException, NullFileException {
        // Initializes attributes and emulates the document
        // download at a default path
        if (creatDate == null) {
            throw new NullValDateException();
        }
        if (path == null) {
            throw new NullPathException();
        }
        if (file == null) {
            throw new NullFileException();
        }

        this.creatDate = creatDate;
        this.path = path;
        this.file = file;
    }

    // the getters
    public Date getCreatDate() throws NullValDateException {
        if (creatDate == null) {
            throw new NullValDateException();
        }
        return this.creatDate;
    }

    public DocPath getPath() throws NullPathException {
        if (path == null) {
            throw new NullPathException();
        }
        return this.path;
    }

    public File getFile() throws NullFileException {
        if (file == null) {
            throw new NullFileException();
        }
        return this.file;
    }

    @Override
    public String toString() { // Converts to String members Date and DocPath
        return "fecha de creacion: " + this.creatDate + " ruta del archvio: " + this.path;
        // To implement only optionally
    }

    public void moveDoc(DocPath destPath) throws BadPathException, NullPathException { // Moves the document to the
                                                                                       // destination path
        // indicated
        if (destPath == null) {
            throw new NullPathException();
        }
        // File from = new File(this.file.getPath());
        File to = new File(destPath.getPath());
        try {
            Files.move(file.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully.");
        } catch (IOException ex) {
            System.out.println(ex);
            throw new BadPathException();
        }
        this.path = destPath;
    }

    public void openDoc(DocPath path) throws BadPathException, NullPathException { // Opens the document at the path
                                                                                   // indicated
        if (path == null) {
            throw new NullPathException();
        }
        try {
            File filePath = new File(path.getPath());
            Desktop.getDesktop().open(filePath);
        } catch (Exception ex) {
            throw new BadPathException();
        }
    }

}
