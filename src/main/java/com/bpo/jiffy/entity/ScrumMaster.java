package com.bpo.jiffy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "employees") // Avoid circular references
@Entity
@Table(name = "scrum_master")
@DiscriminatorValue("SCRUM_MASTER")
public class ScrumMaster extends BaseUser {

    private String project;

    // OneToMany relationship with employees (optional)
    @OneToMany(mappedBy = "scrumMaster", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    // Proper equals() and hashCode() for JPA entities
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScrumMaster)) return false;
        ScrumMaster that = (ScrumMaster) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}