package src.services;

import java.net.ConnectException;

import src.data.Nif;

/**
 * External services involved in procedures from population
 */

public interface SS { // External service for Social Security Govern administration

    LaboralLifeDoc getLaboralLife(Nif nif) throws NotAffiliatedException, ConnectException;

    MemberAccreditationDoc getMembAccred(Nif nif) throws NotAffiliatedException, ConnectException;

}
