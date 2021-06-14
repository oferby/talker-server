package com.toga.netbrain.model.db.entities;

public class EntityExistException extends RuntimeException{

    public EntityExistException() {
    }

    public EntityExistException(String message) {
        super(message);
    }
}
