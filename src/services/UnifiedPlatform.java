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

    String[] instituciones = { "SS", "AEAT", "DGT", "MJ" };
    String[] tramites;// ={ "vida laboral", "numero seguridad social" };

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

    public UnifiedPlatform(CertificationAuthority CertAutho, SS SeguridadSocial) {
        this.ca = CertAutho;
        this.seguridadSS = SeguridadSocial;
    }

    public void processSearcher() throws AnyKeyWordProcedureException {
        // se procede a utilizar el buscador de tramites. Este evento emula la accion de
        // clicar en el buscador para desplegar el campo de texto en el que introducir
        // la o las palabras clave.

        System.out.println("Introduce el tramite a buscar: ");
        try (Scanner sc = new Scanner(System.in)) {
            String keyWord = sc.nextLine();
            enterKeyWords(keyWord);
            // try {
            // enterKeyWords(keyWord);
            // } catch (Exception ex) {
            // ex.printStackTrace();
            // }
        }
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        // se introduce/n la/s palabra/s clave en el buscador de tramites. Obtiene la
        // AAPP responsable de ese tramite y la muestra por pantalla.
        System.out.println("Buscando AAPP...");
        institution = searchKeyWords(keyWord);

        if (institution.compareTo("SS") == 0) {
            System.out.println("SS");
            selectSS();
        } else if (institution.compareTo("AEAT") == 0) {
            System.out.println("AEAT");
            // selectAEAT();
        }
        // throw AnyKeyWordProcedureException();
    }

    public void selectSS() {
        // Evento que emula la accion de clicar en la seccion SS en el mosaico inicial.
        institution = "SS";
        tramites = new String[2];
        tramites[0] = "vida laboral";
        tramites[1] = "numero seguridad social";
        // String institutionToSelect = "SS";
        // for (int i = 0; i < instituciones.length; i++) {
        // if (institutionToSelect.compareTo(instituciones[i]) == 0) {
        // institution = instituciones[i];
        // System.out.println("SS Seleccionada");
        // break;
        // }
        // }
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

    // public String[] getTramites() {
    // return tramites;
    // }

    public void selectCitizens() {
        // Evento que emula la accion de clicar el enlace 'Ciudadanos', en la SS
        // String personTypeToSelect = "persona fisica";
        if (institution != null) { // Precondiciones
            // for (int i = 0; i < persona.length; i++) {
            // if (persona[i].compareTo(personTypeToSelect) == 0) {
            personType = "persona fisica";
            // }
            // }
            // System.out.println("Persona fisica Seleccionado");
        } // selectReports();
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
        // Clicar el enlace 'Informes y certificados', en 'Ciudadanos' de la SS
        // String reportToSelect = "Informes y certificados";
        if (institution != null && institution.compareTo("SS") == 0 && personType != null
                && personType.compareTo("persona fisica") == 0) { // Precondiciones
            report = "informes y certificados";
        }
        // byte option = 0;
        // selectCertificationReport(option);
    }

    public String getReport() {
        return report;
    }

    public void selectCertificationReport(byte opc) {
        // evento que emula la accion de seleccionar el informe o certificado concreto
        // que se desea obtener, tras presentar un menu con las dos opciones
        // disponibles. Utilizaremos un byte para indicar de que informe se trata.

        if (institution != null && institution.compareTo("SS") == 0 && personType != null
                && personType.compareTo("persona fisica") == 0 && report != null
                && report.compareTo("informes y certificados") == 0) { // Precondiciones
            // if (opc == 0) {
            // // pdfDoc = new LaboralLifeDoc();
            // } else if (opc == 1) {
            // // pdfDoc = new MemberAccreditationDoc();
            // }
            if (opc >= 0 && opc < tramites.length) {
                certReport = tramites[opc];
            }
        }
    }

    public String getCertReport() {
        return certReport;
    }

    public void selectAuthMethod(byte opc) {
        if (institution != null && personType != null && report != null && certReport != null) {
            // if (opc == 0) {
            // // Cl@ve PIN
            // authentication = autMethod[opc];
            // System.out.println("Cl@ve PIN Seleccionada");
            // } else if (opc == 1) {
            // // certificado digital
            // authentication = autMethod[opc];
            // System.out.println("certificado digital Seleccionado");
            // }
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
        // transmetre les dades del ciutadà que l’acrediten en Cl@ve PIN,
        // i sol·licitud del PIN per a la realització d’un tràmit,
        // via connexió amb l’autoritat de certificació responsable

        if (institution != null && personType != null && certReport != null && authentication != null
                && authentication.equals("Cl@ve PIN")) { // Preconditions
            if (nif == null) {
                throw new NullNifException();
            }
            if (dateValid(valDate)) {
                if (ca.sendPIN(nif, valDate)) {
                    nifdoc = nif;
                    System.out.println("PIN enviado por SMS");
                    pinSent = true;
                }
            }
        }
    }

    public void enterClavePermanente(Nif nif, Password pass, Date valDate)
            throws IncorrectValDateException, NullValDateException, NifNotRegisteredException, NotValidCredException,
            AnyMobileRegisteredException, ConnectException, NullPinException, NotValidPINException {
        if (dateValid(valDate)) {
            // if (ca.ckeckCredent(nif, pass)) {
            byte roforzada = ca.ckeckCredent(nif, pass);
            if (roforzada == 1) {
                ca.sendPIN(nif, valDate);
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

    public void enterPIN(PINcode pin)
            throws NotValidNifException, NullPinException, NotAffiliatedException, ConnectException,
            BadPathException, NullPointerException, NullPathException {
        // el usuario introduce el PIN recibido via SMS, con objeto de completar su
        // identificacion. Esta operacion se aplica siempre en el proceso de
        // identificacion con Cl@ve PIN, pero tambien en los casos en los que se escoge
        // Cl@ve permanente y el usuario tiene activado el metodo reforzado.

        if (institution != null && personType != null && certReport != null && authentication != null
                && (authentication.equals("Cl@ve PIN") || (authentication.equals("Cl@ve Permanente"))) && pinSent) { // Preconditions

            // this.pindoc = new PINcode(pin.getPin());
            // this.MA = MemberAccreditationDoc(seguridadSS.getMembAccred(nifdoc)) {
            if (ca.checkPIN(nifdoc, pin)) {
                memberAccred = seguridadSS.getMembAccred(nifdoc);
                if (memberAccred == null) {
                    throw new NotAffiliatedException();
                } else {
                    // System.out.println("PIN valido");
                    obtainReportSelected();
                }
            } else {
                throw new NullPinException();
            }
        }
    }

    public void selectCertificate(byte opc) {
        // TODO Opcional
    }

    public void enterPassw(Password pas) throws NotValidPasswordException {
        // TODO Opcional
    }

    private void obtainReportSelected() throws NotAffiliatedException, BadPathException, NullPointerException,
            ConnectException, NullPathException, NotValidNifException {
        if (certReport.compareTo("vida laboral") == 0) {
            pdfDoc = seguridadSS.getLaboralLife(nifdoc);
            DocPath defaultPath = new DocPath();
            defaultPath.addDocPath("/tmp/laboralLife" + nifdoc.getNif());
            pdfDoc.moveDoc(defaultPath);
            OpenDocument(defaultPath);
        }
    }

    private void printDocument() throws BadPathException, PrintingException, NullPathException {
        // El usuario lanza la orden de imprimir el documento. No se pide su
        // implementacion.
        printDocument(pdfDoc.getPath());
        System.out.println("PDF impreso");
    }

    private void downloadDocument() throws BadPathException, NullPathException {
        // El usuario lanza la orden de descargar el documento. No se pide su
        // implementacion.
        downloadDocument(pdfDoc.getPath());
        System.out.println("PDF descargado");
    }

    private void selectPath(DocPath path) throws BadPathException, NullPathException {
        // El usuario escoge la ruta en la que guardar el documento. No se pide su
        // implementacion.
        pdfDoc.moveDoc(path);
        System.out.println("Ruta seleccionada");
    }

    // Other operations
    private String searchKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        if (database.containsKey(keyWord)) {
            String aux = database.get(keyWord);
            return aux;
        } else {
            throw new AnyKeyWordProcedureException();
        }
    }

    private void OpenDocument(DocPath path) throws BadPathException, NullPathException {
        pdfDoc.openDoc(pdfDoc.getPath());
        System.out.println("PDF abierto, ruta: " + path.toString());
    }

    private void printDocument(DocPath path) throws BadPathException, PrintingException {
        pdfDoc.toString();
        System.out.println("PDF enviado a la impresora, ruta: " + path.toString());
    }

    private void downloadDocument(DocPath path) throws BadPathException {
        pdfDoc.toString();
        System.out.println("PDF enviado para descargar a" + path.toString());
    }

    public void loadDatabase(HashMap<String, String> databaseP) {
        // TODO poner como boolean
        this.database = databaseP;
    }

    // Possibly more operations
}

// Notas:
// Es el controlador del caso de uso, tenemos que simular que todo lo del DSS se
// haga correctamente
// Mostrar mensajes por cada paso del DSS

// Ojo: Un metodo de test por cada metodo NO es valido

// Hay que hacer un metodo de test por cada parte relevante del caso de uso, y
// tendran que ir en orden, para hacer un test habra que haber hecho los
// anteriores
