package com.github.guillaumemilani.scoremanagerbackend.repository;

import com.github.guillaumemilani.scoremanagerbackend.model.Score;
import com.github.guillaumemilani.scoremanagerbackend.model.Score_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ScoreSpecs {
    public static Specification<Score> search(String search) {
        if (search == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder
                .or(
                        searchExpression(criteriaBuilder, root.get(Score_.title), search),
                        searchExpression(criteriaBuilder, root.get(Score_.composer), search),
                        searchExpression(criteriaBuilder, root.get(Score_.year).as(String.class), search)
                );
    }

    private static Predicate searchExpression(CriteriaBuilder criteriaBuilder, Expression<String> path, String search) {
        return criteriaBuilder.like(criteriaBuilder.lower(path), "%" + search.toLowerCase() + "%");
    }
}
