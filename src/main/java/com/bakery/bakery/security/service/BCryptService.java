package com.bakery.bakery.security.service;

public interface BCryptService {
    String encryptPassword(String password);
    Boolean verifyPassword(String password, String encrypt);
}
