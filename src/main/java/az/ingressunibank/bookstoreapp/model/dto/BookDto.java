package az.ingressunibank.bookstoreapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private Integer quantity;
    private BigDecimal price;
    private Long publisherId;
    private Set<BooksAuthorDto> booksAuthorSet;
    private LocalDateTime publishedDate;
}
