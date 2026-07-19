package com.yozakuraMinato.monoteBe.common.payload;

import com.yozakuraMinato.monoteBe.common.shared.CommonMessage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Set;

public record PaginationRequest(

        Integer size,
        Integer page,
        String sort

) {

    private static final Set<Integer> ALLOWED_SIZES = Set.of(10, 20, 50, 100);
    private static final int DEFAULT_SIZE = 10;
    private static final int DEFAULT_PAGE = 0;
    private static final String DEFAULT_SORT = "id";


    public PaginationRequest {
        if(size == null) size = DEFAULT_SIZE;
        if(!ALLOWED_SIZES.contains(size)) {
            throw new IllegalArgumentException(CommonMessage.Pagination.HAS_INVALID_SIZE);
        }

        if(page == null) page = DEFAULT_PAGE;
        if(page < 0) {
            throw new IllegalArgumentException(CommonMessage.Pagination.HAS_INVALID_PAGE);
        }

        if(sort == null || sort.isBlank()) sort = DEFAULT_SORT;
    }

    public Pageable toPageable() {
        return PageRequest.of(page, size, Sort.by(sort));
    }

}