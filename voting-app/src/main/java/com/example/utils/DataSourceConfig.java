package com.example.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String host;

    @Value("${spring.datasource.database}")
    private String database;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${db.secret}")
    private String dbSecret;

    private static final String ALGORITHM = "PBEWithMD5AndDES";
    private static final int ITERATIONS = 1000;

    public static String decrypt(String encryptedData, String key) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);
        encryptor.setAlgorithm(ALGORITHM);
        encryptor.setKeyObtentionIterations(ITERATIONS);
        encryptor.setSaltGenerator(new RandomSaltGenerator());
        return encryptor.decrypt(encryptedData);
    }

    @Bean
    public DataSource dataSource() {
        System.out.println(dbSecret);
        System.out.println(decrypt("LNo1//WwBbIPGInuPgX9Lg==", dbSecret));
        // System.out.println("====================================================================================================================================");
        String jdbcUrl = String.format("%s/%s", host, decrypt(database, dbSecret));

        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(decrypt(username, dbSecret))
                .password(decrypt(password, dbSecret))
                .driverClassName(driverClassName)
                .build();
    }
}
