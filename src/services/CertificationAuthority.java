package src.services;

import java.util.Date;

import src.data.*;
import src.exceptions.*;

public interface CertificationAuthority { // External service that represents the
                                          // different trusted certification entities

    boolean sendPIN(Nif nif, Date vaID)
            throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException,
            ConnectException;
    // proporciona las credenciales del ciudadano en el sistema Cl@ve PIN, a la vez
    // que solicita la emisión del PIN para completar su identificación. Retorna un
    // booleano indicando si todo es correcto.

    // compartida
    boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException;

    byte ckeckCredent(Nif nif, Password passw)
            throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException,
            ConnectException;

}
