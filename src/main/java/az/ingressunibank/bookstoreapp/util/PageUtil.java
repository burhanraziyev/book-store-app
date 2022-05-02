package az.ingressunibank.bookstoreapp.util;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Data
public final class PageUtil {

    private PageUtil() {

    }


    public static <T> PageItem<T> convert(Page<T> page) {
        return PageItem.<T>builder()
                .hasPrevPage(page.hasPrevious())
                .content(page.getContent())
                .isSorted(page.getSort().isSorted())
                .hasNextPage(page.hasNext())
                .pageNumber(page.getNumber())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    public static Pageable of(PageInfo pageInfo) {
        return Optional.ofNullable(pageInfo)
                .map(p -> PageRequest.of(p.getPageNumber(), p.getSize(), bindSort(p.getSortBy())))
                .map(Pageable.class::cast)
                .orElse(Pageable.unpaged());
    }

    private static Sort bindSort(SortBy sortBy) {
        return Optional.ofNullable(sortBy)
                .map(s -> Sort.by(s.getDirection(), s.getKey()))
                .orElse(Sort.unsorted());
    }

    public static void bindParams(List<QueryParam> params,
                                  String key,
                                  QueryParam.Operator operator,
                                  Object objectValue) {
        QueryParam param = new QueryParam();
        param.setKey(key);
        param.setOperator(operator);
        QueryParam.Value<Object> value = new QueryParam.Value<>();
        value.setValue(objectValue);
        param.setValues(List.of(value));
        params.add(param);
    }

}
