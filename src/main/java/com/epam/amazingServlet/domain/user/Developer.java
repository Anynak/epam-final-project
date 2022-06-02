package com.epam.amazingServlet.domain.user;

import java.io.Serializable;

public class Developer extends User implements Serializable {

    public Developer() {
    }


    public Developer(User user) {
        super(user);
    }

    private Qualification qualification;

    private Long projectId;

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
