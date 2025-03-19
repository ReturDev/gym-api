package com.returdev.catalog_service.dtos.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A generic pagination response DTO (Data Transfer Object) that encapsulates paginated data and page information.
 *
 * @param <T> the type of the content in the pagination response
 */
public record PaginationResponseDTO<T>(
        @JsonProperty("data") List<T> content,
        @JsonProperty("page_info") PageInfo pageInfo
) {

    /**
     * A record that holds information about the pagination details.
     *
     * @param pageSize the number of elements per page
     * @param totalElements the total number of elements
     * @param totalPages the total number of pages
     * @param pageNumber the current page number
     */
    public record PageInfo(
            @JsonProperty("page_size") int pageSize,
            @JsonProperty("total_elements") long totalElements,
            @JsonProperty("total_pages") long totalPages,
            @JsonProperty("page_number") int pageNumber
    ){}

}
