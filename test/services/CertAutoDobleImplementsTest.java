package test.services;

import java.util.*;

import org.junit.jupiter.api.DisplayName;

import src.data.*;
import src.exceptions.*;
import src.services.*;

public class CertAutoDobleImplementsTest implements CertificationAuthority {

    Map<Nif, Date> databaseValID;
    Map<Nif, String> databaseAccredPincode;
    Map<Nif, PINcode> databasePinCodes;
    Map<Nif, Password> databaseCredencials;

    public CertAutoDobleImplementsTest()
            throws AlreadyAddedException, NullPasswordException, NotValidPasswordException,
            NullNifException, NotValidNifException {
        databaseAccredPincode = new HashMap<>();
        databasePinCodes = new HashMap<>();
        databaseCredencials = new HashMap<>();
        databaseValID = new HashMap<>();
        addsToDatabase();
    }

    @Override
    @DisplayName("Comprobación del envio del pin code por parte de Cl@ve")
    public boolean sendPIN(Nif nif, Date vaID) throws NifNotRegisteredException, IncorrectValDateException,
            AnyMobileRegisteredException, ConnectException, NullPinException, NotValidPINException {
        if (databaseValID.containsKey(nif)) {
            Date currentDate = new java.util.Date();
            if (!currentDate.after(vaID)) {
                String telmobile = databaseAccredPincode.get(nif);
                if (telmobile != null) {
                    PINcode pin = new PINcode("000");
                    databasePinCodes.put(nif, pin);
                    return true;
                } else {
                    throw new AnyMobileRegisteredException();
                }
            } else {
                throw new IncorrectValDateException();
            }
        } else {
            throw new NifNotRegisteredException();
        }
    }

    @Override
    @DisplayName("Comprobación de que el PINcode es correcto")
    public boolean checkPIN(Nif nif, PINcode pin) throws NullPinException, ConnectException, IncorrectPinException {

        if (databasePinCodes.containsKey(nif)) {
            if (databasePinCodes.get(nif).equals(pin)) {
                return true;
            } else {
                throw new IncorrectPinException();
            }
        } else {
            throw new NullPinException();
        }
    }

    @Override
    @DisplayName("Comprobación de las credenciales")
    public byte ckeckCredent(Nif nif, Password passw)
            throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException,
            NullPinException, NotValidPINException {

        if (databaseCredencials.containsKey(nif)) {
            Password passObjective = databaseCredencials.get(nif);
            if (passObjective.equals(passw)) {
                if (databaseAccredPincode.containsKey(nif)) {
                    String telmobile = databaseAccredPincode.get(nif);
                    if (telmobile != null) {
                        PINcode pin = new PINcode("000");
                        databasePinCodes.put(nif, pin);
                    } else {
                        throw new AnyMobileRegisteredException();
                    }
                    return 1; // Significa que tienes methodo reforzado pon PINcode
                } else {
                    return 0; // no tiene refuerzo
                }
            } else {
                throw new NotValidCredException();
            }
        } else {
            throw new NifNotRegisteredException();
        }
    }

    @DisplayName("Comprobación de las credenciales")
    public void addsToDatabase() throws NullPasswordException, NotValidPasswordException,
            IllegalArgumentException, NullNifException, NotValidNifException {
        Nif user1 = new Nif("12345678A");
        Nif user2 = new Nif("12345678B");
        Nif user3 = new Nif("12345678C");
        Nif user4 = new Nif("12345678D");
        databaseAccredPincode.put(user1, "123456781");
        databaseAccredPincode.put(user2, "123456782");
        databaseAccredPincode.put(user3, "123456783");
        databaseAccredPincode.put(user4, null);
        Calendar cal = Calendar.getInstance();
        cal.set(2021, Calendar.JANUARY, 04); // Year, month and day of month
        Date date1 = cal.getTime();
        databaseValID.put(user1, date1);
        databaseValID.put(user2, date1);
        databaseValID.put(user3, date1);
        databaseValID.put(user4, date1);
        Password pass1 = new Password("Pepito01");
        databaseCredencials.put(user1, pass1);
        databaseCredencials.put(user4, pass1);
    }

}