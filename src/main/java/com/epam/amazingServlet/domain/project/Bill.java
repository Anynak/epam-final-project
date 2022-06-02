package com.epam.amazingServlet.domain.project;

import com.epam.amazingServlet.domain.Entity;

import java.math.BigDecimal;

public class Bill extends Entity {
    private Long id;
    private BigDecimal paidAmount;
    private BigDecimal totalAmount;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", paidAmount=" + paidAmount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
