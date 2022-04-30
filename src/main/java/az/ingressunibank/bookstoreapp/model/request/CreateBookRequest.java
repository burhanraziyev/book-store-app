package az.ingressunibank.bookstoreapp.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
public class CreateBookRequest {
    @NotNull
    private Set<Long> authorId;
    @NotNull
    private Long publisherId;
    @NotNull @NotBlank @NotEmpty
    private String title;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal price;
}
