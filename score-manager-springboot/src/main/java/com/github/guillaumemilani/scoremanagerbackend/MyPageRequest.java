package com.github.guillaumemilani.scoremanagerbackend;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public interface MyPageRequest {
    static PageRequest of(Integer pageNumber, Integer pageSize, String sort, String direction) {
        if (sort == null || direction == null) {
            return PageRequest.of(pageNumber, pageSize);
        }

        return PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(direction), sort));
    }
}
