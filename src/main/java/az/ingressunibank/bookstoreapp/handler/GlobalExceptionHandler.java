package az.ingressunibank.bookstoreapp.handler;

import az.ingressunibank.bookstoreapp.exception.ResourceNotFoundException;
import az.ingressunibank.bookstoreapp.exception.TokenExpiredException;
import az.ingressunibank.bookstoreapp.model.response.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> field.getField() + ", " + field.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> globalErrors = ex.getBindingResult()
                .getGlobalErrors()
                .stream()
                .map(field -> field.getObjectName() + ", " + field.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> errors = new ArrayList<>();
        errors.addAll(globalErrors);
        errors.addAll(fieldErrors);

        ApiError response = new ApiError(ex.getLocalizedMessage(), BAD_REQUEST.getReasonPhrase(), errors);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError response = new ApiError(ex.getLocalizedMessage(), BAD_REQUEST.getReasonPhrase());
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError err = new ApiError(ex.getLocalizedMessage(), BAD_REQUEST.getReasonPhrase(),List.of("Something went wrong"));
        return new ResponseEntity<>(err, BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(ex.getLocalizedMessage(), BAD_REQUEST.getReasonPhrase(), details);
        return new ResponseEntity<>(err, BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {

        ApiError err = new ApiError(ex.getLocalizedMessage(), NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<>(err, NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(
            UsernameNotFoundException ex,
            WebRequest request) {

        ApiError err = new ApiError(ex.getLocalizedMessage(), NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<>(err, NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(
            BadCredentialsException ex,
            WebRequest request) {

        ApiError err = new ApiError(ex.getLocalizedMessage(), UNAUTHORIZED.getReasonPhrase());

        return new ResponseEntity<>(err, UNAUTHORIZED);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Object> handleDisabledException(
            DisabledException ex,
            WebRequest request) {

        ApiError err = new ApiError(ex.getLocalizedMessage(), UNAUTHORIZED.getReasonPhrase());

        return new ResponseEntity<>(err, UNAUTHORIZED);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException(
            TokenExpiredException ex,
            WebRequest request) {

        ApiError err = new ApiError(ex.getLocalizedMessage(), NOT_ACCEPTABLE.getReasonPhrase());

        return new ResponseEntity<>(err, NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(
            AccessDeniedException ex,
            WebRequest request) {

        ApiError err = new ApiError(ex.getLocalizedMessage(), FORBIDDEN.getReasonPhrase());

        return new ResponseEntity<>(err, FORBIDDEN);
    }

}
