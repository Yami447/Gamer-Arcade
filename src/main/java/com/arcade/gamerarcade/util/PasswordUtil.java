package com.arcade.gamerarcade.util;

import java.util.regex.Pattern;

public class PasswordUtil {
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&*()\\-+=])[A-Za-z\\d@#$%^&*()\\-+=]{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    public static boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
