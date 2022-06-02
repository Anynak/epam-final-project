package com.epam.amazingServlet.controller.filters.security;

import com.epam.amazingServlet.domain.user.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityConfig {

    protected static List<String> getPublicUrls() {
        return publicUrls;
    }

    //a map with acceptable urls for each position(role)
    protected static Map<Position, List<String>> getRolePermissionMap() {
        return rolePermissionMap;
    }

    private static final Map<Position, List<String>> rolePermissionMap = new HashMap<>();
    private static List<String> urlsForAuthorized = null;
    private static List<String> publicUrls = null;


    static {
        setPublicPermissions();

        setDeveloperPermissions();
        setManagerPermissions();
        setCustomerPermissions();
    }

    private static void setPublicPermissions() {
        publicUrls = new ArrayList<>();
        publicUrls.add("/auth");
        publicUrls.add("/register");
        publicUrls.add("/test");
    }

    private static void setDeveloperPermissions() {
        List<String> DeveloperUrlPatterns = new ArrayList<>(getUrlsForAuthorized());
        DeveloperUrlPatterns.add("/setBill");
        DeveloperUrlPatterns.add("/currentWorks");
        rolePermissionMap.put(Position.DEVELOPER, DeveloperUrlPatterns);
    }

    private static void setManagerPermissions() {
        List<String> ManagerUrlPatterns = new ArrayList<>(getUrlsForAuthorized());
        ManagerUrlPatterns.add("/setProject");
        ManagerUrlPatterns.add("/projectList");
        ManagerUrlPatterns.add("/editProject");
        rolePermissionMap.put(Position.MANAGER, ManagerUrlPatterns);
    }

    private static void setCustomerPermissions() {
        List<String> CustomerUrlPatterns = new ArrayList<>(getUrlsForAuthorized());
        CustomerUrlPatterns.add("/technicalTaskList");
        CustomerUrlPatterns.add("/technicalTask");
        CustomerUrlPatterns.add("/addTechnicalTask");
        CustomerUrlPatterns.add("/setWork");
        CustomerUrlPatterns.add("/work");
        rolePermissionMap.put(Position.CUSTOMER, CustomerUrlPatterns);
    }

    private static void setCorporatePermissions() {
        urlsForAuthorized = new ArrayList<>();
        urlsForAuthorized.add("");
        urlsForAuthorized.add("/logout");
        urlsForAuthorized.add("/myProfile");
        urlsForAuthorized.add("/editProfile");
    }


    private static List<String> getUrlsForAuthorized() {
        if (urlsForAuthorized == null) {
            setCorporatePermissions();
        }
        return urlsForAuthorized;
    }

}
