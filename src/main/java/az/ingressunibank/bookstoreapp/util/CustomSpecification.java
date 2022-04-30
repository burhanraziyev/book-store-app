package az.ingressunibank.bookstoreapp.util;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Data
public class CustomSpecification<T> implements org.springframework.data.jpa.domain.Specification<T> {

    private final List<QueryParam> queryParamList;

    public CustomSpecification(List<QueryParam> queryParamList) {
        this.queryParamList = queryParamList;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (CollectionUtils.isEmpty(queryParamList)) {
            return null;
        }
        Predicate[] predicates = queryParamList.stream()
                .map(queryParam -> queryParam.getOperator().buildPredicate(root, queryParam, builder))
                .toArray(Predicate[]::new);

        return builder.and(predicates);
    }

}

