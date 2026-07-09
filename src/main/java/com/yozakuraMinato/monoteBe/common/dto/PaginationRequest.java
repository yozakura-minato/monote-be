package com.yozakuraMinato.monoteBe.common.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Set;

public record PaginationRequest(

        Integer size,
        Integer page,
        String order

) {

    private static final Set<Integer> ALLOWED_SIZES = Set.of(10, 20, 50, 100);

    private static final String INVALID_SIZE = "pagination.size.isInvalid";
    private static final String INVALID_PAGE = "pagination.page.isInvalid";

    public PaginationRequest {
        if(size == null) size = 10;
        if(!ALLOWED_SIZES.contains(size)) {
            throw new IllegalArgumentException(INVALID_SIZE);
        }

        if(page == null) page = 0;
        if(page < 0) {
            throw new IllegalArgumentException(INVALID_PAGE);
        }

        if(order == null || order.isBlank()) order = "id";
    }

    public Pageable toPageable() {
        return PageRequest.of(page, size, Sort.by(order));
    }

}