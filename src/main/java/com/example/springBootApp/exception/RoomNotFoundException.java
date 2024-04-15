package com.example.springBootApp.exception;

public class RoomNotFoundException extends Exception{
    public RoomNotFoundException(int number) {
        super(String.format("В отеле нет номера %d", number));
    }
}
