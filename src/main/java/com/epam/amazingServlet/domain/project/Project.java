package com.epam.amazingServlet.domain.project;


public class Project {
    private Long id;
    private Long managerId;
    private String projectName;
    private ProjectStatus projectStatus;
    private TechnicalTask technicalTask;
    private Bill bill;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public TechnicalTask getTechnicalTask() {
        return technicalTask;
    }

    public void setTechnicalTask(TechnicalTask technicalTask) {
        this.technicalTask = technicalTask;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", managerId=" + managerId +
                ", projectName='" + projectName + '\'' +
                ", projectStatus=" + projectStatus +
                ", technicalTask=" + technicalTask +
                ", bill=" + bill +
                '}';
    }
}
