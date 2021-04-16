package com.github.ericomonteiro.smartstock.config.error;


import com.github.ericomonteiro.smartstock.config.error.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.github.ericomonteiro.smartstock.config.error.ErrorResponse.ApiError;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static com.github.ericomonteiro.smartstock.config.error.ErrorKeys.General.ACCESS_DENIED;
import static com.github.ericomonteiro.smartstock.config.error.ErrorKeys.General.INTERNAL_SERVER_ERROR;
import static com.github.ericomonteiro.smartstock.config.error.ErrorKeys.General.NOT_FOUND;
import static java.util.stream.Collectors.toList;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {
    private static final String NO_MESSAGE_AVAILABLE = "No message available";
    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

    private final MessageSource apiErrorMessageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNotValidException(MethodArgumentNotValidException exception
            , Locale locale) {
        Stream<ObjectError> errors = exception.getBindingResult().getAllErrors().stream();

        List<ErrorResponse.ApiError> apiErrors = errors
                .map(ObjectError::getDefaultMessage)
                .map(code -> toApiError(code, locale))
                .collect(toList());

        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exception, Locale locale) {
        ApiError apiError = toApiError(ACCESS_DENIED, locale);

        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.UNAUTHORIZED, apiError);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ResourceNotFoundException exception
            , Locale locale) {

        LOG.error("handleNotFoundException", exception);
        HttpStatus status = HttpStatus.NOT_FOUND;

        Object[] errorArgs = {exception.getEntity(), exception.getKey()};
        ApiError apiError = toApiError(NOT_FOUND, locale, errorArgs);
        ErrorResponse errorResponse = ErrorResponse.of(status, apiError);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedError(Exception exception
            , Locale locale) {
        LOG.error("handleUnexpectedError", exception);

        ApiError apiError = toApiError(INTERNAL_SERVER_ERROR, locale);

        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, apiError);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ApiError toApiError(String code, Locale locale) {
        return toApiError(code, locale, (Object) null);
    }


    private ErrorResponse.ApiError toApiError(String code, Locale locale, Object... args) {
        String message;
        try {
            message = apiErrorMessageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            LOG.error("Could not find any message for {} code under {} locale", code, locale);
            message = NO_MESSAGE_AVAILABLE;
        }

        return new ErrorResponse.ApiError(code, message);
    }
}
