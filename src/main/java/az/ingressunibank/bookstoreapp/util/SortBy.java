package az.ingressunibank.bookstoreapp.util;

import lombok.Data;

import static org.springframework.data.domain.Sort.Direction;

@Data
public class SortBy {
    private String key;
    private Direction direction;
}
