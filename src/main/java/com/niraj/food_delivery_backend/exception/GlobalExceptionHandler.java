package com.niraj.food_delivery_backend.exception;


import com.niraj.food_delivery_backend.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleRestaurantNotFoundException(RestaurantNotFoundException exception) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                "Restaurant Not Found",
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MenuItemAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleMenuItemAlreadyExistsException(MenuItemAlreadyExistsException exception)
    {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                "Menu Item Already Exists",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleMenuItemNotFoundException(MenuItemNotFoundException exception)
    {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                "Menu Item Not Found",
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

}
