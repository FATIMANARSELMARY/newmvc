package com.bpo.jiffy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("EMPLOYEE")
@Table(name = "employee")
public class Employee extends BaseUser {
    private String department;

   
}
