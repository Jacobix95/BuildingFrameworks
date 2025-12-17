package com.example.core.config;

public record Config(
        String baseUrl,
        String browser,
        boolean headless,
        String userEmail,
        String userPassword,
        boolean highlight
) {
}

