package com.returdev.catalog_service.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.returdev.utils_library.utils.EnumUtil;

/**
 * Enum representing different muscular groups in the system.
 * This enum is used to categorize muscles into specific groups such as chest, back, shoulders, legs, and abdomen.
 */
public enum MuscularGroup {

    CHEST,
    BACK,
    SHOULDERS,
    LEGS,
    ABDOMEN;

    @JsonCreator
    public static MuscularGroup fromString(String value) {
        return EnumUtil.fromString(MuscularGroup.class, value);
    }

}
