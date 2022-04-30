package az.ingressunibank.bookstoreapp.util;

import lombok.Data;

@Data
public class PageInfo {
    private Integer pageNumber;
    private Integer size;
    private SortBy sortBy;
}
