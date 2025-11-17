package com.mateusbosquetti.agendaja.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    private static final String URL = System.getenv("MINIO_URL");
    private static final String ACCESS_KEY = System.getenv("MINIO_ACCESS_KEY");
    private static final String SECRET_KEY = System.getenv("MINIO_SECRET_KEY");


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(URL)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
    }

}
