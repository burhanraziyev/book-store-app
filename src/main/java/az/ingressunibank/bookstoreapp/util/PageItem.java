package az.ingressunibank.bookstoreapp.util;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PageItem<T> {
    private List<T> content;
    private Boolean isSorted;
    private Boolean hasNextPage;
    private Boolean hasPrevPage;
    private Integer pageNumber;
    private Integer totalPages;
    private Long totalElements;
}
