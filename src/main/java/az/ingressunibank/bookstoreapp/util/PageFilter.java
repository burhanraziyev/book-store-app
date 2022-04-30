package az.ingressunibank.bookstoreapp.util;

import lombok.Data;

import java.util.List;

@Data
public class PageFilter {
    private List<QueryParam> queryParams;
    private PageInfo pageInfo;
}
