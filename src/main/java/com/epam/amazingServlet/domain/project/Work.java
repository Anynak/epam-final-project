package com.epam.amazingServlet.domain.project;

import com.epam.amazingServlet.domain.Entity;
import com.epam.amazingServlet.domain.user.Qualification;

public class Work extends Entity {
    private Long id;
    private boolean finishStatus;
    private Long technicalTaskId;
    private String description;
    private Integer numberOfRequiredSpecialists;
    private Qualification requiredQualification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(boolean finishStatus) {
        this.finishStatus = finishStatus;
    }

    public Long getTechnicalTaskId() {
        return technicalTaskId;
    }

    public void setTechnicalTaskId(Long technicalTaskId) {
        this.technicalTaskId = technicalTaskId;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfRequiredSpecialists() {
        return numberOfRequiredSpecialists;
    }

    public void setNumberOfRequiredSpecialists(Integer numberOfRequiredSpecialists) {
        this.numberOfRequiredSpecialists = numberOfRequiredSpecialists;
    }

    public Qualification getRequiredQualification() {
        return requiredQualification;
    }

    public void setRequiredQualification(Qualification requiredQualification) {
        this.requiredQualification = requiredQualification;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", finishStatus=" + finishStatus +
                ", technicalTaskId=" + technicalTaskId +
                ", description='" + description + '\'' +
                ", numberOfRequiredSpecialists=" + numberOfRequiredSpecialists +
                ", requiredQualification=" + requiredQualification +
                '}';
    }
}
