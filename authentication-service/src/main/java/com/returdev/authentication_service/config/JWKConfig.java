package com.returdev.authentication_service.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.returdev.authentication_service.config.properties.KeystoreProperties;
import com.returdev.authentication_service.exceptions.JWKException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Configuration class for JSON Web Key (JWK) management.
 * This class provides beans for JWK sources and JWT encoding, supporting both production and development environments.
 */
@Configuration
@RequiredArgsConstructor
public class JWKConfig {

    private final KeystoreProperties keystoreProperties;
    private static final Logger log = LoggerFactory.getLogger(JWKConfig.class);

    /**
     * Creates a JWK source for the production environment.
     * This method loads RSA keys from a Java KeyStore (JKS) file based on the current month and optionally the previous month.
     *
     * @return a JWKSource for the production environment
     */
    @Bean
    @Profile("prod")
    public JWKSource<SecurityContext> jwkSourceProd() {
        int daysUntilOldKeysExpire = 5;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("MM-yyyy");
        YearMonth currentMonth = YearMonth.now();
        String prefix = "rsa-";

        List<String> keyIds = new ArrayList<>();
        keyIds.add(prefix + currentMonth.format(timeFormatter));

        if (LocalDate.now().getDayOfMonth() <= daysUntilOldKeysExpire) {
            keyIds.add(prefix + currentMonth.minusMonths(1).format(timeFormatter));
        }

        return new ImmutableJWKSet<>(loadKeysFromJKS(keyIds));
    }

    /**
     * Loads RSA keys from a Java KeyStore (JKS) file based on the provided key IDs.
     *
     * @param keyIds the list of key IDs to load
     * @return a JWKSet containing the loaded keys
     * @throws JWKException if an error occurs while loading the keys
     */
    private JWKSet loadKeysFromJKS(List<String> keyIds) {
        List<JWK> keys = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(keystoreProperties.path())) {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(fis, keystoreProperties.password());

            for (String keyId : keyIds) {
                try {
                    Key key = keyStore.getKey(keyId, keystoreProperties.keyPassword());

                    if (!(key instanceof RSAPrivateKey privateKey)) {
                        throw new IllegalArgumentException("Key is not an RSA private key");
                    }

                    Certificate cert = keyStore.getCertificate(keyId);
                    RSAPublicKey publicKey = (RSAPublicKey) cert.getPublicKey();

                    RSAKey rsaKey = new RSAKey.Builder(publicKey)
                            .privateKey(privateKey)
                            .keyID(keyId)
                            .build();

                    keys.add(rsaKey);
                } catch (Exception e) {
                    log.error("Failed to load key for alias '{}': {}", keyId, e.getMessage());
                    throw new JWKException("Error loading key for alias: " + keyId, e);
                }
            }
        } catch (Exception e) {
            throw new JWKException("Error loading KeyStore in path: " + keystoreProperties.path(), e);
        }

        return new JWKSet(keys);
    }

    /**
     * Creates a JWK source for the development environment.
     * This method generates a new RSA key pair dynamically.
     *
     * @return a JWKSource for the development environment
     */
    @Bean
    @Profile("dev")
    public JWKSource<SecurityContext> jwkSourceDev() {
        KeyPair keyPair = generateRsaKey();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * Generates a new RSA key pair.
     *
     * @return a KeyPair containing the generated RSA keys
     * @throws IllegalStateException if the RSA algorithm is not available
     */
    private static KeyPair generateRsaKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a JWT encoder using the provided JWK source.
     *
     * @param jwkSource the JWK source to use for JWT encoding
     * @return a JwtEncoder instance
     */
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }
}

