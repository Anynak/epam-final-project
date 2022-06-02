package com.epam.amazingServlet.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String passToHash(String pass) {
        return DigestUtils.md5Hex(pass);
    }

    public static boolean validateEmail(String email) {
        String EMAIL_REGEX = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isNumber(String n) {
        String regex = "\\d+";
        return Pattern.matches(regex, n);
    }
}
