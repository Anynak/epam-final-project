package com.epam.amazingServlet.domain.user;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    public Customer() {
    }

    public Customer(User user) {
        super(user);
    }
}
