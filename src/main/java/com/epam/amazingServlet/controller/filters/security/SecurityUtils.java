package com.epam.amazingServlet.controller.filters.security;

import com.epam.amazingServlet.domain.user.Position;
import com.epam.amazingServlet.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SecurityUtils {
    //
    public static boolean hasPublicPermission(HttpServletRequest req) {
        String urlPattern = getUrlPattern(req);
        return SecurityConfig.getPublicUrls().contains(urlPattern);
    }

    //this method check the permissions for registered users according to SecurityConfig class.
    //certain positions should see certain pages
    public static boolean hasInternalPermission(HttpServletRequest req) {
        if (isAuthorized(req)) {
            User user = (User) req.getSession().getAttribute("user");
            Position position = user.getPosition();
            List<String> permUrls = SecurityConfig.getRolePermissionMap().get(position);
            String urlPattern = getUrlPattern(req);
            return permUrls.contains(urlPattern);
        } else return false;
    }

    public static boolean isAuthorized(HttpServletRequest req) {
        return req.getSession(false) != null && req.getSession().getAttribute("user") != null;
    }

    private static String getUrlPattern(HttpServletRequest req) {
        return req.getRequestURI().substring(req.getContextPath().length()).replaceAll("/+$", "");

    }

}