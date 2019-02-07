package net.valorweb.beerstore.error;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.RequiredArgsConstructor;
import net.valorweb.beerstore.error.ErrorResponse.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private static final String NO_MESSAGE_AVALIABLE = "No message avaliable";
    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

    private final MessageSource apiErrorMessageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException exception, Locale locale) {

        List<ApiError> apiErrors = new ArrayList<>();

        exception.getConstraintViolations().iterator().forEachRemaining(constraintViolation -> apiErrors.add(toApiError(constraintViolation.getMessageTemplate(), locale)));

        ErrorResponse errorRespose = ErrorResponse.of(HttpStatus.BAD_REQUEST, apiErrors);
        return ResponseEntity.badRequest().body(errorRespose);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormat(InvalidFormatException exception, Locale locale){
        final String errorCode = "generic-1";
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final ErrorResponse errorResponse = ErrorResponse.of(status, toApiError(errorCode, locale, exception.getValue()));
        return ResponseEntity.badRequest().body(errorResponse);
    }

    private ApiError toApiError(String code, Locale locale, Object... args) {

        String message;

        try {

            message = apiErrorMessageSource.getMessage(code, args, locale);

        } catch (NoSuchMessageException e) {
            LOG.error("Could not find any message for {} code under {} locale", code, locale);
            message = NO_MESSAGE_AVALIABLE;
        }


        return new ApiError(code, message);
    }

}
