package az.ingressunibank.bookstoreapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksAuthorDto {
    private Long id;
    private String fullName;
    private LocalDateTime birthOfDate;
}
