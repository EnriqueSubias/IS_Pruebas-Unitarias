package src.services;

import java.util.Date;

import src.data.*;
import src.exceptions.*;

public interface CertificationAuthority {
    // External service that represents the different trusted certification entities

    boolean sendPIN(Nif nif, Date vaID)
            throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException,
            ConnectException, NullPinException, NotValidPINException;

    // compartida
    boolean checkPIN(Nif nif, PINcode pin) throws NullPinException, ConnectException, IncorrectPinException;

    byte ckeckCredent(Nif nif, Password passw)
            throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException,
            ConnectException, NullPinException, NotValidPINException;

}
