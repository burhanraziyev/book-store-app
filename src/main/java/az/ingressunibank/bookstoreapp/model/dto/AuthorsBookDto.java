package az.ingressunibank.bookstoreapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorsBookDto {
    private Long id;
    private String title;
    private Integer quantity;
    private BigDecimal price;
    private Long userId;
    private LocalDateTime publishedDate;
}
