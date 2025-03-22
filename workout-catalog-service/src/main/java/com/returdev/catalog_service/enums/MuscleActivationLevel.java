package com.returdev.catalog_service.enums;

import com.returdev.catalog_service.utils.EnumUtil;

/**
 * Enum representing different levels of muscle activation.
 * This enum is used to categorize the activation level of muscles involved in exercises.
 */
public enum MuscleActivationLevel {

    LOW,
    MEDIUM,
    HIGH;

    public static MuscleActivationLevel fromString(String value) {
        return EnumUtil.fromString(MuscleActivationLevel.class, value);
    }

}
