package com.scw.ddd.unusual.spends.service;

public interface Communicator {

    boolean communicate(String recipientContact, String subject, String message);

}
