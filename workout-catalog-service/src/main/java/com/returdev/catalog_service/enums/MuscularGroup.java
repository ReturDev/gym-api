package com.returdev.catalog_service.enums;

import com.returdev.catalog_service.utilities.EnumUtil;

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

    public static MuscularGroup fromString(String value) {
        return EnumUtil.fromString(MuscularGroup.class, value);
    }

}
