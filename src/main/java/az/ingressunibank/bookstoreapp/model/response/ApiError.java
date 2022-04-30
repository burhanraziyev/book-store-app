package az.ingressunibank.bookstoreapp.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ApiError {
    private final String code;
    private final String message;
    private final List<String> errors;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

    public ApiError(String message, String code, List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.code = code;
        this.errors = errors;
    }

    public ApiError(String message, String code) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.code = code;
        this.errors = new ArrayList<>();
    }
}
