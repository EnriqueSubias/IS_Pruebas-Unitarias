package src.services;

import java.util.Scanner;

import src.data.AccredNumb;
import src.data.DocPath;
import src.data.Nif;
import src.data.Password;
import src.data.PINcode;

import src.exceptions.*;

import java.util.Date;

import java.util.HashMap;

public class UnifiedPlatform {

    // The class members
    // Input events

    String[] tramites;

    String[] persona = { "persona fisica", "persona juridica" };
    String[] autMethod = { "Cl@ve PIN", "Cl@ve Permanente", "certificado digital" };

    // Variables para indicar lo que se ha seleccionado, por defecto son null
    String institution;
    String personType;
    String certReport;
    String report;
    String authentication;
    Nif nifdoc;
    PINcode pindoc;
    PINcode pinSMS;
    boolean pinSent;

    CertificationAuthority ca;
    SS seguridadSS;
    MemberAccreditationDoc memberAccred;
    AccredNumb accNumber;
    PDFDocument pdfDoc;

    HashMap<String, String> database;

    public UnifiedPlatform(CertificationAuthority certAutho, SS seguridadSocial) {
        this.ca = certAutho;
        this.seguridadSS = seguridadSocial;
    }

    public void processSearcher() throws AnyKeyWordProcedureException {
        System.out.println("Introduce el tramite a buscar: ");
        try (Scanner sc = new Scanner(System.in)) {
            String keyWord = sc.nextLine();
            enterKeyWords(keyWord);
        }
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        System.out.println("Buscando AAPP...");
        institution = searchKeyWords(keyWord);

        if (institution.compareTo("SS") == 0) {
            System.out.println("SS");
            selectSS();
        } else if (institution.compareTo("AEAT") == 0) {
            System.out.println("AEAT");
        }
    }

    public void selectSS() {
        institution = "SS";
        tramites = new String[2];
        tramites[0] = "vida laboral";
        tramites[1] = "numero seguridad social";
    }

    public void selectAEAT() {
        institution = "AEAT";
        tramites = new String[2];
        tramites[0] = "obtener datos fiscales";
        tramites[1] = "borrador de la renta";
    }

    public void selectMJ() {
        institution = "MJ";
        tramites = new String[1];
        tramites[0] = "solicitar el certificado de nacimiento";
    }

    public void selectDGT() {
        institution = "DGT";
        tramites = new String[1];
        tramites[0] = "puntos del carnet";
    }

    public String getInstitution() {
        return institution;
    }

    public void selectCitizens() {
        if (institution != null) { // Precondiciones
            personType = "persona fisica";
        }
    }

    public void selectBusiness() {
        if (institution != null) { // Precondiciones
            personType = "persona juridica";
        }
    }

    public String getPersonType() {
        return personType;
    }

    public void selectReports() {
        if (institution != null && institution.compareTo("SS") == 0 && personType != null
                && personType.compareTo("persona fisica") == 0) { // Precondiciones
            report = "informes y certificados";
        }

    }

    public String getReport() {
        return report;
    }

    public void selectCertificationReport(byte opc) {
        if (institution != null && institution.compareTo("SS") == 0 && personType != null
                && personType.compareTo("persona fisica") == 0 && report != null
                && report.compareTo("informes y certificados") == 0) { // Precondiciones
            if (opc >= 0 && opc < tramites.length) {
                certReport = tramites[opc];
            }
        }
    }

    public String getCertReport() {
        return certReport;
    }

    public void selectAuthMethod(byte opc) {
        if (institution != null && personType != null && report != null && certReport != null) { // Preconditions
            if (opc >= 0 && opc < autMethod.length) {
                authentication = autMethod[opc];
            }
        }
    }

    public String getAuthentication() {
        return authentication;
    }

    public void enterNIFandPINobt(Nif nif, Date valDate)
            throws AnyMobileRegisteredException, ConnectException,
            IncorrectValDateException, NullValDateException, NifNotRegisteredException, NullNifException,
            NullPinException, NotValidPINException {

        if (institution != null && personType != null && certReport != null && authentication != null
                && authentication.equals("Cl@ve PIN")) { // Preconditions
            if (nif == null) {
                throw new NullNifException();
            }
            if (dateValid(valDate) && (ca.sendPIN(nif, valDate))) {
                nifdoc = nif;
                System.out.println("PIN enviado por SMS");
                pinSent = true;

            }
        }

    }

    public void enterClavePermanente(Nif nif, Password pass, Date valDate)
            throws IncorrectValDateException, NullValDateException, NifNotRegisteredException, NotValidCredException,
            AnyMobileRegisteredException, ConnectException, NullPinException, NotValidPINException, NullNifException,
            NullPasswordException {
        if (nif == null) {
            throw new NullNifException();
        }
        if (pass == null) {
            throw new NullPasswordException();
        }
        if (dateValid(valDate)) {
            byte roforzada = ca.ckeckCredent(nif, pass);
            if (roforzada == 1) {
                ca.sendPIN(nif, valDate);
                nifdoc = nif;
                pinSent = true;
            }
        }
    }

    public boolean getEnterNIFandPINobtState() {
        return pinSent;
    }

    public boolean dateValid(Date valDate) throws IncorrectValDateException, NullValDateException {
        if (valDate == null) {
            throw new NullValDateException();
        }
        Date currentDate = new java.util.Date();
        if (currentDate.after(valDate)) {
            throw new IncorrectValDateException();
        } else {
            return true;
        }
    }

    public void enterPIN(PINcode pin) throws NullPointerException, NullPinException, ConnectException,
            IncorrectPinException, NotAffiliatedException, NullNifException, NullAccredNumberException,
            NullValDateException, NullPathException, NullFileException, BadPathException, NotValidNifException,
            NoSuchPeriodException, NifNotRegisteredException {

        if (institution != null && personType != null && certReport != null && authentication != null
                && (authentication.equals("Cl@ve PIN") || (authentication.equals("Cl@ve Permanente"))) && pinSent) { // Preconditions

            if (pin != null) {
                if (ca.checkPIN(nifdoc, pin)) {
                    memberAccred = seguridadSS.getMembAccred(nifdoc);
                    if (memberAccred == null) {
                        throw new NotAffiliatedException();
                    } else {
                        System.out.println("PIN valido");
                        obtainReportSelected();
                    }
                } else {
                    throw new NullPinException();
                }
            } else {
                throw new NullPinException();
            }
        }
    }

    public void selectCertificate(byte opc) {
        // Opcional
    }

    public void enterPassw(Password pas) throws NotValidPasswordException {
        // Opcional
    }

    private void obtainReportSelected() throws NotAffiliatedException, BadPathException, NullPointerException,
            ConnectException, NullPathException, NotValidNifException, NoSuchPeriodException,
            NifNotRegisteredException {
        if (certReport.compareTo("vida laboral") == 0) {
            pdfDoc = seguridadSS.getLaboralLife(nifdoc);
            DocPath defaultPath = new DocPath();
            defaultPath.addDocPath("/tmp/laboralLife" + nifdoc.getNif());
            pdfDoc.moveDoc(defaultPath);
            openDocument(defaultPath);
        }
    }

    private void printDocument() throws BadPathException, PrintingException, NullPathException {
        // No se pide su implementacion.
        printDocument(pdfDoc.getPath());
        System.out.println("PDF impreso");
    }

    private void downloadDocument() throws BadPathException, NullPathException {
        // No se pide su implementacion.
        downloadDocument(pdfDoc.getPath());
        System.out.println("PDF descargado");
    }

    private void selectPath(DocPath path) throws BadPathException, NullPathException {
        // No se pide su implementacion.
        pdfDoc.moveDoc(path);
        System.out.println("Ruta seleccionada");
    }

    // Other operations
    private String searchKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        if (database.containsKey(keyWord)) {
            return database.get(keyWord);
        } else {
            throw new AnyKeyWordProcedureException();
        }
    }

    private void openDocument(DocPath path) throws BadPathException, NullPathException {
        selectPath(path);
        pdfDoc.openDoc(pdfDoc.getPath());
        System.out.println("PDF abierto, ruta: " + path.toString());
    }

    private void printDocument(DocPath path) throws BadPathException, PrintingException, NullPathException {
        printDocument();
        pdfDoc.toString();
        System.out.println("PDF enviado a la impresora, ruta: " + path.toString());
    }

    private void downloadDocument(DocPath path) throws BadPathException, NullPathException {
        downloadDocument();
        pdfDoc.toString();
        System.out.println("PDF enviado para descargar a" + path.toString());
    }

    public void loadDatabase(HashMap<String, String> databaseP) {
        this.database = databaseP;
    }

    // Possibly more operations
}
