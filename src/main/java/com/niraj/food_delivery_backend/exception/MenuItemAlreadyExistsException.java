package com.niraj.food_delivery_backend.exception;

public class MenuItemAlreadyExistsException extends RuntimeException{

    public MenuItemAlreadyExistsException(String message)
    {
        super(message);
    }

}
