package com.epam.amazingServlet.domain.user;

import java.io.Serializable;

public class Manager extends User implements Serializable {
    public Manager() {
    }

    public Manager(User user) {
        super(user);
    }

}
