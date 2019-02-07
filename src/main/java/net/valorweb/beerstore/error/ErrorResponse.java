package net.valorweb.beerstore.error;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = ANY)
public class ErrorResponse {

    private final int statusCode;
    private final List<ApiError> errors;

    static ErrorResponse of(HttpStatus status, List<ApiError> errors){
        return new ErrorResponse(status.value(), errors);
    }

    static ErrorResponse of(HttpStatus status, ApiError error){
        return of(status, Collections.singletonList(error));
    }

    @RequiredArgsConstructor
    @JsonAutoDetect(fieldVisibility = ANY)
    static class ApiError {
        private final String code;
        private final String message;

    }
}
