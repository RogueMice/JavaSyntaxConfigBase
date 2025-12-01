package com.Server.ManageStudent.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class HashPasswordUtil {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    // Hash
    public static String hash(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    // Check
    public static boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}
