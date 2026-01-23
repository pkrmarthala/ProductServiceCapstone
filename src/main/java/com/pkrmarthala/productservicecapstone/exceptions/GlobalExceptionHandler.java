package com.pkrmarthala.productservicecapstone.exceptions;

import com.pkrmarthala.productservicecapstone.dtos.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse buildErrorResponse(String message, HttpStatus status, WebRequest request) {
        return new ErrorResponse(
                "failure",
                message,
                request.getDescription(false).replace("uri=", ""),
                LocalDateTime.now()
        );
    }


    @ExceptionHandler(NullPointerException.class)
    public ErrorResponse handleNullPointerExceptions() {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus("Failure!");
        errorResponse.setMessage("Null Pointer Exception occurred");

        return errorResponse;
    }

    // Validation errors: @Min, @NotNull, @Valid body fields etc.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        return new ResponseEntity<>(
                buildErrorResponse(errorMessage, HttpStatus.BAD_REQUEST, request),
                HttpStatus.BAD_REQUEST
        );
    }

    // PathVariable and RequestParam validation errors
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {

        return new ResponseEntity<>(
                buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request),
                HttpStatus.BAD_REQUEST
        );
    }

    // Business errors
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(
            ProductNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(
                buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request),
                HttpStatus.NOT_FOUND
        );
    }

    // Illegal input errors (other than @Valid/@Min)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {

        return new ResponseEntity<>(
                buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request),
                HttpStatus.BAD_REQUEST
        );
    }

    // Catch-all fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(
            Exception ex, WebRequest request) {

        return new ResponseEntity<>(
                buildErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR, request),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
