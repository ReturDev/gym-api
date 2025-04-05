package com.returdev.catalog_service.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.returdev.utils_library.utils.EnumUtil;

/**
 * Enum representing different levels of muscle activation.
 * This enum is used to categorize the activation level of muscles involved in exercises.
 */
public enum MuscleActivationLevel {

    LOW,
    MEDIUM,
    HIGH;

    @JsonCreator
    public static MuscleActivationLevel fromString(String value) {
        return EnumUtil.fromString(MuscleActivationLevel.class, value);
    }

}
