package com.epam.amazingServlet.domain.user;

import java.math.BigDecimal;

public enum Qualification {
    JUNIOR(1, "junior", new BigDecimal("3.51")),
    MIDDLE(2, "middle", new BigDecimal("11.30")),
    SENIOR(3, "senior", new BigDecimal("17.01"));
    private final int id;
    private final String value;

    private final BigDecimal SalaryPerHourUSD;

    Qualification(final int id, final String value, final BigDecimal SalaryPerHourUSD) {
        this.id = id;
        this.value = value;
        this.SalaryPerHourUSD = SalaryPerHourUSD;
    }

    public BigDecimal getSalaryPerHourUSD() {
        return SalaryPerHourUSD;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
