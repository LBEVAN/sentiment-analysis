package io.github.lbevan.sentiment.service.Util;

import java.util.UUID;

/**
 * Encapsulates the generation of the UUID.
 */
public class UUIDGenerator {

    /**
     * Generate a unique Id.
     *
     * @return UUID
     */
    public static UUID generateUUID() {
        return UUID.randomUUID();
    }
}
