package az.ingressunibank.bookstoreapp.util;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QueryParam {

    private String key;
    private List<Value<Object>> values;
    private Operator operator;

    @Data
    public static class Value<T> {
        private T value;
    }

    public enum Operator {

        IN {
            @Override
            public Predicate buildPredicate(Root<?> root, QueryParam queryParam, CriteriaBuilder builder) {
                return builder.in(root.get(queryParam.getKey()))
                        .value(queryParam.getValues()
                                .stream()
                                .map(Value::getValue)
                                .map(value -> {
                                    if (!(value instanceof String)) {
                                        value = Long.valueOf(value.toString());
                                    }
                                    return value;
                                })
                                .collect(Collectors.toList()));
            }
        },

        LIKE {
            @Override
            public Predicate buildPredicate(Root<?> root, QueryParam queryParam, CriteriaBuilder builder) {
                return builder.like(builder.lower(root.get(queryParam.getKey())),
                        PERCENT + queryParam.getValues().get(0).getValue().toString().toLowerCase() + PERCENT);
            }
        },

        GREATER_THAN_OR_EQUAL {
            @Override
            public Predicate buildPredicate(Root<?> root, QueryParam queryParam, CriteriaBuilder builder) {
                if (root.get(queryParam.getKey()).getJavaType().isAssignableFrom(LocalDateTime.class)) {
                    return builder.greaterThanOrEqualTo(root.get(queryParam.getKey()).as(LocalDate.class),
                            LocalDate.parse(queryParam.getValues().get(0).getValue().toString()));
                }
                return builder.greaterThanOrEqualTo(root.get(queryParam.getKey()), queryParam.getValues().get(0).getValue().toString());
            }
        },
        EQUAL {
            @Override
            public Predicate buildPredicate(Root<?> root, QueryParam queryParam, CriteriaBuilder builder) {
                return builder.equal(root.get(queryParam.getKey()), queryParam.getValues().get(0).getValue());
            }
        };

        private static final String PERCENT = "%";

        public abstract Predicate buildPredicate(Root<?> root, QueryParam criteria, CriteriaBuilder builder);

    }
}
