package com.returdev.catalog_service.dtos.pagination;

import com.returdev.catalog_service.utils.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

/**
 * Data Transfer Object (DTO) for pagination requests.
 * This class is used to transfer pagination data between different layers of the application.
 */
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequestDTO {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PAGE_SIZE = 25;
    private static final String DEFAULT_SORT_DIRECTION = Sort.Direction.ASC.name();
    private static final String DEFAULT_ORDER_BY = OrderByValues.ID.entityPropertyName;

    private Integer page;
    private Integer pageSize;
    private String sortDirection;
    private String orderBy;

    /**
     * Gets the page number. If the page number is not set, returns the default page number.
     *
     * @return the page number
     */
    public int getPage() {
        return page != null ? Math.max(page, DEFAULT_PAGE) : DEFAULT_PAGE;
    }

    /**
     * Gets the page size. If the page size is not set, returns the default page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize != null ? Math.min(pageSize, DEFAULT_PAGE_SIZE) : DEFAULT_PAGE_SIZE;
    }

    /**
     * Gets the sort direction. If the sort direction is not set, returns the default sort direction.
     *
     * @return the sort direction
     */
    public Sort.Direction getSortDirection() {
        return Sort.Direction.fromString(sortDirection != null ? sortDirection : DEFAULT_SORT_DIRECTION);
    }

    /**
     * Gets the order by field. If the order by field is not set, returns the default order by field.
     *
     * @return the order by field
     */
    public String getOrderBy() {
        return orderBy != null ? OrderByValues.fromString(orderBy) : DEFAULT_ORDER_BY;
    }

    /**
     * Enum for order by values.
     * This enum is used to define the possible fields to order by.
     */
    @Getter
    @AllArgsConstructor
    private enum OrderByValues {

        ID("id"),
        NAME("name");

        private final String entityPropertyName;

        /**
         * Converts a string value to an OrderByValues enum.
         *
         * @param value the string value to convert
         * @return the corresponding OrderByValues enum
         */
        public static String fromString(String value) {
            return EnumUtil.fromString(OrderByValues.class, value).getEntityPropertyName();
        }

    }

}
