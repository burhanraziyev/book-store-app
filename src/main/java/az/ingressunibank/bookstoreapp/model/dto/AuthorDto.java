package az.ingressunibank.bookstoreapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private Long id;
    private String fullName;
    private Set<AuthorsBookDto> authorsBookSet;
    private LocalDateTime birthOfDate;
}
