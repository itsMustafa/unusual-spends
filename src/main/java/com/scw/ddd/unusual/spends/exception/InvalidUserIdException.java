package com.scw.ddd.unusual.spends.exception;

public class InvalidUserIdException extends RuntimeException {

    public InvalidUserIdException(String userId) {
        super("Invalid UserId: " + userId);
    }
}
