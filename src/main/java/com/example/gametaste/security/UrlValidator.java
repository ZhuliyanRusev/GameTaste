package com.example.gametaste.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator {
    private static final String URL_PATTERN =
            "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9]+([\\-._][a-z0-9]+)*\\.[a-z]{2,6}"
                    + "(:[0-9]{1,5})?(/.*)?$";

    private static final Pattern pattern = Pattern.compile(URL_PATTERN);

    public static boolean validate(String url) {
        Matcher matcher = pattern.matcher(url);
        return !matcher.matches();
    }
}

