package src.services;

import java.util.Scanner;

import src.data.Nif;
import src.data.PINcode;
import src.data.DocPath;
import src.exceptions.*;

import java.util.Date;

public class UnifiedPlatform {

    // The class members

    // Input events

    String[] tramites = { "vida laboral", "numero seguridad social" };
    String[] instituciones = { "SS", "AEAT", "DGT", "MJ" };

    String institution;
    String personType;
    String report;

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
        String personTypeToSelect = "Ciudadanos";
        if (institution != null) {
            personType = personTypeToSelect;
            System.out.println("Citizens Seleccionado");
        }
        selectReports();
    }

    public void selectReports() {
        // evento que emula la acción de clicar el enlace 'Informes y certificados', en
        // el apartado 'Ciudadanos' de la SS
        String reportToSelect = "Informes y certificados";
        if (personType != null && personType.compareTo("Ciudadanos") == 0) {
            report = reportToSelect;
        }
        byte option = 0;
        selectCertificationReport(option);
    }

    public void selectCertificationReport(byte opc) {
        // evento que emula la acción de seleccionar el informe o certificado concreto
        // que se desea obtener, tras presentar un menú con las dos opciones
        // disponibles. Utilizaremos un byte para indicar de qué informe se trata.
        if (institution != null && personType != null && personType.compareTo("Ciudadanos") == 0) {
            // TODO tramites[opc]

        }
        selectAuthMethod(opc);
    }

    public void selectAuthMethod(byte opc) {
        // TODO
    }

    public void enterNIF_PINobt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException,
            AnyMobileRegisteredException, ConnectException {
        // TODO
    }

    public void enterPIN(PINcode pin) throws NotValidPINException, NotAffiliatedException, ConnectException {
        // TODO
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
