package com.epam.amazingServlet.domain.user;

public enum Position {

    DEVELOPER("developer"),
    MANAGER("manager"),
    CUSTOMER("customer");
    private final String value;

    Position(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
