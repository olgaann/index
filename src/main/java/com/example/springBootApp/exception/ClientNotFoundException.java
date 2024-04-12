package com.example.springBootApp.exception;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException(long id) {
        super(String.format("Не найден клиент с id %d", id));
    }
}
