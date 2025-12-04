package com.taa.deneme0.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    // simple in-memory token store (token -> username)
    private final Map<String, String> tokens = new ConcurrentHashMap<>();
    private final SecureRandom random = new SecureRandom();

    public String login(String username, String password) {
        // For demo purposes accept any username/password where password equals "password"
        if (username == null || password == null) return null;
        if (!"password".equals(password)) return null;
        String token = generateToken();
        tokens.put(token, username);
        return token;
    }

    public boolean validate(String token) {
        return token != null && tokens.containsKey(token);
    }

    public void logout(String token) {
        if (token != null) tokens.remove(token);
    }

    private String generateToken() {
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}

