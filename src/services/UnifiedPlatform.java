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
    String tramites[] = { "vida laboral", "numero seguridad social" };

    public void processSearcher() {
        System.out.println("Introduce el tramite a buscar: ");
        Scanner sc = new Scanner(System.in); // Se crea el lector
        String keyWord = sc.nextLine();
        try {
            enterKeyWords(keyWord);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void enterKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        System.out.println("Buscando AAPP...");
        for (int i = 0; i < tramites.length; i++) {
            if (tramites[i].compareTo(keyWord.toLowerCase()) == 0) {
                selectSS();
            }
        }
        throw new AnyKeyWordProcedureException();
    }

    public void selectSS() {
        System.out.println("SS Seleccionada");
        selectCitizens();
    }

    public void selectCitizens() {
        System.out.println("Citizens Seleccionado");
        selectReports();
    }

    public void selectReports() {
        //selectCertificationReport();
    }

    public void selectCertificationReport(byte opc) {
    }

    public void selectAuthMethod(byte opc) {
    }

    public void enterNIF_PINobt(Nif nif, Date valDate) throws NifNotRegisteredException, IncorrectValDateException,
            AnyMobileRegisteredException, ConnectException {
    }

    public void enterPIN(PINcode pin) throws NotValidPINException, NotAffiliatedException, ConnectException {
    }

    private void printDocument() throws BadPathException, PrintingException {
    }

    private void downloadDocument() {
    }

    private void selectPath(DocPath path) throws BadPathException {
    }

    // Other operations
    private String searchKeyWords(String keyWord) throws AnyKeyWordProcedureException {
        return new String("A");
    }

    private void OpenDocument(DocPath path) throws BadPathException {
    }

    private void printDocument(DocPath path) throws BadPathException, PrintingException {
    }

    private void downloadDocument(DocPath path) throws BadPathException {
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
