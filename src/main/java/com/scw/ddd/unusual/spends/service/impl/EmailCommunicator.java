package com.scw.ddd.unusual.spends.service.impl;

import com.scw.ddd.unusual.spends.service.Communicator;

public class EmailCommunicator implements Communicator {
    @Override
    public boolean communicate(String recipientContact, String subject, String message) {
        try {
            System.out.println("\nTo: " + recipientContact);
            System.out.println("\nSubject: " + subject);
            System.out.println("\nMessage: \n");
            System.out.println(message + "\n");
            System.out.println("Sent\n");
            return true;
        } catch (Exception ex) {
            System.out.println("Exception caught: " +  ex.getMessage());
            ex.fillInStackTrace();
            return false;
        }
    }
}
