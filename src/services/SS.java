package src.services;

import src.exceptions.*;

import src.data.Nif;

/**
 * External services involved in procedures from population
 */

public interface SS { // External service for Social Security Govern administration

    LaboralLifeDoc getLaboralLife(Nif nif) throws NotAffiliatedException, ConnectException;

    MemberAccreditationDoc getMembAccred(Nif nif) throws NotAffiliatedException, ConnectException, NullNifException, NullAccredNumberException, NullValDateException, NullPathException, NullFileException;

    // AccredNumb getNumberAccred(Nif nif) throws NotAffiliatedException,
    // ConnectException;
}
