package com.epam.amazingServlet.domain.project;

import com.epam.amazingServlet.domain.Entity;
import com.epam.amazingServlet.domain.user.Customer;

public class TechnicalTask extends Entity {
    private Long id;
    private String title;
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "TechnicalTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", customer=" + customer +
                '}';
    }
}
