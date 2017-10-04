package com.dad.utility;

import java.util.UUID;


/**
 * Utility methods for UUID Operations
 */
public class UUIDUtility {
    public UUIDUtility() {
    }

    public static String generateClientId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
