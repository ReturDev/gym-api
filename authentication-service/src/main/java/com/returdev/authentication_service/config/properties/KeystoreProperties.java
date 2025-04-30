package com.returdev.authentication_service.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for the keystore.
 * This class is annotated with `@ConfigurationProperties` to bind properties
 * with the prefix "keystore" from the application's configuration file.
 * It uses a record to define immutable properties.
 *
 * @param path The file path to the keystore.
 * @param password The password for accessing the keystore.
 * @param keyPassword The password for accessing the key within the keystore.
 */
@ConfigurationProperties(prefix = "keystore")
public record KeystoreProperties(
        String path,
        char[] password,
        char[] keyPassword
) {}
