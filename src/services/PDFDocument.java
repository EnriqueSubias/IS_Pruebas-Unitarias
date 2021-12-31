package src.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.awt.Desktop;
import src.data.DocPath;

public class PDFDocument { // Represents a PDF document

    private Date creatDate;
    private DocPath path;
    public File file;

    public PDFDocument() { // Initializes attributes and emulates the document download at a default path
        // this.createDate = createDate
        // this.path = path
        // this.file = file
    }

    // the getters
    public Date getCreatDate() {
        return this.creatDate;
    }

    public DocPath getPath() {
        return this.path;
    }

    public File getFile() {
        return this.file;
    }

    public String toString() { // Converts to String members Date and DocPath
        return "fecha de creacion: " + this.creatDate + " ruta del archvio: " + this.path;
        // To implement only optionally
    }

    public void moveDoc(DocPath destPath) throws IOException { // Moves the document to the destination path indicated
        File from = new File(this.file.getPath());
        File to = new File(destPath.getPath());
        try {
            Files.move(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.path = destPath;
    }

    public void openDoc(DocPath path) throws IOException { // Opens the document at the path indicated
        try {
            File filePath = new File(path.getPath());
            Desktop.getDesktop().open(filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
