package com.bpo.jiffy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "scrumMaster") // Avoid circular references in toString
@Entity
@Table(name = "employee")
@DiscriminatorValue("EMPLOYEE")
public class Employee extends BaseUser {

    private String department;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY fetching is better for performance
    @JoinColumn(name = "scrum_master_id", nullable = false)
    private ScrumMaster scrumMaster;

    private String project;

    // Proper equals() and hashCode() for JPA entities
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId() != null && getId().equals(employee.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}