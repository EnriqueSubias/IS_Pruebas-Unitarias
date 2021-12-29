package src.services;

import java.util.Date;

import src.data.*;
import src.exceptions.*;

public interface CertificationAuthority { // External service that represents the
                                          // different trusted certification entities

        boolean sendPIN(Nif nif, Date vaID)
                        throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException,
                        ConnectException;

        // compartida
        public boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException;

        public boolean ckeckCredent(Nif nif, Password passw)
                        throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException,
                        ConnectException;

}
