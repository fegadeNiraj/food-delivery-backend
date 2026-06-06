package com.niraj.food_delivery_backend.exception;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    /*
     * RestaurantNotFoundExceptˀion should extend RuntimeException because it represents a business/application exception. We don't want every service and controller method to be forced to catch or declare it. Instead, Spring's global exception handling mechanism (@ControllerAdvice) can handle it centrally.
     * */

}
