package src.services;

import java.util.Scanner;

import src.data.AccredNumb;
import src.data.DocPath;
import src.data.Nif;
import src.data.Password;
import src.data.PINcode;

import src.exceptions.*;
import src.publicadministration.*;
import src.services.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class UnifiedPlatform implements CertificationAuthority {

    // The class members

    // Input events

    String[] tramites = { "vida laboral", "numero seguridad social" };
    String[] instituciones = { "SS", "AEAT", "DGT", "MJ" };
    String[] persona = { "persona física", "persona jurídica" };
    String[] autMethod = { "Cl@ve PIN", "certificado digital" };

    String institution;
    String personType;
    String report;
    String certReport;
    String authentication;
    Nif nifdoc;
    PINcode pindoc;
    PINcode pinSMS;

    public void processSearcher() {
        // se procede a utilizar el buscador de trámites. Este evento emula la acción de
        // clicar en el buscador para desplegar el campo de texto en el que introducir
        // la o las palabras clave.
        System.out.println("Introduce el tramite a buscar: ");
        try (Scanner sc = new Scanner(System.in)) {
            String keyWord = sc.nextLine();
            try {
                enterKeyWords(keyWord);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        // se introduce/n la/s palabra/s clave en el buscador de trámites. Obtiene la
        // AAPP responsable de ese trámite y la muestra por pantalla.
        System.out.println("Buscando AAPP...");
        for (int i = 0; i < tramites.length; i++) {
            if (tramites[i].compareTo(keyWord.toLowerCase()) == 0) {
                selectSS();
            }
        }
        throw new AnyKeyWordProcedureException();
    }

    public void selectSS() {
        // Evento que emula la accion de clicar en la sección SS en el mosaico inicial
        String institutionToSelect = "SS";
        for (int i = 0; i < instituciones.length; i++) {
            if (institutionToSelect.compareTo(instituciones[i]) == 0) {
                institution = instituciones[i];
                System.out.println("SS Seleccionada");
                break;
            }
        }
        selectCitizens();
    }

    public void selectCitizens() {
        // Evento que emula la acción de clicar el enlace 'Ciudadanos', en la sección de
        // la SS
        String personTypeToSelect = "persona física";
        if (institution != null) {
            for (int i = 0; i < persona.length; i++) {
                if (persona[i].compareTo(personTypeToSelect) == 0) {
                    personType = persona[i];
                }
            }
            System.out.println("Persona física Seleccionado");
        }
        selectReports();
    }

    public void selectReports() {
        // evento que emula la acción de clicar el enlace 'Informes y certificados', en
        // el apartado 'Ciudadanos' de la SS
        String reportToSelect = "Informes y certificados";
        if (personType != null && personType.compareTo("persona física") == 0) {
            report = reportToSelect;
        }
        byte option = 0;
        selectCertificationReport(option);
    }

    public void selectCertificationReport(byte opc) {
        // evento que emula la acción de seleccionar el informe o certificado concreto
        // que se desea obtener, tras presentar un menú con las dos opciones
        // disponibles. Utilizaremos un byte para indicar de qué informe se trata.
        if (institution != null && personType != null && personType.compareTo("persona física") == 0) {
            // if (opc == 0 ) {
            // // Get Loboral LifeDoc
            // certReport = autMethod[opc];
            // selectAuthMethod(opc);
            // } else if (opc == 1) {
            // // Get Numero SS
            // certReport = autMethod[opc];
            // selectAuthMethod(opc);
            // }
            try {
                certReport = autMethod[opc];
            } finally {
                selectAuthMethod(opc);
            }

            // No se ha selecionado una opcion valida
        }

    }

    public void selectAuthMethod(byte opc) {

        if (institution != null && personType != null && personType.compareTo("persona física") == 0
                && certReport != null) {
            if (opc == 1) {
                // Cl@ve PIN
                authentication = tramites[opc];
                System.out.println("Cl@ve PIN Seleccionada");
            } else if (opc == 2) {
                // certificado digital
                authentication = tramites[opc];
                System.out.println("certificado digital Seleccionado");
            }
            // No se ha selecionado una opcion valida
        }
    }

    public void enterNIFPINobt(Nif nif, Date valDate) throws NifNotRegisteredException,
            AnyMobileRegisteredException, ConnectException, NullPointerException, IllegalArgumentException,
            IncorrectValDateException {
        // transmetre les dades del ciutadà que l’acrediten en Cl@ve PIN,
        // i sol·licitud del PIN per a la realització d’un tràmit,
        // via connexió amb l’autoritat de certificació responsable
        if (institution != null && personType != null && personType.compareTo("persona física") == 0
                && certReport != null && authentication.equals("Cl@ve PIN")) { // Preconditions
            if (dateValid(valDate)) {
                Nif nifdoc = new Nif(nif.getNif());
                if (sendPIN(nifdoc, valDate)) {
                    System.out.println("PIN enviado por SMS");
                }
            }
        }
    }

    public static boolean dateValid(Date valDate) throws IncorrectValDateException {
        // if (valDate.before(Calendar.getInstance().getTime())) {
        // if (valDate.before(LocalDate.now())) {
        // if (valDate < hoy) {
        LocalDate valD = Instant.ofEpochMilli(valDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        //if (LocalDate(valD).isBefore(LocalDate.now())) {
        if (valD.compareTo(today) > 0) {
            throw new IncorrectValDateException();
        } else {
            return true;
        }
    }

    public void enterPIN(PINcode pin) throws NotValidPINException, NotAffiliatedException, ConnectException {
        // el usuario introduce el PIN recibido vía SMS, con objeto de completar su
        // identificación. Esta operación se aplica siempre en el proceso de
        // identificación con Cl@ve PIN, pero también en los casos en los que se escoge
        // Cl@ve permanente y el usuario tiene activado el método reforzado.
        this.pindoc = new PINcode(pin.getPin());
        // if ((CertificationAutohrity.checkPIN(nifdoc.getNif(),this.pindoc.getPin())

    }

    private void printDocument() throws BadPathException, PrintingException {
        // TODO
    }

    private void downloadDocument() {
        // TODO
    }

    private void selectPath(DocPath path) throws BadPathException {
        // TODO
    }

    // Other operations
    private String searchKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        return new String("A");
    }

    private void OpenDocument(DocPath path) throws BadPathException {
        // TODO
    }

    private void printDocument(DocPath path) throws BadPathException, PrintingException {
        // TODO
    }

    private void downloadDocument(DocPath path) throws BadPathException {
        // TODO
    }

    @Override
    public boolean sendPIN(Nif nif, Date vaID) { // throws NifNotRegisteredException, IncorrectValDateException,
        // AnyMobileRegisteredException, ConnectException {
        try {
            pinSMS = new PINcode("842");
        } finally {
            System.out.println("SMS enviado correctamente");
        }
        return true;
    }

    @Override
    public boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public byte ckeckCredent(Nif nif, Password passw)
            throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
        // TODO Auto-generated method stub
        return 0;
    }

    // Possibly more operations
}

// Notas:
// Es el controlador del caso de uso, tenemos que simular que todo lo del DSS se
// haga correctamente
// Mostrar mensajes por cada paso del DSS

// Ojo: Un metodo de test por cada metodo NO es valido

// Hay que hacer un metodo de test por cada parte relevante del caso de uso, y
// tendran que ir en orden, para hacer un test habrá que haber hecho los
// anteriores
