package com.github.guillaumemilani.scoremanagerbackend.repository;

import com.github.guillaumemilani.scoremanagerbackend.model.*;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class PartSpecs {
    public static Specification<Part> hasPlayer(Player player) {
        if (player == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> {
            var plays = root.join(Part_.plays, JoinType.INNER);

            return criteriaBuilder.equal(plays.get(Play_.player), player);
        };
    }

    public static Specification<Part> hasSeason(Season season) {
        if (season == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> {
            var plays = root.join(Part_.plays, JoinType.INNER);

            return criteriaBuilder.equal(plays.get(Play_.season), season);
        };
    }

    public static Specification<Part> hasScore(Score score) {
        if (score == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Part_.score), score);
    }
}
