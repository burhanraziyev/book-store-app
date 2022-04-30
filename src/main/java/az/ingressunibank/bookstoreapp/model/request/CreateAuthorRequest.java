package az.ingressunibank.bookstoreapp.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CreateAuthorRequest {
    @NotNull @NotEmpty @NotBlank
    private String fullName;

    @NotNull
    private LocalDateTime birthOfDate;
}
