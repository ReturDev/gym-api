package com.returdev.catalog_service.utilities;

import com.returdev.catalog_service.exceptions.InvalidEnumValueException;

import java.util.Arrays;
import java.util.List;


/**
 * Utility class for working with enums.
 * This class provides methods for converting strings to enum values and retrieving valid enum values.
 */
public final class EnumUtil {

    /**
     * Converts a string to the corresponding enum value.
     *
     * @param <E> the type of the enum
     * @param enumClass the class of the enum
     * @param value the string value to convert
     * @return the corresponding enum value
     * @throws InvalidEnumValueException if the string does not match any enum value
     */
    public static <E extends Enum<E>> E fromString(Class<E> enumClass, String value) {
        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(
                    value,
                    "", // TODO: Add message resource
                    getValidValues(enumClass.getEnumConstants())
            );
        }
    }

    /**
     * Retrieves a string representation of valid enum values.
     *
     * @param <E> the type of the enum
     * @param enumConstants the array of enum constants
     * @return a string representation of valid enum values
     */
    private static <E extends Enum<E>> String getValidValues(E[] enumConstants) {
        List<String> names = Arrays.stream(enumConstants)
                .map(E::name)
                .toList();
        return String.join(", ", names.subList(0, names.size() - 1)) + " or " + names.getLast();
    }
}
