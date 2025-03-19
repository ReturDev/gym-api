package com.returdev.catalog_service.mappers;

import com.returdev.catalog_service.dtos.content.ContentResponseDTO;
import com.returdev.catalog_service.dtos.pagination.PaginationRequestDTO;
import com.returdev.catalog_service.dtos.pagination.PaginationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Interface for mapping between different model types.
 *
 * @param <R> the response DTO type
 * @param <T> the request DTO type
 * @param <E> the entity type
 */
public interface ModelMapper<R, T, E> {

    /**
     * Converts an entity to a response DTO.
     *
     * @param entity the entity to convert
     * @return the response DTO
     * @throws IllegalArgumentException if the entity is invalid or cannot be mapped
     */
    R mapToResponseDto(E entity);

    /**
     * Converts a request DTO to an entity.
     *
     * @param requestDto the request DTO to convert
     * @return the entity
     * @throws IllegalArgumentException if the request DTO is invalid or cannot be mapped
     */
    E mapToEntity(T requestDto);

    /**
     * Converts an entity to a content response DTO.
     *
     * @param entity the entity to convert
     * @return the content response DTO
     */
    default ContentResponseDTO<R> toContentResponse(E entity) {
        return new ContentResponseDTO<>(mapToResponseDto(entity));
    }

/**
 * Converts a list of entities to a content response DTO containing a list of response DTOs.
 *
 * @param entities the list of entities to convert
 * @return the content response DTO containing a list of response DTOs
 */
default ContentResponseDTO<List<R>> toContentResponse(List<E> entities) {
    return new ContentResponseDTO<>(
            entities.stream().map(this::mapToResponseDto).toList()
    );
}

    /**
     * Converts a page of entities to a pagination response DTO.
     *
     * @param page the page of entities to convert
     * @return the pagination response DTO
     */
    default PaginationResponseDTO<R> toPaginationResponse(Page<E> page) {
        return new PaginationResponseDTO<>(
                page.getContent().stream().map(this::mapToResponseDto).toList(),
                toPageInfo(page)
        );
    }

    /**
     * Converts a pagination request DTO to a pageable object.
     *
     * @param pagination the pagination request DTO
     * @return the pageable object
     */
    default Pageable toPageable(PaginationRequestDTO pagination) {
        Sort sort = Sort.by(pagination.getSortDirection(), pagination.getOrderBy());
        return PageRequest.of(
                pagination.getPage(),
                pagination.getPageSize(),
                sort
        );
    }

    /**
     * Converts a page of entities to page information.
     *
     * @param page the page of entities
     * @return the page information
     */
    private PaginationResponseDTO.PageInfo toPageInfo(Page<E> page) {
        return new PaginationResponseDTO.PageInfo(
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber()
        );
    }

}