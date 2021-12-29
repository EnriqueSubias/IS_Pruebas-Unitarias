package src.services;

import java.io.File;
import java.io.IOException;
import java.util.*;

import src.data.DocPath;

public class PDFDocument { // Represents a PDF document

    private Date creatDate;
    private DocPath path;
    private File file;

    public PDFDocument() { // Initializes attributes and emulates the document download at a default path
        // this.createDate = createDate;
        // this.path = path;
        // this.file = file;
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
        return ""; // To implement only optionally
    }

    public void moveDoc(DocPath destPath) throws IOException { // Moves the document to the destination path indicated

    }
    
    public void openDoc(DocPath path) throws IOException { // Opens the document at the path indicated

    }

}
